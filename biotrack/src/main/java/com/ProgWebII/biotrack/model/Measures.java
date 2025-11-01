package com.ProgWebII.biotrack.model; // Ajuste o pacote conforme a sua estrutura

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_measures")
@Data // Gera Getters, Setters, toString, equals e hashCode
@NoArgsConstructor // Construtor sem argumentos
@AllArgsConstructor // Construtor com todos os argumentos
public class Measures {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id; // Chave primária

  @Column(name = "measurement_date", nullable = false)
  private LocalDateTime measurementDate = LocalDateTime.now(); // Data e hora da medição

  // Medidas Comuns
  @Column(nullable = false)
  private Double weightKg; // Peso (kg)

  private Double heightCm; // Altura (cm)

  // Perímetros
  private Double waistCm; // Cintura (cm)
  private Double hipCm;   // Quadril (cm)
  private Double chestCm; // Peito/Tórax (cm)
  private Double armRightCm; // Braço Direito (cm)
  private Double armLeftCm;  // Braço Esquerdo (cm)
  private Double thighRightCm; // Coxa Direita (cm)
  private Double thighLeftCm;  // Coxa Esquerda (cm)

  // Porcentagens (opcional, se calculado)
  private Double bodyFatPercentage; // Percentual de Gordura Corporal

  // Relacionamento Many-to-One: Muitas Medidas pertencem a um Usuário.
  // Este campo 'user' é a chave estrangeira (FK) que referencia o Usuário.
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false) // Nome da coluna FK no banco de dados
  private User user;
}