package santyman.pruebaTecnicaJava.registro.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import santyman.pruebaTecnicaJava.registro.dto.PhoneDTO;
import santyman.pruebaTecnicaJava.registro.services.IPhoneService;

@RestController
@RequestMapping("/api/v1/phones")
public class PhoneController {
    

    @Autowired
    private IPhoneService phoneService;



    @PostMapping("/users/{userId}/createPhone")
    public ResponseEntity<PhoneDTO> createPhone( @Valid @RequestBody PhoneDTO phone, @PathVariable Long userId){
        return new ResponseEntity<>(phoneService.createPhone(phone, userId), HttpStatus.ACCEPTED);
    }

    @GetMapping("/showPhones")
    public ResponseEntity<List<PhoneDTO>> showPhones(){
        return new ResponseEntity<>(phoneService.showPhones(), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/getPhone/{id}")
    public ResponseEntity<PhoneDTO> getPhoneById(@PathVariable Long userId,@PathVariable Long id){
        return new ResponseEntity<>(phoneService.getPhoneById(id,userId),HttpStatus.OK);
    }


    @DeleteMapping("/users/{userId}/deletePhone/{id}")
    public ResponseEntity<Boolean> deletePhone(@PathVariable Long userId,@PathVariable Long id ){
        Boolean phoneElimineted = phoneService.deletePhone(id,userId);
        return new ResponseEntity<>(phoneElimineted, HttpStatus.OK);
    }


}