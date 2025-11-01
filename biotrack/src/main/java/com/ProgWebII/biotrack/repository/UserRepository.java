package com.ProgWebII.biotrack.repository; // Ajuste o pacote

import com.ProgWebII.biotrack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  // Spring Data JPA gera a implementação automaticamente
}