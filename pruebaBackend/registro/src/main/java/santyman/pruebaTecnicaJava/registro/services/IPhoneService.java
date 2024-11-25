package santyman.pruebaTecnicaJava.registro.services;


import java.util.List;

import santyman.pruebaTecnicaJava.registro.dto.PhoneDTO;

public interface IPhoneService {
    
    public PhoneDTO createPhone(PhoneDTO phone, Long userId);

    public List<PhoneDTO> showPhones();

    public PhoneDTO getPhoneById(Long id, Long userId);

    public Boolean deletePhone(Long id, Long userId);

}
