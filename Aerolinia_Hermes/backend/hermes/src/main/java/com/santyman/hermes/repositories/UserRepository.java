package com.santyman.hermes.repositories;


import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.santyman.hermes.entities.User;

public interface UserRepository extends JpaRepository<User,Long> {
    
   List<User> findAll();

   Optional<User> findByEmail(String email);
}
