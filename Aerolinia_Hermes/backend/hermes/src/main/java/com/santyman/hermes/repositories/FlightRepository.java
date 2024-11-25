package com.santyman.hermes.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;

import com.santyman.hermes.entities.Flight;

public interface FlightRepository extends JpaRepository<Flight,Long> {
    
    List<Flight> findAll();

    Optional<Flight> findByFlightNumber(int fligthNumber);

    @Query("SELECT flight FROM Flight flight WHERE flight.departureAirportCode = ?1 AND flight.arrivalAirportCode = ?2 AND flight.departureDate = ?3")
    public List<Flight> findByDepartureAirportCodeAndArrivalAirportCodeAndDepartureDate(String departureAirportCode, String arrivalAirportCode, @DateTimeFormat(pattern="dd-MM-yyyy") LocalDate departureDate);
    
    @Query("SELECT flight FROM Flight flight WHERE flight.departureDate = ?1 AND flight.departureAirportCode = ?2")
    public List<Flight> findByDepartureDateAndDepartureAirportCode(@DateTimeFormat(pattern="dd-MM-yyyy") LocalDate departureDate, String departureAirportCode);
}
