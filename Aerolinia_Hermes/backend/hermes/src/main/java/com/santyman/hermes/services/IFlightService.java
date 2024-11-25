package com.santyman.hermes.services;

import java.util.List;
import java.time.LocalDate;
import com.santyman.hermes.dtos.fligthDTO.FlightDto;


public interface IFlightService {
    
    public FlightDto registerFlight(FlightDto flight);

    public FlightDto updateFlight(Long id,FlightDto flightUpdate);

    public List<FlightDto> showFlights();

    public Boolean deleteFlight(Long id);

    public List<FlightDto> flightsByAirportsAndDate(String airportCode, String arrivalAirportCode, LocalDate departureDate);

    public List<FlightDto> flightsByDateAndAirport(LocalDate date, String airportCode);

    public List<FlightDto> findAllFlights();
}
