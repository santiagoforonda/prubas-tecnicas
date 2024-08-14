package santyman.pruebaTecnicaJava.registro.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NumberPhoneExistsException extends RuntimeException {

    private String invalidNumberPhone;

    public NumberPhoneExistsException(String invalidNumberPhone){
        super(String.format("El numero de telefono %s ya existe", invalidNumberPhone));
        this.invalidNumberPhone=invalidNumberPhone;
    }

    public String getInvalidNumberPhone() {
        return invalidNumberPhone;
    }

    public void setInvalidNumberPhone(String invalidNumberPhone) {
        this.invalidNumberPhone = invalidNumberPhone;
    }

    
    
}
