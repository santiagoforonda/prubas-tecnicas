package com.santyman.hermes.dtos.userDTO;

import java.util.Set;

import com.santyman.hermes.entities.Booking;

import jakarta.validation.constraints.NotEmpty;

public class UserDto {
    
    private Long id;

    @NotEmpty(message = "El nombre del usuario no puede estar vacio")
    private String name;

    @NotEmpty(message = "El apellido del usuario no puede estar vacio")
    private String lastname;

    @NotEmpty(message = "El email de usuario no puede estar vacio")
    private String email;

    @NotEmpty(message = "El password del usuario no puede estar vacio")
    private String password;

    private Set<Booking> bookings;

    public UserDto(){

    }

    public UserDto(Long id, String name, String lastname, String email, String password) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    
}
