package com.santyman.gestiontareas.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TareaNotFoundException extends RuntimeException {
    
    private Long idNotFound;


    public TareaNotFoundException(Long idNotFound){
        super(String.format("La tarea con el ID "+ idNotFound + "No fue encontrada"));
        this.idNotFound=idNotFound;
    }


    public Long getIdNotFound() {
        return idNotFound;
    }


    public void setIdNotFound(Long idNotFound) {
        this.idNotFound = idNotFound;
    }

    
}
