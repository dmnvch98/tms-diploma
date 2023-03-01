package com.example.conversationservice.repositories;

import com.example.conversationservice.model.ConversationDetails;
import com.example.conversationservice.model.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConversationDetailsRepository extends Repository<ConversationDetails, Long> {
    String FIND_TUTORS_WITH_EXISTING_CONV_DETAILS =
        "SELECT distinct users.id AS id, users.email AS email, users.roles AS roles, users.gender AS gender, " +
            "users.password AS password, users.location AS location, users.last_name AS last_name, users.first_name " +
            "AS first_name, users.nationality AS nationality, tutor.user_id AS tutor_user_id, tutor.about_me " +
            "AS tutor_about_me, tutor.tutor_id AS tutor_tutor_id FROM conv_details cd " +
            "LEFT OUTER JOIN tutors tutor " +
            "ON cd.tutor_id = tutor.tutor_id " +
            "LEFT OUTER JOIN users " +
            "ON tutor.user_id = users.id";

    ConversationDetails save(ConversationDetails conversationDetails);

    List<ConversationDetails> findAllByTutorId(Long tutorId);

    ConversationDetails findAllByConvDetailsId(Long convDetailsId);

    @Query(FIND_TUTORS_WITH_EXISTING_CONV_DETAILS)
    List<User> findTutorsWithExistingConvDetails();

    @Query(FIND_TUTORS_WITH_EXISTING_CONV_DETAILS + 
        " LEFT OUTER JOIN language_levels ll on cd.min_lang_level_id = ll.language_level_id" +
        " LEFT OUTER JOIN languages l on l.language_id = ll.language_id" +
        " LEFT OUTER JOIN levels l2 on ll.level_id = l2.level_id" +
        " WHERE cd.price <=:price and cd.conv_type_id =:convTypeId and users.location =:location " +
        "and ll.language_id =:languageId AND l2.level_id <=:levelId")
    List<User> filterTutors(@Param("price") double price, @Param("convTypeId") Long convTypeId,
                            @Param("location") String location, @Param("languageId") Long languageId,
                            @Param("levelId") Long levelId);
}
