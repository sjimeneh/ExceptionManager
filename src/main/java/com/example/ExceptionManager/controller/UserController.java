package com.example.ExceptionManager.controller;

import com.example.ExceptionManager.controller.DTO.UserDTO;
import com.example.ExceptionManager.entity.User;
import com.example.ExceptionManager.service.IUserService;
import com.example.ExceptionManager.util.ResponseMessage;
import jakarta.validation.Valid;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    IUserService _iUserService;

    @GetMapping("/")
    public ResponseEntity<ResponseMessage<List<User>>> getAllUser(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseMessage<>(true,HttpStatus.OK.value(),
                        "User exist",_iUserService.getAllUsers()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage<User>> finUserById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage<>(true,HttpStatus.OK.value(),
                "User exist",_iUserService.getUserById(id)));
    }

    @GetMapping("/email")
    public ResponseEntity<ResponseMessage<User>> finUserByEmail(@RequestParam String email){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage<>(true,HttpStatus.OK.value(),
                "User exist",_iUserService.getUserByEmial(email)));
    }

    @PostMapping("/")
    public ResponseEntity<ResponseMessage<User>> saveUser(@RequestBody @Valid UserDTO userDTO){
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userDTO, User.class);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseMessage<>(true,HttpStatus.OK.value(),
                        "User saved successfully",_iUserService.saveUser(user)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage<User>> updateUser(@PathVariable(required = true) Long id, @RequestBody @Valid UserDTO userDTO){
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userDTO,User.class);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseMessage<>(true,HttpStatus.OK.value(),
                        "User updated successfully",_iUserService.updateUser(id,user)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage<?>> deleteUser(@PathVariable(required = true) Long id){
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(new ResponseMessage<>(true,HttpStatus.NO_CONTENT.value(),
                        "User deleted successfully",_iUserService.DeleteUserById(id)));
    }

}
