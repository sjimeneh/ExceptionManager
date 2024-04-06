package com.example.ExceptionManager.service;

import com.example.ExceptionManager.entity.User;
import com.example.ExceptionManager.repository.IUserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    IUserRepository _iUserRepository;
    @Override
    public List<User> getAllUsers() {
        return _iUserRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        return _iUserRepository.save(user);
    }

    @Override
    public User getUserById(Long id) throws EntityNotFoundException {
        User user = _iUserRepository.findById(id).orElse(null);
        if(user != null){
            return user;
        }else{
            throw new EntityNotFoundException("User with id= "+id+" does not exist");
        }
    }

    @Override
    public User updateUser(Long id, User user)throws EntityNotFoundException {
        if(_iUserRepository.existsById(id)){
            user.setId(id);
            return _iUserRepository.save(user);
        }else{
            throw new EntityNotFoundException("User with id= "+id+" does not exist");
        }
    }

    @Override
    public User getUserByEmial(String email)throws EntityNotFoundException {
        User user = _iUserRepository.getUserByEmail(email).orElse(null);
        if(user != null){
            return user;
        }else{
            throw new EntityNotFoundException("User with email = "+email+" does not exist");
        }
    }

    @Override
    public boolean DeleteUserById(Long id) throws EntityNotFoundException {
        if(_iUserRepository.existsById(id)){
            _iUserRepository.deleteById(id);
            return true;
        }else{
            throw new EntityNotFoundException("User with id= "+id+" does not exist");
        }
    }
}
