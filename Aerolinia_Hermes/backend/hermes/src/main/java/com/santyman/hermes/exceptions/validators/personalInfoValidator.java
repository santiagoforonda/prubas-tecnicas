package com.santyman.hermes.exceptions.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import com.santyman.hermes.entities.User;
import com.santyman.hermes.exceptions.customExceptions.InvalidEmailException;
import com.santyman.hermes.exceptions.customExceptions.InvalidPasswordException;
import com.santyman.hermes.exceptions.customExceptions.PersonalLastNameException;
import com.santyman.hermes.exceptions.customExceptions.PersonalNamesException;
import com.santyman.hermes.exceptions.customExceptions.UserExistException;
import com.santyman.hermes.repositories.UserRepository;

@Component
public class personalInfoValidator {
    
    private static final String PASSWORD_PATTERN ="^(?=.*[A-Z])(?=.*[Q$!%*?&])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d@$!%*?&]{8,}$";

    private static final String NAME_PATTERN="^[a-zA-ZáéíóúÁÉÍÓÚñÑ]{1,25}$";

    private static final String LASTNAME_PATTERN="^[a-zA-ZáéíóúÁÉÍÓÚñÑ]{1,30}$";

    private static final String EMAIL_PATTERN = "^[a-z0-9]+(\\.[a-z0-9]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,50})$";


    @Autowired
    private UserRepository userRepo;

    public static void validateEmail(String email){
        if(!email.matches(EMAIL_PATTERN)){
            throw new InvalidEmailException();
        }
    }

    public void validExistEmail(String email){
        Optional<User> userOptional= userRepo.findByEmail(email);
        if(userOptional.isPresent()){
            throw new UserExistException(email);
        }
    }

    public static void validatePassword(String password){
        if(!password.matches(PASSWORD_PATTERN)){
            throw new InvalidPasswordException();
        }
    }
    public static void validatePersonalName(String name){
        if(!name.matches(NAME_PATTERN)){
            throw new PersonalNamesException(name);
        }else{
            return;
        }
    }

    public static void validatePersonalLastname(String lastname){
        if(!lastname.matches(LASTNAME_PATTERN)){
            throw new PersonalLastNameException(lastname);
        }else{
            return;
        }
    }
}