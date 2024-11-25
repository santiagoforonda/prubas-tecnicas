package com.santyman.hermes.exceptions.customExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException(){
        super(String.format("la password no es valida, La contraseña debe tener al menos 8 caracteres, incluyendo una letra mayúscula, una minúscula, un número y un carácter especial."));
    }
    
}
