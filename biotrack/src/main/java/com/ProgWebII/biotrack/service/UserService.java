package com.ProgWebII.biotrack.service;

import com.ProgWebII.biotrack.model.User;
import com.ProgWebII.biotrack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ProgWebII.biotrack.dto.request.UserRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public void createUser(UserRequest userRequest) {
    try{
      User user = User.builder()
          .name(userRequest.name())
          .birthDate(userRequest.birthDate())
          .zipCode(userRequest.zipCode())
          .email(userRequest.email())
          .password(hashPassword(userRequest.password())) // O hash aqui
          .build(); // Finaliza a construção do objeto
    userRepository.save(user);
    } catch (Exception e) {
      System.err.println("Erro ao criar usuário: " + e.getMessage());
      throw new RuntimeException("Falha ao processar a criação do usuário.");
    }
  }
  private String hashPassword(String password) {
    return passwordEncoder.encode(password);
  }
}
