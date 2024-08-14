package santyman.pruebaTecnicaJava.registro.services.servicesImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import santyman.pruebaTecnicaJava.registro.dto.MapperPhoneDTO;
import santyman.pruebaTecnicaJava.registro.dto.PhoneDTO;
import santyman.pruebaTecnicaJava.registro.entities.Phone;
import santyman.pruebaTecnicaJava.registro.entities.User;
import santyman.pruebaTecnicaJava.registro.exceptions.NumberPhoneValidator;
import santyman.pruebaTecnicaJava.registro.exceptions.PhoneNotFoundException;
import santyman.pruebaTecnicaJava.registro.exceptions.UserNotFoundException;
import santyman.pruebaTecnicaJava.registro.repositories.IPhoneRepository;
import santyman.pruebaTecnicaJava.registro.repositories.IUserRepository;
import santyman.pruebaTecnicaJava.registro.services.IPhoneService;

@Service
public class PhoneServiceimpl implements IPhoneService {


    @Autowired
    private IUserRepository userRepo;

    @Autowired
    private IPhoneRepository phoneRepo;

    @Autowired
    private MapperPhoneDTO mapper;

    @Autowired
    private NumberPhoneValidator numberPhoneValidator;

    @Override
    public PhoneDTO createPhone(PhoneDTO phone, Long userId) {
        NumberPhoneValidator.validlengthNumberPhone(phone.getNumber());
        numberPhoneValidator.validExistPhone(phone.getNumber());
        Phone phoneAux = mapper.dtoToEntity(phone);
        User user = userRepo.findById(userId).orElseThrow(()-> new UserNotFoundException(userId));
        phoneAux.setUser(user);
        Phone phoneNew = phoneRepo.save(phoneAux);
        return mapper.entityToDto(phoneNew);
    }

    @Override
    public List<PhoneDTO> showPhones() {
        List<Phone> phonesAux = phoneRepo.findAll();
        List<PhoneDTO> phonesDTOResponse = phonesAux.stream().map(phoneAux -> mapper.entityToDto(phoneAux)).collect(Collectors.toList());

        return phonesDTOResponse;
    }

    @Override
    public PhoneDTO getPhoneById(Long id, Long userId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new UserNotFoundException(userId));

        Phone phone = phoneRepo.findById(id).orElseThrow(()-> new PhoneNotFoundException(id));

        if(!phone.getUser().getId().equals(user.getId())){
            throw new PhoneNotFoundException(id);
        }
        return mapper.entityToDto(phone);
    }

    @Override
    public Boolean deletePhone(Long id, Long userId) {
        
        User user = userRepo.findById(userId).orElseThrow(()-> new UserNotFoundException(userId));

        Phone phone = phoneRepo.findById(id).orElseThrow(()-> new PhoneNotFoundException(id));

        if(!phone.getUser().getId().equals(user.getId())){
            throw new PhoneNotFoundException(id);
        }else{
            phoneRepo.deleteById(id);
            return true;
        }
        
    }
     
}