package com.example.ExceptionManager.repository;

import com.example.ExceptionManager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User,Long> {
    Optional<User> getUserByEmail(String email);
}
