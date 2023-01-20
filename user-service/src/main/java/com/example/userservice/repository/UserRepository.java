package com.example.userservice.repository;

import com.example.userservice.model.User;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends Repository<User, Long> {

    User save(final User user);

    User findUserById(Long id);

    Boolean existsByEmail(String email);

    @Modifying
    @Query("update users u set first_name=:firstName where u.id=:userId")
    void updateFirstName(@Param("firstName") String firstName, @Param("userId") Long userId);

    @Modifying
    @Query("update users u set last_name=:lastName where u.id=:userId")
    void updateLastName(@Param("lastName") String lastName, @Param("userId") Long userId);

    @Modifying
    @Query("update users u set email=:email where u.id=:userId")
    void updateEmail(@Param("email") String email, @Param("userId") Long userId);

    @Modifying
    @Query("update users u set nationality=:countryId where u.id=:userId")
    void updateNationality(@Param("countryId") Long countryId, @Param("userId") Long userId);

}
