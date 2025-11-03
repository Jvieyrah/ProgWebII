package com.ProgWebII.biotrack.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record MedidaResponse(
        Long id,
        LocalDateTime measurementDate,
        Double weightKg,
        Double heightCm,
        Double waistCm,
        Double hipCm,
        Double chestCm,
        Double armRightCm,
        Double armLeftCm,
        Double thighRightCm,
        Double thighLeftCm,
        Double bodyFatPercentage
) {}
