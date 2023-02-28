package com.example.userservice.converters;

import com.example.userservice.converters.utils.FindAddress;
import com.example.userservice.converters.utils.FindLanguageLevelById;
import com.example.userservice.converters.utils.FindLanguageLevelId;
import com.example.userservice.dto.ConversationDetailsRequestDto;
import com.example.userservice.dto.ConversationDetailsResponseDto;
import com.example.userservice.model.ConversationDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(uses = {FindAddress.class, FindLanguageLevelId.class, FindLanguageLevelById.class})
public interface ConversationConverter {
    @Mapping(source = "minimumLanguageLevel", target = "minLangLevel")
    @Mapping(source = "startDate", target = "startDate", dateFormat = "yyyy-MM-dd HH:mm")
    @Mapping(source = "endDate", target = "endDate", dateFormat = "yyyy-MM-dd HH:mm")
    ConversationDetails dtoToConversationDetails(ConversationDetailsRequestDto dto);

    @Mapping(source = "addressId", target = "address")
    @Mapping(source = "minLangLevel", target = "minimumLanguageLevel")
    ConversationDetailsResponseDto conversationDetailsToResponseDto(ConversationDetails conversationDetails);
}
