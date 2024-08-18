package com.santyman.gestiontareas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santyman.gestiontareas.entities.Estado;
import com.santyman.gestiontareas.entities.Tarea;

public interface ITareaRepository extends JpaRepository<Tarea,Long> {
    

    List<Tarea> findByEstado(Estado estado);
}
