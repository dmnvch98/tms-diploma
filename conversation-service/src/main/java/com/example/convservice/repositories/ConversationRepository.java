package com.example.convservice.repositories;


import com.example.convservice.model.Conversation;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConversationRepository extends Repository<Conversation, Long> {

    Conversation save(Conversation conversation);

    List<Conversation> findAllByStudentIdOrderByConvId(Long studentId);

    @Query("select c.* from conversations c" +
        " join conv_details cd on cd.conv_details_id = c.conv_details_id" +
        " where cd.tutor_id=:tutorId" +
        " order by c.conv_id desc")
    List<Conversation> findAllByTutorId(@Param("tutorId") Long tutorId);

    @Query("select c.student_id from conversations c where conv_id=:convId")
    Long findStudentIdByConversationId(@Param("convId") Long convId);

    @Query("select cd.tutor_id from conversations c " +
        "join conv_details cd on cd.conv_details_id = c.conv_details_id " +
        "where c.conv_id=:convId")
    Long findTutorIdByConversationId(@Param("convId") Long convId);

    @Query("select cd.language_id " +
        "from conversations c " +
        "join conv_details cd on cd.conv_details_id = c.conv_details_id " +
        "where c.conv_id=:convId")
    Long findLanguageIdByConversationId(@Param("convId") Long convId);

    Integer countAllByConvIdAndStudentId(Long convId, Long studentId);

    @Query("select count(c.conv_id) " +
        "from conv_details cd " +
        "join conversations c on cd.conv_details_id = c.conv_details_id " +
        "where tutor_id=:tutorId and c.conv_id=:convId")
    Integer countAllByConvIdAndTutorId(@Param("convId") Long convId, @Param("tutorId") Long tutorId);

    @Modifying
    @Query("UPDATE conversations set student_left_feedback = true where conv_id=:convId")
    Integer updateStudentLeftFeedbackFlag(@Param("convId") Long convId);

    @Modifying
    @Query("UPDATE conversations set tutor_left_feedback = true where conv_id=:convId")
    Integer updateTutorLeftFeedbackFlag(@Param("convId") Long convId);

    @Modifying
    @Query("UPDATE conversations set status_id = 5 where conv_id=:convId")
    Integer updateConversationStatusToFinish(@Param("convId") Long convId);

    int countAllByStatusIdAndStudentId(Long statusId, Long studentId);

    @Query("select count(*) from conversations c join conv_details cd on cd.conv_details_id = c.conv_details_id " +
        " where cd.tutor_id=:tutorId and c.status_id=:statusId")
    int countAllByStatusIdAndTutorId(@Param("statusId") Long statusId, @Param("tutorId") Long tutorId);

}
