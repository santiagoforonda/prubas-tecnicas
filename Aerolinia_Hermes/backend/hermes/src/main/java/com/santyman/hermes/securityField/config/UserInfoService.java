package com.santyman.hermes.securityField.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.santyman.hermes.dtos.userDTO.MapperUserDto;
import com.santyman.hermes.dtos.userDTO.UserDto;
import com.santyman.hermes.entities.User;
import com.santyman.hermes.exceptions.customExceptions.UserNotFoundException;
import com.santyman.hermes.exceptions.validators.personalInfoValidator;
import com.santyman.hermes.repositories.UserRepository;

@Service
public class UserInfoService implements UserDetailsService {

    private final UserRepository userRepo;

    @Autowired
    private personalInfoValidator personalValidator;

    @Autowired
    private MapperUserDto mapeador;

    public UserInfoService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public UserDto registerUser(UserDto user) {
        personalInfoValidator.validateEmail(user.getEmail());
        personalInfoValidator.validatePersonalName(user.getName());
        personalInfoValidator.validatePersonalLastname(user.getLastname());
        personalInfoValidator.validatePassword(user.getPassword());
        personalValidator.validExistEmail(user.getEmail());
        User userAux = userRepo.save(mapeador.dtoToEntity(user));
        return mapeador.entityToDto(userAux);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username).orElseThrow(()-> new UserNotFoundException(username));
        UserInfoDetails userDetails = new UserInfoDetails(user);
        return userDetails;
    }
    
}
