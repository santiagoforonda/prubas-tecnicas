package santyman.pruebaTecnicaJava.registro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import santyman.pruebaTecnicaJava.registro.entities.Phone;

public interface IPhoneRepository extends JpaRepository<Phone,Long> {
    
    public List<Phone> findByUserId(Long userId);

    public Phone findByNumber(String numberPhone);
}
