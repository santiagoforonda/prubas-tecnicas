package santyman.pruebaTecnicaJava.registro.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public class PhoneDTO {

    private Long id;
    
    @NotEmpty(message = "El numero de telefono no puede estar vacio")
    private String number;

    @NotNull(message = "El campo no puede estar vacio")
    private Long cityCode;

    @NotNull(message = "El campo no puede estar vacio")
    private Long countryCode;
 
    
    public PhoneDTO(){

    }

    public PhoneDTO(Long id,  String number,Long cityCode, Long countryCode) {
        this.id = id;
        this.number = number;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
    }




    public String getNumber() {
        return number;
    }


    public void setNumber(String number) {
        this.number = number;
    }


    public Long getCityCode() {
        return cityCode;
    }


    public void setCityCode(Long cityCode) {
        this.cityCode = cityCode;
    }


    public Long getCountryCode() {
        return countryCode;
    }


    public void setCountryCode(Long countryCode) {
        this.countryCode = countryCode;
    }




    public Long getId() {
        return id;
    }




    public void setId(Long id) {
        this.id = id;
    }
 
}