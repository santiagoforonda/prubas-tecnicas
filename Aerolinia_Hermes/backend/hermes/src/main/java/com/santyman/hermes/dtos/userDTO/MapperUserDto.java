package com.santyman.hermes.dtos.userDTO;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.santyman.hermes.entities.User;

@Component
public class MapperUserDto {

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public UserDto entityToDto(User user){
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setLastname(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setBookings(user.getBookings());
        return dto;
    }

    public User dtoToEntity(UserDto dto){
        User user = new User();
        user.setName(dto.getName());
        user.setLastName(dto.getLastname());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return user;
    }

    public UserDto optionalToDto(Optional<User> userToUpdate) {
        UserDto dto = new UserDto();
        dto.setId(userToUpdate.get().getId());
        dto.setName(userToUpdate.get().getName());
        dto.setLastname(userToUpdate.get().getLastName());
        dto.setEmail(userToUpdate.get().getEmail());
        dto.setPassword(passwordEncoder.encode(userToUpdate.get().getPassword()));
        dto.setBookings(userToUpdate.get().getBookings());
        return dto;
    }

}
