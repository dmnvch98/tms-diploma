package com.example.userservice.model;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Value
@Builder
@Table("conversations")
public class Conversation {
    @Id
    Long id;
    @Column("status_id")
    Long statusId;
    @Column("student_id")
    Long studentId;
    @Column("conv_details_id")
    Long conversationDetailsId;

}
