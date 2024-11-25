package com.santyman.hermes.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.santyman.hermes.dtos.fligthDTO.FlightDto;
import com.santyman.hermes.services.IFlightService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/catalog")
public class FlightController {
    
    @Autowired
    private IFlightService flightService;

    @PostMapping("/flights")
    public ResponseEntity<FlightDto> createFlight(@RequestBody @Valid FlightDto flight){
        return new ResponseEntity<FlightDto>(flightService.registerFlight(flight), HttpStatus.ACCEPTED);
    }

    @GetMapping("/flights/")
    public ResponseEntity<List<FlightDto>> getFlightsByquery(@RequestParam("departureAirPortCode") String departureAirPortCode, @RequestParam("arrivalAirPortCode") String arrivalAirPortCode, @RequestParam("departureDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate departureDate){
        List<FlightDto> flights = flightService.flightsByAirportsAndDate(departureAirPortCode, arrivalAirPortCode, departureDate);
        return ResponseEntity.ok().body(flights);
    }

   

    @PutMapping("/flight/{id}")
    public ResponseEntity<FlightDto> updateFlight(@PathVariable Long id, @RequestBody @Valid FlightDto flightDto){
        FlightDto flightDtoResponse = flightService.updateFlight(id, flightDto);
        return new ResponseEntity<FlightDto>(flightDtoResponse, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/flight/{id}")
    public ResponseEntity<Boolean> deleteFligth(@PathVariable Long id){
        Boolean fligthIsDelete = flightService.deleteFlight(id);
        return ResponseEntity.ok().body(fligthIsDelete);
    }

    @GetMapping("/fligth/{airPortCode}/")
    public ResponseEntity<List<FlightDto>> getFligthByAirPortAndDate(@PathVariable String airPortCode, @RequestParam("departureDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate departureDate){
        List<FlightDto> fligthsResponse = flightService.flightsByDateAndAirport(departureDate, airPortCode);
        return ResponseEntity.ok(fligthsResponse);
    }

    @GetMapping("/flightsAll")
    public ResponseEntity<List<FlightDto>> getFlights(){
        List<FlightDto> fightsDtos = flightService.findAllFlights();
        return ResponseEntity.ok().body(fightsDtos);
    }
}