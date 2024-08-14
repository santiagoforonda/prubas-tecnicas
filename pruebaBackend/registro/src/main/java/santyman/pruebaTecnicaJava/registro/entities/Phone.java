package santyman.pruebaTecnicaJava.registro.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="phones", uniqueConstraints = {
    @UniqueConstraint(columnNames ={"number_phone"})
} )
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="number_phone")
    private String number;

    private Long cityCode;

    private Long countryCode;

    @ManyToOne(fetch = FetchType.LAZY)
    //Esta etiqueta sirve para resolver el problema de referencia ciclia, pero esta etiqueta va en la entidad dependiente de la relacion
    @JsonBackReference
    @JoinColumn(name = "userID", nullable = false)
    private User user;

    public Phone(){

    }

    public Phone(Long id, String number, Long cityCode, Long countryCode) {
        this.id = id;
        this.number = number;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
}