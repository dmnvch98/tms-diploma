package com.example.userservice.facades;

import com.example.userservice.converters.LanguageLevelConverter;
import com.example.userservice.converters.UserConverter;
import com.example.userservice.dto.CredentialsDto;
import com.example.userservice.dto.LanguageLevelDto;
import com.example.userservice.dto.UserRequestDto;
import com.example.userservice.dto.UserResponseDto;
import com.example.userservice.model.User;
import com.example.userservice.model.UserLanguageLevel;
import com.example.userservice.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class UserFacade {

    private final UserService userService;
    private final LanguageLevelService languageLevelService;
    private final UserConverter userConverter;
    private final LanguageLevelConverter languageLevelConverter;
    private final ConversationService conversationService;
    private final FeedbackService feedbackService;
    private final StudentService studentService;
    private final TutorService tutorService;

    @Transactional
    public UserResponseDto save(UserRequestDto userRequestDto) {
        User user = userConverter.userRequestDtoToUserSave(userRequestDto);
        user = userService.save(user);
        List<UserLanguageLevel> userLanguageLevels =
            extractUserLanguageLevelsFromDto(userRequestDto, user.getId());
        List<LanguageLevelDto> LanguageLevelDto2List = saveUserLanguageLevels(userLanguageLevels);
        return userConverter.userToResponseDto(user, LanguageLevelDto2List, 0, 0, 0D, 0D);
    }

    @Transactional
    public UserResponseDto update(UserRequestDto userRequestDto, Long userId) {
        User user = userConverter.userRequestDtoToUserUpdate(userRequestDto, userId);
        if (user.getStudent() != null) {
            studentService.save(user.getStudent());
        }
        if (user.getTutor() != null) {
            tutorService.save(user.getTutor());
        }
        user = userService.update(user);
        List<UserLanguageLevel> existingUserLanguageLevels = languageLevelService.findAllByUserId(userId);

        List<UserLanguageLevel> userLanguageLevels =
            extractUserLanguageLevelsFromDto(userRequestDto, user.getId());

        List<Long> languageLevelsIdToDelete = findLanguageLevelIdsToDelete(existingUserLanguageLevels, userLanguageLevels);
        if (languageLevelsIdToDelete.size() > 0) {
            languageLevelsIdToDelete.forEach(x -> languageLevelService.deleteUserLanguageLevel(x, userId));
        }

        List<LanguageLevelDto> LanguageLevelDto2List = saveUserLanguageLevels(userLanguageLevels);
        return userConverter.userToResponseDto(
            user,
            LanguageLevelDto2List,
            conversationService.countTutorLessons(getTutorIdIfExists(user)),
            conversationService.countStudentLessons(getStudentIdIfExists(user)),
            feedbackService.findAvgRateForTutor(getTutorIdIfExists(user)),
            feedbackService.findAvgRateForStudent(getStudentIdIfExists(user))
        );
    }

    public UserResponseDto get(Long id) {
        User user = userService.get(id);
        return userConverter.userToResponseDto(
            user,
            findLanguageLevelsByUserId(id),
            conversationService.countTutorLessons(getTutorIdIfExists(user)),
            conversationService.countStudentLessons(getStudentIdIfExists(user)),
            feedbackService.findAvgRateForTutor(getTutorIdIfExists(user)),
            feedbackService.findAvgRateForStudent(getStudentIdIfExists(user))
        );
    }

    public List<LanguageLevelDto> findLanguageLevelsByUserId(Long userId) {
        return languageLevelService
            .findLanguageLevelsByUserId(userId)
            .stream()
            .map(languageLevelConverter::languageLevelToDto)
            .toList();
    }

    public Boolean isEmailExists(String email) {
        return userService.isEmailExists(email);
    }


    private List<UserLanguageLevel> extractUserLanguageLevelsFromDto(UserRequestDto userRequestDto, Long userId) {
        return userRequestDto.getLanguageLevels().stream()
            .map(x -> languageLevelService
                .getLanguageLevelId(x.getLevel().getLevelId(), x.getLanguage().getLanguageId()))
            .map(x -> languageLevelConverter.languageLevelIdToUll(x, userId))
            .toList();
    }

    public UserResponseDto deleteUserLanguageLevels(Long languageId, Long levelId, Long userId) {
        Long languageLevelId = languageLevelService.getLanguageLevelId(levelId, languageId);
        languageLevelService.deleteUserLanguageLevel(languageLevelId, userId);
        return get(userId);
    }

    public UserResponseDto findUserByTutorId(Long tutorId) {
        User user = userService.findUserByTutorId(tutorId);
        return userConverter.userToResponseDto(user,
            findLanguageLevelsByUserId(user.getId()),
            conversationService.countTutorLessons(getTutorIdIfExists(user)),
            conversationService.countStudentLessons(getStudentIdIfExists(user)),
            feedbackService.findAvgRateForTutor(getTutorIdIfExists(user)),
            feedbackService.findAvgRateForStudent(getStudentIdIfExists(user))
        );
    }

    public UserResponseDto findUserByStudentId(Long studentId) {
        User user = userService.findUserByStudentId(studentId);
        return userConverter.userToResponseDto(
            user,
            findLanguageLevelsByUserId(user.getId()),
            conversationService.countTutorLessons(getTutorIdIfExists(user)),
            conversationService.countStudentLessons(getStudentIdIfExists(user)),
            feedbackService.findAvgRateForTutor(getTutorIdIfExists(user)),
            feedbackService.findAvgRateForStudent(getStudentIdIfExists(user))
        );
    }

    public User findUserByEmail(String email) {
        return userService.findUserByEmail(email);
    }

    public Boolean existsByEmailAndPassword(CredentialsDto credentialsDto) {
        return userService.existsByEmailAndPassword(credentialsDto.getEmail(), credentialsDto.getPassword());
    }

    public void updateRefreshToken(String token, Long userId) {
        userService.updateRefreshToken(token, userId);
    }

    public List<LanguageLevelDto> saveUserLanguageLevels(List<UserLanguageLevel> userLanguageLevels) {
        return userLanguageLevels
            .stream()
            .map(languageLevelService::saveUserLanguageLevel)
            .map(languageLevelService::findLanguageLevel)
            .map(languageLevelConverter::languageLevelToDto)
            .toList();
    }

    public int setAvatar(Long userId) {
        return userService.setAvatar(userId);
    }

    public int deleteAvatar(Long userId) {
        return userService.deleteAvatar(userId);
    }

    private List<Long> findLanguageLevelIdsToDelete(List<UserLanguageLevel> existing,
                                                    List<UserLanguageLevel> fromDto) {
        List<Long> list1 = new ArrayList<>(existing.stream().map(UserLanguageLevel::getLanguageLevelId).toList());
        List<Long> list2 = fromDto.stream().map(UserLanguageLevel::getLanguageLevelId).toList();
        list1.removeAll(list2);
        return list1;
    }

    public Long getTutorIdIfExists(User user) {
        return user.getTutor() != null
            ? user.getTutor().getTutorId()
            : 0L;
    }

    public Long getStudentIdIfExists(User user) {
        return user.getStudent() != null
            ? user.getStudent().getStudentId()
            : 0L;
    }
}
