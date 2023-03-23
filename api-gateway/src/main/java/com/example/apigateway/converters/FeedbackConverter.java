package com.example.apigateway.converters;

import com.example.apigateway.dto.FeedbackAboutStudentResponseDto;
import com.example.apigateway.dto.FeedbackAboutTutorResponseDto;
import com.example.apigateway.dto.FeedbackCardInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface FeedbackConverter {
    @Mapping(source = "dto.tutorRate", target = "rate")
    @Mapping(source = "dto.tutorFeedback", target = "feedback")
    FeedbackCardInfoDto feedbackAboutStudentResponseDtoToCardDto(FeedbackAboutStudentResponseDto dto,
                                                                 String languageDescription,
                                                                 String userAvatarUrl,
                                                                 String firstName,
                                                                 String lastName);

    @Mapping(source = "dto.studentRate", target = "rate")
    @Mapping(source = "dto.studentFeedback", target = "feedback")
    FeedbackCardInfoDto feedbackAboutTutorResponseDtoToCardDto(FeedbackAboutTutorResponseDto dto,
                                                               String languageDescription,
                                                               String userAvatarUrl,
                                                               String firstName,
                                                               String lastName);
}
