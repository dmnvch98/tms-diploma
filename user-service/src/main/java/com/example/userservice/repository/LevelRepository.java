package com.example.userservice.repository;

import com.example.userservice.model.Level;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface LevelRepository extends Repository<Level, Long> {

    List<Level> findAll();
}
