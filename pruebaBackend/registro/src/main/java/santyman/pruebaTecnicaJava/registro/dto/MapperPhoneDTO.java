package santyman.pruebaTecnicaJava.registro.dto;

import org.springframework.stereotype.Component;

import santyman.pruebaTecnicaJava.registro.entities.Phone;

@Component
public class MapperPhoneDTO {
    

    public Phone dtoToEntity(PhoneDTO dto){
        Phone phone = new Phone();
        
        phone.setNumber(dto.getNumber());
        phone.setCityCode(dto.getCityCode());
        phone.setCountryCode(dto.getCountryCode());
        
        return phone;
    }

    public PhoneDTO entityToDto(Phone phone){
        PhoneDTO dto = new PhoneDTO();
        dto.setId(phone.getId());
        dto.setNumber(phone.getNumber());
        dto.setCityCode(phone.getCityCode());
        dto.setCountryCode(phone.getCountryCode());

        return dto;
    }
}
