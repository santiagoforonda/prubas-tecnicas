package com.santyman.hermes.exceptions.customExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT)
public class BookingExistException extends RuntimeException {


    public BookingExistException(){
        super(String.format("La reserva ya esta registrada, revise el *tokenn de pago* O el *codigo de referencia*"));
    }

}