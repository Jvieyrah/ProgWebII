package com.ProgWebII.biotrack.controller.documentation;

import com.ProgWebII.biotrack.dto.request.UserRequest;
import com.ProgWebII.biotrack.dto.response.BuscarUsuarioPorIdResponse;
import com.ProgWebII.biotrack.dto.response.ListarTodosUsuariosResponse;
import com.ProgWebII.biotrack.dto.response.UsuarioResponse;
import com.ProgWebII.biotrack.dto.response.UsuarioSemMedidasResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UsuarioControllerDocs {

    @Operation(summary = "Cria um usuário", description = "Cria um novo usuário no sistema com as informações fornecidas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos."),
            @ApiResponse(responseCode = "500", description = "Erro interno ao criar usuário.")
    })
    @PostMapping
    ResponseEntity<String> criarUsuario(@RequestBody UserRequest userRequest);

    @Operation(summary = "Filtra usuários pela faixa de IMC", description = "Retorna os usuários pertencentes à faixa de IMC informada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuários filtrada por IMC.")
    })
    @GetMapping("/filtro-imc")
    ResponseEntity<List<UsuarioResponse>> filtrarUsuariosPorImc(@Parameter(description = "Faixa do IMC para filtro") @RequestParam String faixa);

    @Operation(summary = "Lista todos os usuários sem medidas.", description = "Retorna uma lista de todos os usuários cadastrados sem suas medidas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuários.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ListarTodosUsuariosResponse.class)))
    })
    @GetMapping
    ResponseEntity<List<ListarTodosUsuariosResponse>> listarTodos();

    @Operation(summary = "Busca usuário por ID (sem medidas)", description = "Busca um usuário pelo seu ID e retorna apenas seus dados cadastrais.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado."),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    })
    @GetMapping("/{id}")
    ResponseEntity<BuscarUsuarioPorIdResponse> buscarPorId(@Parameter(description = "ID do usuário") @PathVariable Long id);

    @Operation(summary = "Lista usuários sem medidas", description = "Retorna todos os usuários, sem trazer suas medições.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuários sem medidas.")
    })
    @GetMapping("/sem-medidas")
    ResponseEntity<List<UsuarioSemMedidasResponse>> listarTodosSemMedidas();

    @Operation(summary = "Busca usuário + todas medidas", description = "Busca usuário por ID e retorna todos os dados de medições associados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário e medidas encontrados.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    })
    @GetMapping("/{id}/todas-medidas")
    ResponseEntity<UsuarioResponse> trazerUsuarioComTodasMedidas(@Parameter(description = "ID do usuário") @PathVariable Long id);

    @Operation(summary = "Busca usuário + última medida", description = "Busca usuário por ID e retorna os dados da última medição.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário e última medida encontrada."),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    })
    @GetMapping("/{id}/ultima-medida")
    ResponseEntity<UsuarioResponse> trazerUsuarioComUltimaMedida(@Parameter(description = "ID do usuário") @PathVariable Long id);
}
