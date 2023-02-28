package com.example.userservice.model;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Value
@Builder
@Table("conv_details")
public class ConversationDetails {
    @Id
    Long convDetailsId;
    @Column("tutor_id")
    Long tutorId;
    @Column("conv_type_id")
    Long conversationTypeId;
    @Column("price")
    double price;
    @Column("address_id")
    Long addressId;
    @Column("min_lang_level_id")
    Long minLangLevel;
    @Column("start_date")
    LocalDateTime startDate;
    @Column("end_date")
    LocalDateTime endDate;
}
