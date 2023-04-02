package com.example.userservice.controllers;

import com.example.userservice.model.Student;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;
import org.springframework.jdbc.core.JdbcTemplate;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


import javax.sql.DataSource;
import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Import(DataSourceTestConfig.class)
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    private Student student = Student.builder().build();
    private User user = User
        .builder()
        .student(student)
        .email("testEmail")
        .firstName("Eugen")
        .lastName("Demyanovich")
        .gender("Male")
        .password("12345")
        .roles(List.of("TUTOR"))
        .nationality(1L)
        .location("Minsk")
        .build();
    @BeforeAll
    public void init() {
        jdbcTemplate = new JdbcTemplate(dataSource);
        migrateDatabase();
    }

    @Test
    void createUserWithNewEmail() {
        User user1 = userRepository.save(user);
        assertThat(user1).isNotNull();
        jdbcTemplate.execute("DELETE from students where student_id = " + user1.getStudent().getStudentId());
        jdbcTemplate.execute("DELETE from users where id = " + user1.getId());
    }

    @Test
    void createUserWithExistingEmail() {
        User user1 = userRepository.save(user);
        assertThrows(DbActionExecutionException.class, () -> {
            User user2 = userRepository.save(user);
        });
        jdbcTemplate.execute("DELETE from students where student_id = " + user1.getStudent().getStudentId());
        jdbcTemplate.execute("DELETE from users where id = " + user1.getId());
    }

    private void migrateDatabase() {
        final Flyway flyway = Flyway
            .configure()
            .dataSource(dataSource)
            .locations("filesystem:/Users/Yauhen/Documents/Yauhen/tms-diploma/db")
            .load();
        flyway.migrate();
    }
}