package com.finalproject.springbootfoodapp.repository;

import com.finalproject.springbootfoodapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    /** find user by email address */
    Optional<User> findByEmail(String email);
}
