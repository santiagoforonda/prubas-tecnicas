package santyman.pruebaTecnicaJava.registro.dto;

import java.util.Set;

import jakarta.validation.constraints.NotEmpty;

import santyman.pruebaTecnicaJava.registro.entities.Phone;

public class UserDTO {
    
    private Long id;

    @NotEmpty(message = "El nombre no puede estar vacio")
    private String name;

    @NotEmpty(message = "El campo no puede estar vacio")
    private String email;

    @NotEmpty(message = "El campo no puede estar vacio")
    private String password;

    private Set<Phone> phones;

    public UserDTO(){

    }

    public UserDTO(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password=password;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Set<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
}
