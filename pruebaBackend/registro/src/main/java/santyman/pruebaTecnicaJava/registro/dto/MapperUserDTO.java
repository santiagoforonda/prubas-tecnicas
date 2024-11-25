package santyman.pruebaTecnicaJava.registro.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import santyman.pruebaTecnicaJava.registro.entities.User;

@Component
public class MapperUserDTO {

    @Autowired
    private PasswordEncoder passwordEncoder;


    
    public UserDTO entityToDto(User user){
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setPhones(user.getPhones());
        return dto;
    }

    public User dtoToEntity(UserDTO dto){
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setPhones(dto.getPhones());

        return user;
    }
}
