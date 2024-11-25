package com.santyman.gestiontareas.dtos.mapper;

import org.springframework.stereotype.Component;

import com.santyman.gestiontareas.dtos.TareaDTO;
import com.santyman.gestiontareas.dtos.TareaResponseDTO;
import com.santyman.gestiontareas.dtos.TareaUpdateDTO;
import com.santyman.gestiontareas.entities.Estado;
import com.santyman.gestiontareas.entities.Tarea;

@Component
public class GlobalMapper {
    

    public Tarea dtoToEntity(TareaDTO dto){
        Tarea tarea = new Tarea();
        tarea.setTitulo(dto.getTitulo());
        tarea.setDescripcion(dto.getDescripcion());
        tarea.setEstado(Estado.PENDIENTE);
        return tarea;
    }

    public TareaResponseDTO entityToTareaResponseDTO(Tarea tarea){
        TareaResponseDTO dto = new TareaResponseDTO();
        dto.setId(tarea.getId());
        dto.setTitulo(tarea.getTitulo());
        dto.setDescripcion(tarea.getDescripcion());
        dto.setEstado(tarea.getEstado());
        return dto;
    }

    public TareaResponseDTO tareaUpdateToTareaResponseDTO(TareaUpdateDTO dto){
        TareaResponseDTO responseDTO = new TareaResponseDTO(null,dto.getTitulo(), dto.getDescripcion(), dto.getEstado());
        return responseDTO;
    }

}