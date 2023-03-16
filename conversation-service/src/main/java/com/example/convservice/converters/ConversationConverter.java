package com.example.convservice.converters;

import com.example.convservice.converters.utils.*;
import com.example.convservice.dto.*;
import com.example.convservice.model.Conversation;
import com.example.convservice.model.ConversationDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(uses = {FindAddress.class, FindConversationTypeById.class, FindConversationDetailsById.class, FindConversationStatusById.class})
public interface ConversationConverter {
    @Mapping(source = "startDate", target = "startDate", dateFormat = "yyyy-MM-dd HH:mm")
    @Mapping(source = "endDate", target = "endDate", dateFormat = "yyyy-MM-dd HH:mm")
    ConversationDetails dtoToConversationDetails(ConversationDetailsRequestDto dto);

    @Mapping(source = "conversationDetails.addressId", target = "address")
    @Mapping(source = "conversationDetails.conversationTypeId", target = "conversationType")
    ConversationDetailsResponseDto conversationDetailsToResponseDto(ConversationDetails conversationDetails,
                                                                    LanguageLevelDto minLanguageLevel);

    Conversation requestDtoToConversation(ConversationRequestDto dto, Long statusId);

    @Mapping(source = "convId", target = "id")
    @Mapping(source = "conversationDetailsId", target = "conversationDetails")
    @Mapping(source = "statusId", target = "status")
    ConversationResponseDto conversationToResponseDto(Conversation conversation);
}
