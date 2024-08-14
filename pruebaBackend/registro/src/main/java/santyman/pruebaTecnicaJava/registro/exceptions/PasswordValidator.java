package santyman.pruebaTecnicaJava.registro.exceptions;

import org.springframework.stereotype.Component;

@Component
public class PasswordValidator {
    
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    public static void validatePassword(String password){
        if(!password.matches(PASSWORD_PATTERN)){
            throw new InvalidPasswordException();
        }
    }

}