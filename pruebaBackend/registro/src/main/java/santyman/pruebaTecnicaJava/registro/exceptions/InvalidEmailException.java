package santyman.pruebaTecnicaJava.registro.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidEmailException extends RuntimeException {
    
    
    public InvalidEmailException(){
        super(String.format("El email no es un email valido "));
    }

    
}

