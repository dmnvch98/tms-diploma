package com.example.userservice.controllers;

import com.example.userservice.model.Student;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Import(DataSourceTestConfig.class)
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private User newUser;

    private final Student defaultStudent = Student.builder().build();
    private final User defaultUser = User
        .builder()
        .student(defaultStudent)
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
    }

    @BeforeEach
    public void createUser() {
        newUser = userRepository.save(defaultUser);
    }

    @Test
    @DisplayName("Should return user when saving a new user")
    void createUserTest() {
        assertThat(newUser).isNotNull();
    }

    @Test
    @DisplayName("Should throw exception when saving user with existing email")
    void createUserWithExistingEmail() {
        assertThrows(DbActionExecutionException.class, () -> {
            userRepository.save(defaultUser);
        });
    }

    @Test
    @DisplayName("Should return user by valid user id")
    void getUserByUserId() {
        User extractedUser = userRepository.findUserById(newUser.getId());
        assertThat(extractedUser.getId()).isEqualTo(newUser.getId());
    }

    @Test
    @DisplayName("Should return user by valid student id")
    void getUserByStudent() {
        Student student = newUser.getStudent();
        User extractedUser = userRepository.findUserByStudentId(student.getStudentId());
        assertThat(extractedUser.getId()).isEqualTo(newUser.getId());
    }

    @AfterEach
    @Transactional
    public void deleteUserAndStudent() {
        jdbcTemplate.execute("DELETE from students");
        jdbcTemplate.execute("DELETE from users");
    }

}