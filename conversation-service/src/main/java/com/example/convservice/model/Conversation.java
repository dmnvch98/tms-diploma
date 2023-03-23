package com.example.convservice.model;

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
    Long convId;
    @Column("status_id")
    Long statusId;
    @Column("student_id")
    Long studentId;
    @Column("conv_details_id")
    Long conversationDetailsId;
    @Column("student_left_feedback")
    Boolean studentLeftFeedback;
    @Column("tutor_left_feedback")
    Boolean tutorLeftFeedback;

}
