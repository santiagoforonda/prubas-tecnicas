package com.santyman.hermes.exceptions.customExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    
    public UserNotFoundException(Long id){
        super(String.format("El usuario con el ID" + id + "No fue encontrado"));
    }

    public UserNotFoundException(String email){
        super(String.format("El usuario con el email %s no fue encontrado", email));
    }
}
