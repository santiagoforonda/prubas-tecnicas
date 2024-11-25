package santyman.pruebaTecnicaJava.registro.services;

import java.util.List;

import santyman.pruebaTecnicaJava.registro.dto.UserDTO;


public interface IUserService {
    
    public UserDTO getUserById(Long id);

    public UserDTO updateUser(Long id, UserDTO user);

    public List<UserDTO> showUsers();

    public Boolean deleteUser(Long id);
}
