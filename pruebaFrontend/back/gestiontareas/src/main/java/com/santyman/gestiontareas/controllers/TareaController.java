package com.santyman.gestiontareas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.santyman.gestiontareas.dtos.TareaDTO;
import com.santyman.gestiontareas.dtos.TareaResponseDTO;
import com.santyman.gestiontareas.dtos.TareaUpdateDTO;
import com.santyman.gestiontareas.services.ITareaService;

@RestController
@RequestMapping("/api/v1/tareas")
@CrossOrigin(origins ="http://localhost:3000")
public class TareaController {

    @Autowired
    private ITareaService tareaservice;


    @PostMapping("/crearTarea")
    public ResponseEntity<TareaResponseDTO> crearTarea(@RequestBody TareaDTO dto){
        TareaResponseDTO responseDTO = tareaservice.crearTarea(dto);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/actualizarTarea/{id}")
    public ResponseEntity<TareaResponseDTO> actualizarTarea(@PathVariable Long id, @RequestBody TareaUpdateDTO dto){
        TareaResponseDTO responseDTO = tareaservice.actualizarTarea(id, dto);
        return new ResponseEntity<>(responseDTO,HttpStatus.ACCEPTED);
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<TareaResponseDTO> buscarTareaPorId(@PathVariable Long id){
        TareaResponseDTO responseDTO = tareaservice.buscarTareaPorId(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    

    @GetMapping("/mostrarTodas")
    public ResponseEntity<List<TareaResponseDTO>> buscarTodas(){
        List<TareaResponseDTO> response = tareaservice.mostrarTodasLasTareas();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/mostrarTareasCompletadas")
    public ResponseEntity<List<TareaResponseDTO>> mostrarTareasCompletadas(){
        List<TareaResponseDTO> response = tareaservice.mostrarTareasCompletadas();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/mostrarTareasIncompletas")
    public ResponseEntity<List<TareaResponseDTO>> mostrarTareasPendientes(){
        List<TareaResponseDTO> response = tareaservice.mostrarTareasPendientes();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/eliminarTarea/{id}")
    public ResponseEntity<Boolean> eliminarTarea(@PathVariable Long id){
        return new ResponseEntity<>(tareaservice.eliminarTarea(id), HttpStatus.OK);
    }
}
