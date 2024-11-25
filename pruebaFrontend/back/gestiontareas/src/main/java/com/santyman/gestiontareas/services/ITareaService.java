package com.santyman.gestiontareas.services;

import java.util.List;

import com.santyman.gestiontareas.dtos.TareaDTO;
import com.santyman.gestiontareas.dtos.TareaResponseDTO;
import com.santyman.gestiontareas.dtos.TareaUpdateDTO;

public interface ITareaService {
    

    public TareaResponseDTO crearTarea(TareaDTO dto);

    public TareaResponseDTO actualizarTarea(Long id, TareaUpdateDTO dto);

    public TareaResponseDTO buscarTareaPorId(Long id);

    public List<TareaResponseDTO> mostrarTodasLasTareas();

    public List<TareaResponseDTO> mostrarTareasCompletadas();

    public List<TareaResponseDTO> mostrarTareasPendientes();

    public Boolean eliminarTarea(Long id);

}
