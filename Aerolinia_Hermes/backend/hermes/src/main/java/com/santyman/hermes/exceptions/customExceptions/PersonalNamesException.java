package com.santyman.hermes.exceptions.customExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PersonalNamesException extends RuntimeException{
    
    public PersonalNamesException(String invalidName){
        super(String.format("El nombre %s no es un nombre valido", invalidName));
    } 
}