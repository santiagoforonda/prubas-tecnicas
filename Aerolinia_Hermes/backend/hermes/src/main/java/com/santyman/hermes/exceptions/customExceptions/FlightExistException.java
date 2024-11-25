package com.santyman.hermes.exceptions.customExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class FlightExistException extends RuntimeException{

    public FlightExistException(int flightNumber){
        super(String.format("El vuelo con el numero " + flightNumber + " ya se encuentra registrado"));
    }
}
