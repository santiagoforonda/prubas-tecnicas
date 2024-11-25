package com.santyman.hermes.exceptions.customExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FlightNotFoundException extends RuntimeException{
    

    public FlightNotFoundException(Long id){
        super(String.format("El vuelo con el ID"+ id +" no fue encontrado"));
    }

    public FlightNotFoundException(int flightNumber){
        super(String.format("El vuelo con el numero de vuelo"+ flightNumber+"No fue encontrado"));
    }
}
