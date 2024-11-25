package santyman.pruebaTecnicaJava.registro.services.servicesImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import santyman.pruebaTecnicaJava.registro.dto.MapperUserDTO;
import santyman.pruebaTecnicaJava.registro.dto.UserDTO;
import santyman.pruebaTecnicaJava.registro.entities.User;
import santyman.pruebaTecnicaJava.registro.exceptions.PasswordValidator;
import santyman.pruebaTecnicaJava.registro.exceptions.UserNotFoundException;
import santyman.pruebaTecnicaJava.registro.repositories.IUserRepository;
import santyman.pruebaTecnicaJava.registro.services.IUserService;

@Service
public class UserServiceImpl  implements IUserService{
    

    private final IUserRepository userRepo;

    private final MapperUserDTO maper;

    public UserServiceImpl(IUserRepository userRepo, MapperUserDTO maper) {
    this.userRepo = userRepo;
    this.maper = maper;
}

    @Override
    public UserDTO getUserById(Long id) {
       User user  = userRepo.findById(id).orElseThrow(()-> new UserNotFoundException(id));
       UserDTO userDTOResponse = maper.entityToDto(user);
        return userDTOResponse;
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO user) {
        User userAux = userRepo.findById(id).orElseThrow(()-> new UserNotFoundException(id));
        UserDTO userDTOResponse = maper.entityToDto(userAux);
        userDTOResponse.setName(user.getName());
        userDTOResponse.setEmail(user.getEmail());
        userDTOResponse.setPassword(user.getPassword());
       PasswordValidator.validatePassword(userDTOResponse.getPassword());
    
        return userDTOResponse;
    }


    @Override
    public List<UserDTO> showUsers() {
        List<User> usersAux = userRepo.findAll();
        List<UserDTO> usersDTOResponse = usersAux.stream().map(userAux->maper.entityToDto(userAux)).collect(Collectors.toList());
        return usersDTOResponse;
    }

    @Override
    public Boolean deleteUser(Long id) {
        User userAux= userRepo.findById(id).orElseThrow(()-> new UserNotFoundException(id));
        if(userAux != null){
            userRepo.deleteById(id);
            return true;
        }
        return false;
    }

}