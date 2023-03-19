package com.example.convservice.repositories;

import com.example.convservice.model.ConversationDetails;
import jdk.jfr.Label;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConversationDetailsRepository extends Repository<ConversationDetails, Long> {

    ConversationDetails save(ConversationDetails conversationDetails);

    @Query("SELECT * from conv_details cd where cd.conv_details_id not in (select conv_details_id from conversations)" +
        "and cd.tutor_id=:tutorId")
    List<ConversationDetails> findAllByTutorIdOrderByConvDetailsIdDesc(@Param("tutorId") Long tutorId);

    ConversationDetails findAllByConvDetailsId(Long convDetailsId);

    @Query("SELECT MIN(price) FROM conv_details where tutor_id=:tutorId")
    double findMinimumPrice(@Param("tutorId") Long tutorId);

    @Query("SELECT MIN(price) FROM conv_details cd where tutor_id=:tutorId" +
        " and (:convTypeId is null or cd.conv_type_id=:convTypeId)" +
        "and (:minLevel is null or cd.min_level >= :minLevel)" +
        "and (:languageId is null or cd.language_id = :languageId);")
    Double findMinimumPrice(@Param("tutorId") Long tutorId, @Param("convTypeId") Long convTypeId,
                            @Param("minLevel") Long minLevel, @Param("languageId") Long languageId);

    @Query("select count(distinct(tutor_id)) from conv_details cd " +
        "where cd.conv_details_id not in (select conv_details_id from conversations)")
    int countTutorsWithConvDetails();

    @Query("select count(distinct(cd.tutor_id)) from conv_details cd " +
        " full join addresses a on cd.address_id = a.address_id" +
        " where cd.conv_details_id not in (select conv_details_id from conversations)" +
        " and (price between :minPrice and :maxPrice or :maxPrice is null)" +
        " and (:countryId is null or a.country_id = :countryId)" +
        " and (:city is null or city = :city)" +
        " and (:convTypeId is null or cd.conv_type_id=:convTypeId)" +
        " and (:minLevel is null or cd.min_level >=:minLevel) " +
        " and (:languageId is null or cd.language_id =:languageId)")
    int countFilteredTutorsWithConvDetails(@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice,
                                           @Param("countryId") Long countryId, @Param("city") String city,
                                           @Param("convTypeId") Long convTypeId, @Param("minLevel") Long minLevel,
                                           @Param("languageId") Long languageId);

}
