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
}
