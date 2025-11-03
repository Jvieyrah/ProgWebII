package com.ProgWebII.biotrack.controller;

import com.ProgWebII.biotrack.dto.BuscarUsuarioPorIdResponse;
import com.ProgWebII.biotrack.dto.ListarTodosUsuariosResponse;
import com.ProgWebII.biotrack.dto.UsuarioResponse;
import com.ProgWebII.biotrack.dto.UsuarioSemMedidasResponse;
import com.ProgWebII.biotrack.model.User;
import com.ProgWebII.biotrack.model.Imc;
import com.ProgWebII.biotrack.repository.UserRepository;
import com.ProgWebII.biotrack.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/usuarios")/*
mapeamento de rota base (ou endpoint base) de um controller REST no Spring Boot.*/
public class UsuarioController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final Imc imc;

    public UsuarioController(UserRepository userRepository, UserService userService, Imc imc) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.imc = imc;
    }

    @GetMapping("/filtro-imc")
    public ResponseEntity<List<User>> filtrarUsuariosPorImc(@RequestParam String faixa) {
        List<User> todosUsuarios = userRepository.findAll();

        List<User> usuariosFiltrados = todosUsuarios.stream()
                .filter(user -> {
                    Double imcUsuario = imc.obterImcUsuario(user.getId());
                    if (imcUsuario == null) {
                        return false;
                    }
                    String faixaUsuario = imc.classificarFaixaImc(imcUsuario);
                    return faixaUsuario != null && faixaUsuario.equalsIgnoreCase(faixa);
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(usuariosFiltrados);
    }

    //GET /usuarios → lista todos (sem medidas)
    @GetMapping
    public ResponseEntity<List<ListarTodosUsuariosResponse>> listarTodos() {
        return ResponseEntity.ok(userService.listarTodos());
    }

    //GET /usuarios/{id} → busca usuário por ID (sem medidas)
    @GetMapping("/{id}")
    public ResponseEntity<BuscarUsuarioPorIdResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(userService.buscarPorId(id));
    }

    //GET /usuarios/sem-medidas → lista todos os usuários sem medidas
    @GetMapping("/sem-medidas")
    public ResponseEntity<List<UsuarioSemMedidasResponse>> listarTodosSemMedidas() {
        return ResponseEntity.ok(userService.listarTodosSemMedidas());
    }

    //GET /usuarios/{id}/todas-medidas → usuário + todas as medidas
    @GetMapping("/{id}/todas-medidas")
    public ResponseEntity<UsuarioResponse> trazerUsuarioComTodasMedidas(@PathVariable Long id) {
        return ResponseEntity.ok(userService.trazerUsuarioPorIdComTodasAsMedidas(id));
    }

    //GET /usuarios/{id}/ultima-medida → usuário + última medida
    @GetMapping("/{id}/ultima-medida")
    public ResponseEntity<UsuarioResponse> trazerUsuarioComUltimaMedida(@PathVariable Long id) {
        return ResponseEntity.ok(userService.trazerUsuarioPorIdComUltimaMedida(id));
    }
}

