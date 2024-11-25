package com.santyman.hermes.services.servicesImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santyman.hermes.dtos.fligthDTO.FlightDto;
import com.santyman.hermes.dtos.fligthDTO.MapperFligthDto;
import com.santyman.hermes.entities.Booking;
import com.santyman.hermes.entities.Flight;
import com.santyman.hermes.exceptions.customExceptions.FlightNotFoundException;
import com.santyman.hermes.exceptions.validators.FlightValidator;
import com.santyman.hermes.repositories.BookingRepository;
import com.santyman.hermes.repositories.FlightRepository;
import com.santyman.hermes.services.IFlightService;

@Service
public class FligthServiceImpl implements IFlightService{

    @Autowired
    private FlightRepository flightRepo;

    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private MapperFligthDto mapeador;

    @Autowired
    private FlightValidator validator;

    @Override
    public FlightDto registerFlight(FlightDto flight) {
        Flight vuelo  = mapeador.dtoToEntity(flight);
        validator.validExistFlight(flight.getFlightNumber());
        FlightDto vueloDto = mapeador.entityToDto(flightRepo.save(vuelo));
        return vueloDto;
    }

    @Override
    public FlightDto updateFlight(Long id,FlightDto flighUpdate) {
        Set<Booking> bookings = bookingRepo.findByFlight(id);
       Optional<Flight> flightInDb = flightRepo.findById(id);
        Optional<Flight> flightToUpdate = flightInDb.map(oldFlight->{
            Flight fligthAux = oldFlight.updateWith(mapeador.dtoToEntity(flighUpdate));
            validator.validExistFlight(fligthAux.getFlightNumber());
            fligthAux.setBookings(bookings);
            return flightRepo.save(fligthAux);
        });
        return mapeador.optionalToDto(flightToUpdate);
    }

    @Override
    public List<FlightDto> showFlights() {
        List<Flight> vuelos = flightRepo.findAll();
        List<FlightDto> vuelosDto = vuelos.stream().map(vuelo -> mapeador.entityToDto(vuelo)).collect(Collectors.toList());
        return vuelosDto;
    }

    @Override
    public Boolean deleteFlight(Long id) {
        Flight flightIsDelete = flightRepo.findById(id).orElseThrow(()-> new FlightNotFoundException(id));
        if(flightIsDelete != null){
            flightRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<FlightDto> flightsByAirportsAndDate(String airportCode, String arrivalAirportCode,
            LocalDate departureDate) {
        
                List<Flight> vuelos = flightRepo.findByDepartureAirportCodeAndArrivalAirportCodeAndDepartureDate(airportCode, arrivalAirportCode, departureDate);
                List<FlightDto> vuelosDtos = vuelos.stream().map(vuelo -> mapeador.entityToDto(vuelo)).collect(Collectors.toList());
                return vuelosDtos;
    }

    @Override
    public List<FlightDto> flightsByDateAndAirport(LocalDate date, String airportCode) {
      List<Flight> vuelos = flightRepo.findByDepartureDateAndDepartureAirportCode(date, airportCode);
      List<FlightDto> vuelosDtos = vuelos.stream().map(vuelo->mapeador.entityToDto(vuelo)).toList();
      return vuelosDtos;
    }

    @Override
    public List<FlightDto> findAllFlights() {
       List<Flight> vuelos = flightRepo.findAll();
       List<FlightDto> vuelosDtos = vuelos.stream().map(vuelo-> mapeador.entityToDto(vuelo)).toList();
       return vuelosDtos;
    }
    
}
