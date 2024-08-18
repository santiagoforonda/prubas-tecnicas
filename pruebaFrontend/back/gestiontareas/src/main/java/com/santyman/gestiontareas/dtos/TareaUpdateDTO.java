package com.santyman.gestiontareas.dtos;

import com.santyman.gestiontareas.entities.Estado;

public class TareaUpdateDTO {
    
    private String titulo;

    private String descripcion;

    private Estado estado;

    public TareaUpdateDTO(String titulo, String descripcion, Estado estado) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    
}
