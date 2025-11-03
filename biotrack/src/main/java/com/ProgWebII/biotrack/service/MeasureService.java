package com.ProgWebII.biotrack.service;

import com.ProgWebII.biotrack.dto.request.MeasureRequest;
import com.ProgWebII.biotrack.model.Measure;
import com.ProgWebII.biotrack.model.User;
import com.ProgWebII.biotrack.repository.MeasureRepository;
import com.ProgWebII.biotrack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeasureService {
  @Autowired
  private MeasureRepository measureRepository;
  @Autowired
  private UserRepository userRepository;

  public void CreateMeasure(MeasureRequest measureRequest, Long userId) {
    try {
      User user = userRepository.findById(userId)
          .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + userId));
      Measure measure = Measure.builder()
          .user(user)
          .weightKg(measureRequest.weightKg())
          .heightCm(measureRequest.heightCm())
          .measurementDate(measureRequest.measurementDate())
          .build();
      measureRepository.save(measure);
    } catch (Exception e) {
      System.err.println("Erro ao criar medida: " + e.getMessage());
      throw new RuntimeException("Falha ao processar a criação da medida.");
    }
  }
}
