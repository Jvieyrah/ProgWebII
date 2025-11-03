package com.ProgWebII.biotrack.dto;

import java.time.LocalDate;

public record UsuarioSemMedidasResponse(
        Long id,
        String name,
        LocalDate birthDate,
        String zipCode,
        String email
) {}
