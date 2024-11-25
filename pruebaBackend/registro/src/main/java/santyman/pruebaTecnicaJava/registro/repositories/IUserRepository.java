package santyman.pruebaTecnicaJava.registro.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import santyman.pruebaTecnicaJava.registro.entities.User;

public interface IUserRepository extends JpaRepository<User,Long> {

    
    public User findByEmail(String userEmail);
    
}
