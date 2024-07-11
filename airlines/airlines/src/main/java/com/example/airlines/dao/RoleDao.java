package com.example.airlines.dao;

import com.example.airlines.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleDao extends JpaRepository<Role, Long> {
    @Query("")
    Role findByRoleName(String roleName);
}
