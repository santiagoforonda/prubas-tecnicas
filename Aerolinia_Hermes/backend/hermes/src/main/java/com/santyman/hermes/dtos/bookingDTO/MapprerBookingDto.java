package com.santyman.hermes.dtos.bookingDTO;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.santyman.hermes.entities.Booking;

@Component
public class MapprerBookingDto {
    
    
    public Booking dtoToEntity(BookingDto dto){
        Booking booking = new Booking();
        booking.setStatus(dto.getStatus());
        booking.setCheckInd(dto.getCheckInd());
        booking.setCreatedAt(dto.getCreatedAt());
        booking.setPaymentToken(dto.getPaymentToken());
        booking.setReferenceCode(dto.getReferenceCode());
        return booking;
    }

    public BookingDto entityToDto(Booking booking){
        BookingDto dto = new BookingDto();
        dto.setId(booking.getId());
        dto.setCheckInd(booking.getCheckInd());
        dto.setCreatedAt(booking.getCreatedAt());
        dto.setPaymentToken(booking.getPaymentToken());
        dto.setReferenceCode(booking.getReferenceCode());
        dto.setStatus(booking.getStatus());
        return dto;
    }

    public BookingDto optionalToDto(Optional<Booking> bookingToUpdate) {
        BookingDto dto = new BookingDto();
        dto.setId(bookingToUpdate.get().getId());
        dto.setCheckInd(bookingToUpdate.get().getCheckInd());
        dto.setCreatedAt(bookingToUpdate.get().getCreatedAt());
        dto.setPaymentToken(bookingToUpdate.get().getPaymentToken());
        dto.setReferenceCode(bookingToUpdate.get().getReferenceCode());
        dto.setStatus(bookingToUpdate.get().getStatus());
        return dto;
    }
}