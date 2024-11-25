package com.santyman.hermes.exceptions.customExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PersonalLastNameException extends RuntimeException {

    public PersonalLastNameException(String invalidLastName){
        super(String.format("El apellido %s no es un apellido valido",invalidLastName));
    }
    
}