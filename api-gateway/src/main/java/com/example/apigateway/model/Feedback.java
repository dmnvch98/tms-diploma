package com.example.apigateway.model;

import lombok.Builder;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("feedbacks")
@Value
@Builder
@Jacksonized
public class Feedback {
    @Id
    Long feedbackId;
    @Column("conversation_id")
    Long conversationId;
    @Column("tutor_rate")
    @Setter
    @NonFinal
    Integer tutorRate;
    @Setter
    @NonFinal
    @Column("student_rate")
    Integer studentRate;
    @Setter
    @NonFinal
    @Column("tutor_feedback")
    String tutorFeedback;
    @Setter
    @NonFinal
    @Column("student_feedback")
    String studentFeedback;
}
