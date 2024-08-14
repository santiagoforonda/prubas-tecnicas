package santyman.pruebaTecnicaJava.registro.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PhoneNotFoundException extends RuntimeException {

    private Long id;

    private String numberPhone;

    public PhoneNotFoundException(Long id){
        super(String.format("El telofono con con id " + id +" no existe"));
        this.id=id;
    }

    public PhoneNotFoundException(String numberPhone){
        super(String.format("El telofono con numero de telefono %s no fue encontrado",numberPhone));
        this.numberPhone=numberPhone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }
    
}