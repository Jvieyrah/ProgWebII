package com.ProgWebII.biotrack.repository; // Certifique-se de que este pacote corresponde ao seu projeto

import com.ProgWebII.biotrack.model.Measures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório para a entidade Measures.
 * Estende JpaRepository para herdar métodos CRUD básicos (save, findById, findAll, delete, etc.).
 */
@Repository
public interface MeasuresRepository extends JpaRepository<Measures, Long> {

  /**
   * Exemplo de método de consulta personalizado.
   * Encontra todas as medidas associadas a um User específico, ordenadas pela data de medição.
   * O Spring Data JPA infere a consulta automaticamente pelo nome do método.
   *
   * @param userId O ID da chave primária do Usuário.
   * @return Uma lista de objetos Measures.
   */
  List<Measures> findByUserIdOrderByMeasurementDateDesc(Long userId);

  /**
   * Encontra a medida mais recente para um usuário específico.
   *
   * @param userId O ID da chave primária do Usuário.
   * @return O objeto Measures mais recente ou null se não for encontrado.
   */
  Measures findTopByUserIdOrderByMeasurementDateDesc(Long userId);
}