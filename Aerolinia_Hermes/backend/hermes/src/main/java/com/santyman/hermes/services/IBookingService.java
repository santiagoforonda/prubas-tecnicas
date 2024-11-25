package com.santyman.hermes.services;

import com.santyman.hermes.dtos.bookingDTO.BookingDto;
import com.santyman.hermes.entities.BookingStatus;

import java.util.List;
import java.util.Set;

public interface IBookingService {

    public BookingDto registerBooking(BookingDto booking, Long idFlight, Long idUser);

    public List<BookingDto> showBookings();
    
    public BookingDto findById(Long id);

    public BookingDto updateBookingDto (Long id, BookingDto bookingUpdate, Long idUser, Long idFlight);

    public Boolean deleteBooking(Long id);

    public List<BookingDto> getBookingByUserCustomer (String email);

    public Set<BookingDto> getBookingsByFligth(Long idFligth);

    public List<BookingDto> getBookingByStatus (BookingStatus bookingStatus);

    public List<BookingDto> getBookingsByUserAndStatus (String email, BookingStatus bookingStatus);


}
