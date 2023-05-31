package com.example.userservice.controllers;

import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;

@TestConfiguration
@RequiredArgsConstructor
public class DataSourceTestConfig {

    @Bean
    public PostgreSQLContainer<?> postgresContainer() {
        final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:13.4")
            .withUsername("postgres")
            .withPassword("postgres")
            .withDatabaseName("diploma-test")
            .withExposedPorts(5432)
            .withInitScript("init.sql")
            .withCreateContainerCmdModifier(cmd -> cmd
                .getHostConfig()
                .withPortBindings(
                    new PortBinding(Ports.Binding.bindPort(35435), new ExposedPort(5432))));
        container.start();
        return container;
    }

    @Bean
    @DependsOn("postgresContainer")
    public DataSource createTestDataSource() {
        final HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:35435/diploma-test");
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("postgres");
        return new HikariDataSource(hikariConfig);
    }

}