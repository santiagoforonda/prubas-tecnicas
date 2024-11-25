package santyman.pruebaTecnicaJava.registro.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import santyman.pruebaTecnicaJava.registro.entities.Phone;
import santyman.pruebaTecnicaJava.registro.repositories.IPhoneRepository;

@Component
public class NumberPhoneValidator {
    
    @Autowired
    private IPhoneRepository phoneRepo;

    public void validExistPhone(String numberPhone){
        Phone phoneExist = phoneRepo.findByNumber(numberPhone);
        if(phoneExist!=null){
            throw new NumberPhoneExistsException(numberPhone);
        }
    }

    public static void validlengthNumberPhone(String numberPhone){
        if(numberPhone.length()!=10){
            throw new InvalidNumberException();
        }
    }
}
