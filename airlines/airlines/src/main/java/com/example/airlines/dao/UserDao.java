package com.example.airlines.dao;

import com.example.airlines.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findByEmail(String email);
    boolean existsByEmail(String email);
}
