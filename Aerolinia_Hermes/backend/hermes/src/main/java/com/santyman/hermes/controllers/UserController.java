package com.santyman.hermes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santyman.hermes.dtos.userDTO.UserDto;
import com.santyman.hermes.services.IUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    
    @Autowired
    private IUserService userService;

    @GetMapping("/getUsers")
    public ResponseEntity<List<UserDto>> showUsers(){
        return new ResponseEntity<List<UserDto>>(userService.showUser(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getById(id));
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @Valid @RequestBody UserDto userDto){
        UserDto userDtoResponse = userService.updateUser(id, userDto);
        return new ResponseEntity<>(userDtoResponse,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long id){
        Boolean userElimated = userService.deleteUser(id);
        return new ResponseEntity<>(userElimated,HttpStatus.OK);
    }

}