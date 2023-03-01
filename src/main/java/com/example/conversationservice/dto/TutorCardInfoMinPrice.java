package com.example.conversationservice.dto;

import lombok.Builder;
import lombok.Value;
import java.util.List;

@Builder
@Value
public class TutorCardInfoMinPrice {
    String firstName;
    String lastName;
    List<LanguageLevelDto> languageLevels;
    double minPrice;

}
