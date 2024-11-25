package com.santyman.hermes.exceptions.validators;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.santyman.hermes.entities.Flight;
import com.santyman.hermes.exceptions.customExceptions.FlightExistException;
import com.santyman.hermes.repositories.FlightRepository;

@Component
public class FlightValidator {


    @Autowired
    private FlightRepository fligthRepo;

    public void validExistFlight(int fligthNumber){
        Optional<Flight> flightExist = fligthRepo.findByFlightNumber(fligthNumber);
        if(flightExist.isPresent()){
            throw new FlightExistException(fligthNumber);
        }else{
            return;
        }
    }
}
