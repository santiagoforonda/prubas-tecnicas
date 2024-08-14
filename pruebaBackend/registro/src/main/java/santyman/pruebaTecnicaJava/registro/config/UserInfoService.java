package santyman.pruebaTecnicaJava.registro.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import santyman.pruebaTecnicaJava.registro.dto.MapperUserDTO;
import santyman.pruebaTecnicaJava.registro.dto.UserDTO;
import santyman.pruebaTecnicaJava.registro.entities.User;
import santyman.pruebaTecnicaJava.registro.exceptions.EmailValidator;
import santyman.pruebaTecnicaJava.registro.exceptions.PasswordValidator;
import santyman.pruebaTecnicaJava.registro.exceptions.UserNotFoundException;
import santyman.pruebaTecnicaJava.registro.repositories.IUserRepository;

@Service
public class UserInfoService implements UserDetailsService {


    private final IUserRepository userRepo;

    @Autowired
    private MapperUserDTO userMapper;

    @Autowired
    private EmailValidator emailValidator;

    public UserInfoService(IUserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public UserDTO addUser(UserDTO userDTO){
        emailValidator.validExistEmail(userDTO.getEmail());
        EmailValidator.validateEmail(userDTO.getEmail());
        PasswordValidator.validatePassword(userDTO.getPassword());
        User userAux = userRepo.save(userMapper.dtoToEntity(userDTO));
        return userMapper.entityToDto(userAux);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UserNotFoundException {
        User user = userRepo.findByEmail(email);
        UserInfoDetails userDetail = new UserInfoDetails(user);
        return userDetail;
    }

    
}
