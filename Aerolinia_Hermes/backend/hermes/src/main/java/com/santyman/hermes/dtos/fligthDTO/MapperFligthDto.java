package com.santyman.hermes.dtos.fligthDTO;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.santyman.hermes.entities.Flight;

@Component
public class MapperFligthDto {
    

    //Esto es lo que va resibir el cliente (frontend)
    public FlightDto entityToDto(Flight flight){
        FlightDto dto = new FlightDto();
            dto.setId(flight.getId());
            dto.setArrivalAirportCode(flight.getArrivalAirportCode());
            dto.setArrivalAirPortName(flight.getArrivalAirPortName());
            dto.setArrivalCity(flight.getArrivalCity());
            dto.setArrivalDate(flight.getArrivalDate());
            dto.setArrivalLocale(flight.getArrivalLocale());
            dto.setDepartureAirportCode(flight.getDeaprtureAirportCode());
            dto.setDepartureAirportName(flight.getDepartureAirportName());
            dto.setDepartureCity(flight.getDepartureCity());
            dto.setDepartureLocale(flight.getDepartureLocale());
            dto.setDepartureDate(flight.getDepartureDate());
            dto.setFlightNumber(flight.getFlightNumber());
            dto.setSeatCapacity(flight.getSeatCapacity());
            dto.setTicketCurrency(flight.getTicketCurrency());
            dto.setTicketPrice(flight.getTicketPrice());
            dto.setBookings(flight.getBookings());
            return dto;
    }

    //Esto es lo que va resibir el servidor (la BD)
    public Flight dtoToEntity(FlightDto dto){
        Flight fligthAux= new Flight();
        fligthAux.setArrivalAirPortName(dto.getArrivalAirPortName());
        fligthAux.setArrivalAirportCode(dto.getArrivalAirportCode());
        fligthAux.setArrivalCity(dto.getArrivalCity());
        fligthAux.setArrivalDate(dto.getArrivalDate());
        fligthAux.setArrivalLocale(dto.getArrivalLocale());
        fligthAux.setDeaprtureAirportCode(dto.getDepartureAirportCode());
        fligthAux.setDepartureAirportName(dto.getDepartureAirportName());
        fligthAux.setDepartureCity(dto.getDepartureCity());
        fligthAux.setDepartureDate(dto.getDepartureDate());
        fligthAux.setDepartureLocale(dto.getDepartureLocale());
        fligthAux.setFlightNumber(dto.getFlightNumber());
        fligthAux.setSeatCapacity(dto.getSeatCapacity());
        fligthAux.setTicketCurrency(dto.getTicketCurrency());
        fligthAux.setTicketPrice(dto.getTicketPrice());
        return fligthAux;
    }

    public FlightDto optionalToDto(Optional<Flight> flightToUpdate) {
       FlightDto dto = new FlightDto();
       dto.setId(flightToUpdate.get().getId());
        dto.setArrivalAirportCode(flightToUpdate.get().getArrivalAirportCode());
        dto.setArrivalAirPortName(flightToUpdate.get().getArrivalAirPortName());
        dto.setArrivalCity(flightToUpdate.get().getArrivalCity());
        dto.setArrivalDate(flightToUpdate.get().getArrivalDate());
        dto.setArrivalLocale(flightToUpdate.get().getArrivalLocale());
        dto.setDepartureAirportCode(flightToUpdate.get().getDeparrtureAirportCode());
        dto.setDepartureAirportName(flightToUpdate.get().getDepartureAirportName());
        dto.setDepartureCity(flightToUpdate.get().getDepartureCity());
        dto.setDepartureDate(flightToUpdate.get().getDepartureDate());
        dto.setDepartureLocale(flightToUpdate.get().getDepartureLocale());
        dto.setFlightNumber(flightToUpdate.get().getFlightNumber());
        dto.setSeatCapacity(flightToUpdate.get().getSeatCapacity());
        dto.setTicketCurrency(flightToUpdate.get().getTicketCurrency());
        dto.setTicketPrice(flightToUpdate.get().getTicketPrice());
        dto.setBookings(flightToUpdate.get().getBookings());
        return dto;
    }



}
