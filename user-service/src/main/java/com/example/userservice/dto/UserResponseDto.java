package com.example.userservice.dto;

import com.example.userservice.model.Country;
import com.example.userservice.model.Student;
import com.example.userservice.model.Tutor;
import lombok.Builder;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.NonFinal;

import java.util.List;

@Builder(toBuilder = true)
@Value
public class UserResponseDto {
    Long id;
    String firstName;
    String lastName;
    String email;
    String password;
    Country nationality;
    String gender;
    List<String> roles;
    @NonFinal
    @Setter
    Student student;
    @NonFinal
    @Setter
    Tutor tutor;
    List<LanguageLevelDto> languageLevels;
    String location;
    @NonFinal
    @Setter
    String avatarName;
    Integer tutorConversationCount;
    Integer studentConversationCount;
    Double tutorAverageRate;
    Double studentAverageRate;
}
