package santyman.pruebaTecnicaJava.registro.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidNumberException extends RuntimeException {
    

    public InvalidNumberException(){
        super(String.format("El numero de telefono no es valido, debe tener 10 caracteres"));
    }

}
