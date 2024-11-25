package com.santyman.hermes.dtos.fligthDTO;

import java.util.Set;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.santyman.hermes.entities.Booking;

import jakarta.validation.constraints.NotNull;


public class FlightDto {
    
    private Long id;

    @NotNull(message = "Campo obligatorio")
    @JsonFormat(shape =JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate departureDate;

    @NotNull(message = "Campo obligatorio")
    private String departureAirportCode;

    @NotNull(message = "Campo obligatorio")
    private String departureAirportName;

    @NotNull(message = "Campo obligatorio")
    private String departureCity;

    @NotNull(message = "Campo obligatorio")
    private String departureLocale;

    @NotNull(message = "Campo obligatorio")
    @JsonFormat(shape =JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate arrivalDate;

    @NotNull(message = "Campo obligatorio")
    private String arrivalAirportCode;

    @NotNull(message = "Campo obligatorio")
    private String arrivalAirPortName;

    @NotNull(message = "Campo obligatorio")
    private String arrivalCity;

    @NotNull(message = "Campo obligatorio")
    private String arrivalLocale;

    @NotNull(message = "Campo obligatorio")
    private int ticketPrice;

    @NotNull(message = "Campo obligatorio")
    private String ticketCurrency;
    
    @NotNull(message = "Campo obligatorio")
    private int flightNumber;

    @NotNull(message = "Campo obligatorio")
    private int seatCapacity;

    private Set<Booking> bookings;

    public FlightDto(){

    }

   

    public FlightDto(Long id,  LocalDate departureDate,
             String departureAirportCode,
             String departureAirportName,
            String departureCity,
             String departureLocale,
             LocalDate arrivalDate,
             String arrivalAirportCode,
             String arrivalAirPortName,
             String arrivalCity,
             String arrivalLocale,
            int ticketPrice,
             String ticketCurrency,
             int flightNumber,
             int seatCapacity) {
        this.id = id;
        this.departureDate = departureDate;
        this.departureAirportCode = departureAirportCode;
        this.departureAirportName = departureAirportName;
        this.departureCity = departureCity;
        this.departureLocale = departureLocale;
        this.arrivalDate = arrivalDate;
        this.arrivalAirportCode = arrivalAirportCode;
        this.arrivalAirPortName = arrivalAirPortName;
        this.arrivalCity = arrivalCity;
        this.arrivalLocale = arrivalLocale;
        this.ticketPrice = ticketPrice;
        this.ticketCurrency = ticketCurrency;
        this.flightNumber = flightNumber;
        this.seatCapacity = seatCapacity;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public void setDepartureAirportCode(String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }

    public String getDepartureAirportName() {
        return departureAirportName;
    }

    public void setDepartureAirportName(String departureAirportName) {
        this.departureAirportName = departureAirportName;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getDepartureLocale() {
        return departureLocale;
    }

    public void setDepartureLocale(String departureLocale) {
        this.departureLocale = departureLocale;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getArrivalAirportCode() {
        return arrivalAirportCode;
    }

    public void setArrivalAirportCode(String arrivalAirportCode) {
        this.arrivalAirportCode = arrivalAirportCode;
    }

    public String getArrivalAirPortName() {
        return arrivalAirPortName;
    }

    public void setArrivalAirPortName(String arrivalAirPortName) {
        this.arrivalAirPortName = arrivalAirPortName;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public String getArrivalLocale() {
        return arrivalLocale;
    }

    public void setArrivalLocale(String arrivalLocale) {
        this.arrivalLocale = arrivalLocale;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getTicketCurrency() {
        return ticketCurrency;
    }

    public void setTicketCurrency(String ticketCurrency) {
        this.ticketCurrency = ticketCurrency;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public int getSeatCapacity() {
        return seatCapacity;
    }

    public void setSeatCapacity(int seatCapacity) {
        this.seatCapacity = seatCapacity;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

}