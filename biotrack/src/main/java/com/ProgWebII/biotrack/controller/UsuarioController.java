package com.ProgWebII.biotrack.controller;

import com.ProgWebII.biotrack.model.User;
import com.ProgWebII.biotrack.model.Imc;
import com.ProgWebII.biotrack.repository.UserRepository;
import com.ProgWebII.biotrack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import com.ProgWebII.biotrack.dto.request.UserRequest;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private Imc imc;

    @PostMapping()
    public ResponseEntity<String> criarUsuario(@RequestBody UserRequest userRequest) {
        userService.createUser(userRequest);
        return ResponseEntity.ok("Usuário criado com sucesso!");
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
}

