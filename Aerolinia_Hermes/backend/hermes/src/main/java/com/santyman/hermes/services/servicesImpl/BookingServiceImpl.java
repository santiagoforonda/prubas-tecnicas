package com.santyman.hermes.services.servicesImpl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santyman.hermes.dtos.bookingDTO.BookingDto;
import com.santyman.hermes.dtos.bookingDTO.MapprerBookingDto;
import com.santyman.hermes.entities.Booking;
import com.santyman.hermes.entities.BookingStatus;
import com.santyman.hermes.entities.Flight;
import com.santyman.hermes.entities.User;
import com.santyman.hermes.exceptions.customExceptions.BookingNotFoundException;
import com.santyman.hermes.exceptions.customExceptions.FlightNotFoundException;
import com.santyman.hermes.exceptions.customExceptions.UserNotFoundException;
import com.santyman.hermes.exceptions.validators.BookingValidator;
import com.santyman.hermes.repositories.BookingRepository;
import com.santyman.hermes.repositories.FlightRepository;
import com.santyman.hermes.repositories.UserRepository;
import com.santyman.hermes.services.IBookingService;

@Service
public class BookingServiceImpl implements IBookingService {

    @Autowired
    private BookingRepository boookingRepo;

    @Autowired
    private MapprerBookingDto mapeador;

    @Autowired
    private FlightRepository fligthRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BookingValidator validator;

    @Override
    public BookingDto registerBooking(BookingDto booking, Long idFlight, Long idUser) {
        Booking bookingAux = mapeador.dtoToEntity(booking);
        User user = userRepo.findById(idUser).orElseThrow(()-> new UserNotFoundException(idUser));
        Flight fligth = fligthRepo.findById(idFlight).orElseThrow(()-> new FlightNotFoundException(idFlight));
        bookingAux.setUser(user);
        bookingAux.setFlight(fligth);
        bookingAux.setCheckInd(false);
        bookingAux.setStatus(BookingStatus.UNCONFIRMED);
        validator.validBookingExist(bookingAux);
        Booking bookingNew = boookingRepo.save(bookingAux);
        return mapeador.entityToDto(bookingNew);
    }

    @Override
    public BookingDto updateBookingDto(Long id, BookingDto bookingUpdate, Long idUser, Long idFlight) {
       Optional<Booking> bookingInDb = boookingRepo.findById(id);
       User user = userRepo.findById(idFlight).orElseThrow(()-> new UserNotFoundException(idUser));
       Flight flight = fligthRepo.findById(idFlight).orElseThrow(()-> new FlightNotFoundException(idFlight));
       Optional<Booking> bookingToUpdate = bookingInDb.map(oldBooking ->{
            Booking bookingAux = oldBooking.updateWith(mapeador.dtoToEntity(bookingUpdate));
            bookingAux.setUser(user);
            bookingAux.setFlight(flight);
            return boookingRepo.save(bookingAux);
       });
       return mapeador.optionalToDto(bookingToUpdate);
    }

    @Override
    public List<BookingDto> showBookings() {
        List<Booking> boookings = boookingRepo.findAll();
        List<BookingDto> bookingsDto= boookings.stream().map(booking->mapeador.entityToDto(booking)).collect(Collectors.toList());
        return bookingsDto;
    }

    @Override
    public BookingDto findById(Long id) {
        Booking booking = boookingRepo.findById(id).orElseThrow(()-> new BookingNotFoundException(id));
        return mapeador.entityToDto(booking);
    }

  

    @Override
    public Boolean deleteBooking(Long id) {
       Booking bookingIsDelete = boookingRepo.findById(id).orElseThrow(()-> new BookingNotFoundException(id));
       if(bookingIsDelete != null){
            boookingRepo.deleteById(id);
            return true;
       }
       return false;
    }

    @Override
    public List<BookingDto> getBookingByUserCustomer(String email) {
        //Primero hay que validar que el usuario con el email exista.
        userRepo.findByEmail(email).orElseThrow(()-> new UserNotFoundException(email));
        List<Booking> bookings = boookingRepo.finByUser(email);
        List<BookingDto> bookingsDtos= bookings.stream().map(booking -> mapeador.entityToDto(booking)).toList();
        return bookingsDtos;
    }

    @Override
    public List<BookingDto> getBookingByStatus(BookingStatus bookingStatus) {
        List<Booking> bookings = boookingRepo.findByStatus(bookingStatus);
        List<BookingDto> bookingsDtos = bookings.stream().map(booking-> mapeador.entityToDto(booking)).toList();
        return bookingsDtos;
    }

    @Override
    public List<BookingDto> getBookingsByUserAndStatus(String email, BookingStatus bookingStatus) {
        userRepo.findByEmail(email).orElseThrow(()-> new UserNotFoundException(email));
        List<Booking> bookings = boookingRepo.finByUserAndStatus(bookingStatus,email);
        List<BookingDto> bookingsDtos = bookings.stream().map(booking->mapeador.entityToDto(booking)).toList();
        return bookingsDtos;
    }

    @Override
    public Set<BookingDto> getBookingsByFligth(Long idFligth) {
        Set<Booking> bookings = boookingRepo.findByFlight(idFligth);
        Set<BookingDto> bookingsDto = bookings.stream().map(booking-> mapeador.entityToDto(booking)).collect(Collectors.toSet());
        return bookingsDto;
    }
    
}
