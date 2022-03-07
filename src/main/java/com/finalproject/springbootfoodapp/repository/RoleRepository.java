package com.finalproject.springbootfoodapp.repository;

import com.finalproject.springbootfoodapp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findById(Long id);

    Optional<Role> findByRoleName(String roleName);
}
