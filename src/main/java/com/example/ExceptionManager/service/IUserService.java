package com.example.ExceptionManager.service;

import com.example.ExceptionManager.entity.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {
    List<User> getAllUsers();
    User saveUser(User user) throws EntityNotFoundException;
    User getUserById(Long id) throws EntityNotFoundException;
    User updateUser(Long id, User user) throws EntityNotFoundException;
    User getUserByEmial(String email) throws EntityNotFoundException;
    boolean DeleteUserById(Long id) throws EntityNotFoundException;
}
