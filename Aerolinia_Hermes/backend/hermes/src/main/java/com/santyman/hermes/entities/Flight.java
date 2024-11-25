package com.santyman.hermes.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "flights", uniqueConstraints = {
    @UniqueConstraint(columnNames={"flightNumber"})
})
public class Flight {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate departureDate;

    @Column(name="departureAirportCode")
    private String departureAirportCode;

    
    private String departureAirportName;

    private String departureCity;

    private String departureLocale;

    @JsonFormat(shape =JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate arrivalDate;

    @Column(name="arrivalAirportCode")
    private String arrivalAirportCode;

    private String arrivalAirPortName;

    private String arrivalCity;

    private String arrivalLocale;

    private int ticketPrice;

    private String ticketCurrency;
    
    @Column(name="flightNumber")
    private int flightNumber;

    private int seatCapacity;

    @JsonManagedReference("flight-booking")
    @OneToMany(mappedBy = "flight", cascade=CascadeType.ALL, orphanRemoval = true)
    private Set<Booking> bookings = new HashSet<>();

    public Flight(){

    }

    public Flight(Long id, LocalDate departureDate, String departureAirportCode, String departureAirportName,
            String departureCity, String departureLocale, LocalDate arrivalDate, String arrivalAirportCode,
            String arrivalAirPortName, String arrivalCity, String arrivalLocale, int ticketPrice, String ticketCurrency,
            int flightNumber, int seatCapacity) {
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

    public String getDeaprtureAirportCode() {
        return departureAirportCode;
    }

    public void setDeaprtureAirportCode(String deaprtureAirportCode) {
        this.departureAirportCode = deaprtureAirportCode;
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

    public String getDeparrtureAirportCode() {
        return departureAirportCode;
    }

    public void setDeparrtureAirportCode(String deparrtureAirportCode) {
        this.departureAirportCode = deparrtureAirportCode;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }


    public Flight updateWith(Flight flight) {
        return new Flight(this.id, flight.getDepartureDate(), 
        flight.getDeparrtureAirportCode(), 
        flight.getDepartureAirportName(), 
        flight.getDepartureCity(), 
        flight.getDepartureLocale(), 
        flight.getArrivalDate(), 
        flight.getArrivalAirportCode(), 
        flight.getArrivalAirPortName(), 
        flight.getArrivalCity(), 
        flight.getArrivalLocale(), 
        flight.getTicketPrice(), 
        flight.getTicketCurrency(), 
        flight.getFlightNumber(), 
        flight.getSeatCapacity());
    }
    
}