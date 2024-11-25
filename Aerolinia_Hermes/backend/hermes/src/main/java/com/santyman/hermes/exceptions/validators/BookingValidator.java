package com.santyman.hermes.exceptions.validators;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.santyman.hermes.entities.Booking;
import com.santyman.hermes.exceptions.customExceptions.BookingExistException;
import com.santyman.hermes.repositories.BookingRepository;

@Component
public class BookingValidator {
    
    @Autowired
    private BookingRepository bookingRepo;

    public void validBookingExist(Booking booking){
        Optional<Booking> bookingExist = bookingRepo.findByPaymentToken(booking.getPaymentToken());
        Optional<Booking> bookingExistAux = bookingRepo.findByReferenceCode(booking.getReferenceCode());
        if(bookingExist.isPresent()||bookingExistAux.isPresent()){
            throw new BookingExistException();
        }
    }
}