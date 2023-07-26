package com.example.userservice.repository;

import com.example.userservice.model.User;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends Repository<User, Long> {

    String userSelect = "SELECT u.id AS id, u.email AS email, u.roles AS roles, u.gender AS gender, " +
        "u.password AS password, u.location AS location, u.last_name AS last_name, u.first_name AS first_name, " +
        "u.nationality AS nationality, u.avatar_name AS avatar_name, s.user_id AS student_user_id, s.about_me AS student_about_me, " +
        "s.student_id AS student_student_id, s.presentation_url AS student_presentation_file_name, t.user_id AS tutor_user_id, t.about_me AS tutor_about_me, " +
        "t.tutor_id AS tutor_tutor_id, t.presentation_url AS tutor_presentation_url " +
        "FROM users u " +
        "LEFT OUTER JOIN students s " +
        "ON s.user_id = u.id " +
        "LEFT OUTER JOIN tutors t " +
        "ON t.user_id = u.id";

    @Transactional
    User save(final User user);

    @Query("INSERT INTO users(first_name, last_name, email, password, nationality, location, roles, gender) " +
        "VALUES (:firstName, :lastName, :email, :password, :nationality, :location, :roles, :gender)")
    void save(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("email") String email,
              @Param("password") String password, @Param("nationality") Long nationality, @Param("location") String location,
              @Param("roles") String[] roles, @Param("gender") String gender);


    @Modifying
    @Query("UPDATE users SET first_name=:firstName, last_name=:lastName, email=:email, location=:location WHERE id=:id")
    Integer update(@Param("id") Long id, @Param("firstName") String firstName,
                @Param("lastName") String lastName, @Param("email") String email,
                @Param("location") String location);


    User findUserById(Long id);

    Boolean existsByEmail(String email);

    @Query(userSelect + " WHERE t.tutor_id=:tutorId" )
    User findUserByTutorId(@Param("tutorId") Long tutorId);

    @Query(userSelect + " WHERE s.student_id=:studentId")
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
