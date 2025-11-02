package com.ProgWebII.biotrack.dto;

import java.time.LocalDate;

public record ListarTodosUsuariosResponse(
        Long id,
        String name,
        LocalDate birthDate,
        String zipCode,
        String email
) {}
