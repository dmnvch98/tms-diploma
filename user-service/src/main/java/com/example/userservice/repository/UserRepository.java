package com.example.userservice.repository;

import com.example.userservice.model.User;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends Repository<User, Long> {

    @Transactional
    User save(final User user);

    @Modifying
    @Query("UPDATE users SET first_name=:firstName, last_name=:lastName, email=:email, location=:location WHERE id=:id")
    Integer update(@Param("id") Long id, @Param("firstName") String firstName,
                @Param("lastName") String lastName, @Param("email") String email,
                @Param("location") String location);


    User findUserById(Long id);

    Boolean existsByEmail(String email);

    @Query("SELECT users.id AS id, users.email AS email, users.roles AS roles, users.gender AS gender, " +
        "users.password AS password, users.location AS location, users.last_name AS last_name, users.first_name " +
        "AS first_name, users.nationality AS nationality, users.avatar_name AS avatar_name, tutor.user_id AS tutor_user_id, tutor.about_me " +
        "AS tutor_about_me, tutor.tutor_id AS tutor_tutor_id FROM users " +
        "LEFT OUTER JOIN tutors tutor " +
        "ON tutor.user_id = users.id WHERE tutor.tutor_id=:tutorId")
    User findUserByTutorId(@Param("tutorId") Long tutorId);

    @Query("SELECT users.id AS id, users.email AS email, users.roles AS roles, users.gender AS gender, " +
        "users.password AS password, users.location AS location, users.last_name AS last_name, users.first_name " +
        "AS first_name, users.nationality AS nationality, users.avatar_name AS avatar_name, student.user_id AS student_user_id, student.about_me " +
        "AS student_about_me, student.student_id AS student_student_id FROM users " +
        "LEFT OUTER JOIN students student " +
        "ON student.user_id = users.id WHERE student.student_id=:studentId")
    User findUserByStudentId(@Param("studentId") Long studentId);

    User findUserByEmail(String email);

    Boolean existsByEmailAndPassword(String email, String password);

    @Query("UPDATE users set refresh_token=:refreshToken WHERE id=:userId")
    @Modifying
    void updateRefreshToken(@Param("refreshToken") String refreshToken, @Param("userId") Long userId);

    @Query("select distinct u.*, t.tutor_id AS tutor_tutor_id from users u" +
        " left outer join tutors t on u.id = t.user_id" +
        " right outer join conv_details cd on t.tutor_id = cd.tutor_id " +
        "where cd.conv_details_id not in (select conv_details_id from conversations)" +
        " and t.tutor_id >:lastTutorId order by t.tutor_id limit :pageSize")
    List<User> findTutorsWhoHaveNotBookedConvDetails(@Param("lastTutorId") Long lastTutorId, @Param("pageSize") int pageSize);

    @Query("UPDATE users set avatar_name=:avatarName WHERE id=:userId")
    @Modifying
    int setAvatar(@Param("avatarName") String avatarName, @Param("userId") Long userId);

    @Query("UPDATE users set avatar_name = null WHERE id=:userId")
    @Modifying
    int deleteAvatar(@Param("userId") Long userId);

    @Query("select distinct u.*, t.tutor_id AS tutor_tutor_id from users u" +
        " left outer join tutors t on u.id = t.user_id" +
        " right outer join conv_details cd on t.tutor_id = cd.tutor_id " +
        " full join addresses a on a.address_id = cd.address_id" +
        " where cd.conv_details_id not in (select conv_details_id from conversations)" +
        " and t.tutor_id >:lastTutorId and (price between :minPrice and :maxPrice or :maxPrice is null)" +
        " and (:countryId is null or a.country_id =:countryId)" +
        " and (:city is null or city = :city) " +
        " and (:convTypeId is null or cd.conv_type_id=:convTypeId)" +
        " and (:minLevel is null or cd.min_level >=:minLevel) " +
        " and (:languageId is null or cd.language_id =:languageId)" +
        " order by t.tutor_id limit :pageSize")
    List<User> filterTutorsWhoHaveNotBookedConvDetails(@Param("lastTutorId") Long lastTutorId, @Param("pageSize") int pageSize,
                                                       @Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice,
                                                       @Param("countryId") Long countryId, @Param("city") String city,
                                                       @Param("convTypeId") Long convTypeId, @Param("minLevel") Long minLevel,
                                                       @Param("languageId") Long languageId);

    @Modifying
    @Query("UPDATE users set roles = array_append(roles, CAST(:role AS text)) where id=:id")
    int addRoleToUser(@Param("role") String role, @Param("id") Long id);

    @Modifying
    @Query("UPDATE users set roles = array_remove(roles, CAST(:role AS text)) where id=:id")
    int deleteRoleFromUser(@Param("role") String role, @Param("id") Long id);
}
