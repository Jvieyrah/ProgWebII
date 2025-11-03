package com.ProgWebII.biotrack.service;

import com.ProgWebII.biotrack.dto.MedidaResponse;
import com.ProgWebII.biotrack.model.Measure;
import com.ProgWebII.biotrack.model.User;
import com.ProgWebII.biotrack.repository.MeasureRepository;
import com.ProgWebII.biotrack.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasureService {

    private final MeasureRepository measureRepository;
    private final UserRepository userRepository;

    public MeasureService(MeasureRepository measureRepository, UserRepository userRepository) {
        this.measureRepository = measureRepository;
        this.userRepository = userRepository;
    }

    //Lista todas as medidas de um usuário específico
    public List<MedidaResponse> listarTodasAsMedidasDeUmUsuario(Long idUsuario) {
        User user = userRepository.findById(idUsuario)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        return user.getMeasures().stream()
                .map(this::mapToMedidaResponse)
                .toList();
    }

    //Busca uma medida específica de um usuário.
    public MedidaResponse buscarMedidaPorId(Long idUsuario, Long medidaId) {
        User user = userRepository.findById(idUsuario)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Measure medida = user.getMeasures().stream()
                .filter(m -> m.getId().equals(medidaId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Medida não encontrada para este usuário"));

        return mapToMedidaResponse(medida);
    }

    //Converte entidade Measure → DTO MedidaResponse.
    private MedidaResponse mapToMedidaResponse(Measure m) {
        return new MedidaResponse(
                m.getId(),
                m.getMeasurementDate(),
                m.getWeightKg(),
                m.getHeightCm(),
                m.getWaistCm(),
                m.getHipCm(),
                m.getChestCm(),
                m.getArmRightCm(),
                m.getArmLeftCm(),
                m.getThighRightCm(),
                m.getThighLeftCm(),
                m.getBodyFatPercentage()
        );
    }
}
