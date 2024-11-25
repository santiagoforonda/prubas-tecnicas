package santyman.pruebaTecnicaJava.registro.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    
    private Long id;

    private String email;


    public UserNotFoundException(Long id){
        super(String.format("Usuario con id " + id +" no fue encontrado"));
        this.id=id;
    }

    public UserNotFoundException(String email){
        super(String.format("El usuario con el email %s no fue encontrado", email));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


   
   
}