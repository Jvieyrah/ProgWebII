package com.ProgWebII.biotrack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

  /**
   * Define o BCryptPasswordEncoder como o bean padrão para codificação de senha.
   * @return A implementação de PasswordEncoder (BCryptPasswordEncoder).
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    // Usa o fator de força padrão (cost) 10, que é seguro para a maioria dos casos.
    return new BCryptPasswordEncoder();
  }
}