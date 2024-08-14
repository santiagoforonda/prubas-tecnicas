package santyman.pruebaTecnicaJava.registro.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import santyman.pruebaTecnicaJava.registro.entities.User;
import santyman.pruebaTecnicaJava.registro.repositories.IUserRepository;

@Component
public class EmailValidator {
    
    private static final String EMAIL_PATTERN = "^[a-z0-9]+(\\.[_a-z0-9]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,15})$";

    @Autowired
    private IUserRepository userRepository;


    public static void validateEmail(String email){
        if(!email.matches(EMAIL_PATTERN)){
            throw new InvalidEmailException();
        }
    }

    public void validExistEmail(String email){
        User userExist = userRepository.findByEmail(email);
        if(userExist!=null){
            throw new UserExistException(email);
        }
    }
}
