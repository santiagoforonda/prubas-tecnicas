package com.santyman.hermes.exceptions.customExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class BookingNotFoundException extends RuntimeException {
    
    public BookingNotFoundException(Long id){
        super(String.format("La reserva con el ID "+ id+ "No fue encontrada"));
    }

    public BookingNotFoundException(String reference){
        super(String.format("La reserva con la referencia %s no fue encontrada"));
    }
}
