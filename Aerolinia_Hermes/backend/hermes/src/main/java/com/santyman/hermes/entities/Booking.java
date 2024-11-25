package com.santyman.hermes.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name ="bookings", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"paymentToken","referenceCode"})
})
public class Booking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paymentToken")
    private String paymentToken;

    private Boolean checkInd;

    @JsonFormat(shape =JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    @Column(name="referenceCode")
    private String referenceCode;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="flight_id", nullable = false)
    @JsonBackReference("flight-booking")
    private Flight flight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    @JsonBackReference("user-booking")
    private User user;

    public Booking(){
        
    }

    public Booking(Long id, String paymentToken, Boolean checkInd, LocalDate createdAt, String referenceCode,BookingStatus status) {
        this.id = id;
        this.paymentToken = paymentToken;
        this.checkInd = checkInd;
        this.createdAt = createdAt;
        this.referenceCode = referenceCode;
        this.status=status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaymentToken() {
        return paymentToken;
    }

    public void setPaymentToken(String paymentToken) {
        this.paymentToken = paymentToken;
    }

    public Boolean getCheckInd() {
        return checkInd;
    }

    public void setCheckInd(Boolean checkInd) {
        this.checkInd = checkInd;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public String getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }


    public Booking updateWith(Booking bookingUpdate){
        return new Booking(this.id, bookingUpdate.getPaymentToken(),bookingUpdate.getCheckInd(),bookingUpdate.getCreatedAt(),bookingUpdate.getReferenceCode(), bookingUpdate.getStatus());
    }
    
}