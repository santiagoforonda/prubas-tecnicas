package com.santyman.gestiontareas.excepciones;

import java.util.Date;

public class ErrorDetails {
    
    private Date date;

    private String message;

    private String detalles;

    public ErrorDetails(Date date, String message, String detalles) {
        this.date = date;
        this.message = message;
        this.detalles = detalles;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    
}
