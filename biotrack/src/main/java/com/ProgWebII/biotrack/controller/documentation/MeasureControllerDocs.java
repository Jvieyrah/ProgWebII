package com.ProgWebII.biotrack.controller.documentation;

import com.ProgWebII.biotrack.dto.request.MeasureRequest;
import com.ProgWebII.biotrack.dto.response.MedidaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface MeasureControllerDocs {

    @Operation(
            summary = "Cria uma nova medida",
            description = "Cria uma nova medida para o usuário existente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medida criada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados da medida inválidos."),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno ao criar medida.")
    })
    @PostMapping("/{userId}")
    ResponseEntity<String> createMeasure(
            @RequestBody MeasureRequest measureRequest,
            @Parameter(description = "ID do usuário") @PathVariable Long userId
    );

    @Operation(
            summary = "Lista todas as medidas de um usuário",
            description = "Retorna todas as medidas cadastradas para um usuário específico."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medidas encontradas.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MedidaResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    })
    @GetMapping("/{usuarioId}/medidas")
    ResponseEntity<List<MedidaResponse>> listarTodasAsMedidas(
            @Parameter(description = "ID do usuário") @PathVariable Long usuarioId
    );

    @Operation(
            summary = "Busca uma medida específica de um usuário",
            description = "Retorna os dados de uma medida específica do usuário pelo ID da medida."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medida encontrada.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MedidaResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuário ou medida não encontrada.")
    })
    @GetMapping("/{usuarioId}/medidas/{medidaId}")
    ResponseEntity<MedidaResponse> buscarMedidaPorId(
            @Parameter(description = "ID do usuário") @PathVariable Long usuarioId,
            @Parameter(description = "ID da medida") @PathVariable Long medidaId
    );
}
