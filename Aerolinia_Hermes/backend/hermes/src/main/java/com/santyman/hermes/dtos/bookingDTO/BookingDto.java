package com.santyman.hermes.dtos.bookingDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.santyman.hermes.entities.BookingStatus;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class BookingDto {
    
    private Long id;

    @NotNull(message = "Campo obligatorio")
    private String paymentToken;
    
   
    private Boolean checkInd;
    
    @NotNull(message = "Campo obligatorio")
    @JsonFormat(shape =JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate createdAt;
    
    @NotNull(message = "Campo obligatorio")
    private String referenceCode;
    
    private BookingStatus status;

    public BookingDto(){

    }

    public BookingDto(Long id, String paymentToken, Boolean checkInd, LocalDate createdAt, String referenceCode,
            BookingStatus status) {
        this.id = id;
        this.paymentToken = paymentToken;
        this.checkInd = checkInd;
        this.createdAt = createdAt;
        this.referenceCode = referenceCode;
        this.status = status;
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

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    
}
