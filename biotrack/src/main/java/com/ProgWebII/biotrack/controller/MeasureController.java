package com.ProgWebII.biotrack.controller;

import com.ProgWebII.biotrack.dto.MedidaResponse;
import com.ProgWebII.biotrack.service.MeasureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios/{idUsuario}/medidas")
public class MeasureController {
  @Autowired
  private MeasureService measureService;

  @PostMapping("/{userId}")
  public ResponseEntity<String> createMeasure(@RequestBody MeasureRequest measureRequest, @PathVariable Long userId) {
    measureService.CreateMeasure(measureRequest, userId);
    return ResponseEntity.ok("Medida criada com sucesso!");
  }

    private final MeasureService measureService;

    public MeasureController(MeasureService measureService) {
        this.measureService = measureService;
    }

    //GET /api/v1/usuarios/{idUsuario}/medidas → todas as medidas do usuário
    @GetMapping("/{usuarioId}/medidas")
    public ResponseEntity<List<MedidaResponse>> listarTodasAsMedidas(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(measureService.listarTodasAsMedidasDeUmUsuario(usuarioId));
    }

    // GET /api/v1/usuarios/{idUsuario}/medidas/{medidaId} → busca uma medida específica de um usuário
    @GetMapping("/{usuarioId}/medidas/{medidaId}")
    public ResponseEntity<MedidaResponse> buscarMedidaPorId(
            @PathVariable Long usuarioId,
            @PathVariable Long medidaId) {
        MedidaResponse medida = measureService.buscarMedidaPorId(usuarioId, medidaId);
        return ResponseEntity.ok(medida);
    }

}
