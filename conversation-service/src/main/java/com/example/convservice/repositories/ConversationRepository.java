package com.example.convservice.repositories;


import com.example.convservice.model.Conversation;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConversationRepository extends Repository<Conversation, Long> {

    Conversation save(Conversation conversation);

    List<Conversation> findAllByStudentId(Long studentId);

    @Query("select c.* from conversations c" +
        " join conv_details cd on cd.conv_details_id = c.conv_details_id" +
        " where cd.tutor_id=:tutorId")
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
}
