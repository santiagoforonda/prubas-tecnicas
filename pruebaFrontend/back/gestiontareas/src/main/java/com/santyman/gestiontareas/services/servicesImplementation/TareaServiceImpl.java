package com.santyman.gestiontareas.services.servicesImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santyman.gestiontareas.dtos.TareaDTO;
import com.santyman.gestiontareas.dtos.TareaResponseDTO;
import com.santyman.gestiontareas.dtos.TareaUpdateDTO;
import com.santyman.gestiontareas.dtos.mapper.GlobalMapper;
import com.santyman.gestiontareas.entities.Estado;
import com.santyman.gestiontareas.entities.Tarea;
import com.santyman.gestiontareas.excepciones.TareaNotFoundException;
import com.santyman.gestiontareas.repositories.ITareaRepository;
import com.santyman.gestiontareas.services.ITareaService;

@Service
public class TareaServiceImpl  implements ITareaService{

    @Autowired
     private ITareaRepository tareaRepo;

    @Autowired
    private GlobalMapper mapper;

    @Override
    public TareaResponseDTO crearTarea(TareaDTO dto) {
        Tarea  tarea  = tareaRepo.save(mapper.dtoToEntity(dto));
        return mapper.entityToTareaResponseDTO(tarea);
    }

    @Override
    public TareaResponseDTO actualizarTarea(Long id, TareaUpdateDTO dto) {
       Tarea tarea = tareaRepo.findById(id).orElseThrow(()-> new TareaNotFoundException(id));
       tarea.setTitulo(dto.getTitulo());
       tarea.setDescripcion(dto.getDescripcion());
       tarea.setEstado(dto.getEstado());
       tareaRepo.save(tarea);
       return mapper.entityToTareaResponseDTO(tarea);
    }

    @Override
    public TareaResponseDTO buscarTareaPorId(Long id) {
        Tarea tarea = tareaRepo.findById(id).orElseThrow(()-> new TareaNotFoundException(id));
        return mapper.entityToTareaResponseDTO(tarea);
    }

    @Override
    public List<TareaResponseDTO> mostrarTodasLasTareas() {
        List<Tarea> tareas = tareaRepo.findAll();
        List<TareaResponseDTO> dtos = tareas.stream().map(tarea -> mapper.entityToTareaResponseDTO(tarea)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public List<TareaResponseDTO> mostrarTareasCompletadas() {
        List<Tarea> tareas = tareaRepo.findByEstado(Estado.COMPLETADA);
        List<TareaResponseDTO> dtos = tareas.stream().map(tarea -> mapper.entityToTareaResponseDTO(tarea)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public List<TareaResponseDTO> mostrarTareasPendientes() {
        List<Tarea> tareas = tareaRepo.findByEstado(Estado.PENDIENTE);
        List<TareaResponseDTO> dtos = tareas.stream().map(tarea -> mapper.entityToTareaResponseDTO(tarea)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public Boolean eliminarTarea(Long id) {
        if(tareaRepo.existsById(id)){
            tareaRepo.deleteById(id);
            return true;
        }
        return false;
    }
    
}