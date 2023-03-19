package com.example.convservice.repositories;

import com.example.convservice.model.Feedback;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FeedbackRepository extends Repository<Feedback, Long> {

    @Modifying
    @Query("INSERT INTO feedbacks (conversation_id, student_feedback, student_rate) " +
        "VALUES (:convId, :studentFeedback, :studentRate) ON CONFLICT(conversation_id) " +
        " DO UPDATE SET student_feedback=:studentFeedback, student_rate=:studentRate")
    Integer saveFeedbackAboutTutor(@Param("convId") Long convId,
                                   @Param("studentFeedback") String studentFeedback,
                                   @Param("studentRate") Integer studentRate);

    @Modifying
    @Query("INSERT INTO feedbacks (conversation_id, tutor_feedback, tutor_rate) " +
        "VALUES (:convId, :tutorFeedback, :tutorRate) ON CONFLICT(conversation_id) " +
        "DO UPDATE SET tutor_feedback=:tutorFeedback, tutor_rate=:tutorRate")
    Integer saveFeedbackAboutStudent(@Param("convId") Long convId,
                                     @Param("tutorFeedback") String tutorFeedback,
                                     @Param("tutorRate") Integer tutorRate);

    Feedback findAllByConversationId(Long conversationId);

    @Query("select f.* from feedbacks f " +
        "join conversations c on c.conv_id = f.conversation_id " +
        "join conv_details cd on cd.conv_details_id = c.conv_details_id " +
        "where cd.tutor_id=:tutorId and f.student_rate IS NOT NULL" +
        "  and f.student_feedback IS NOT NULL order by f.feedback_id desc")
    List<Feedback> findFeedbacksAboutTutor(@Param("tutorId") Long tutorId);

    @Query("select f.* from feedbacks f " +
        "join conversations c on c.conv_id = f.conversation_id " +
        "join conv_details cd on cd.conv_details_id = c.conv_details_id " +
        "where c.student_id=:studentId and f.tutor_rate IS NOT NULL " +
        "  and f.tutor_feedback IS NOT NULL order by f.feedback_id desc")
    List<Feedback> findFeedbacksAboutStudent(@Param("studentId") Long studentId);

    @Query("select avg(student_rate) from feedbacks " +
        "join conversations c on c.conv_id = feedbacks.conversation_id " +
        "join conv_details cd on cd.conv_details_id = c.conv_details_id " +
        "where tutor_id=:tutorId")
    Double findAvgRateForTutor(@Param("tutorId") Long tutorId);

    @Query("select avg(tutor_rate) from feedbacks " +
        "join conversations c on c.conv_id = feedbacks.conversation_id " +
        "where student_id=:studentId")
    Double findAvgRateForStudent(@Param("studentId") Long studentId);

}
