package santyman.pruebaTecnicaJava.registro.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import santyman.pruebaTecnicaJava.registro.dto.UserDTO;
import santyman.pruebaTecnicaJava.registro.services.IUserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService){
        this.userService=userService;
    }


    @GetMapping("/showUser")
    public ResponseEntity<List<UserDTO>> showUsers(){
        return new ResponseEntity<>(userService.showUsers(),HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/userUpdate/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        UserDTO userDTOResponse = userService.updateUser(id, userDTO);
        return new ResponseEntity<>(userDTOResponse, HttpStatus.OK);
    }

    @DeleteMapping("/userDelete/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long id){
        Boolean userElimnated = userService.deleteUser(id);
        return new ResponseEntity<>(userElimnated, HttpStatus.OK);
    }
}