package com.ProgWebII.biotrack.controller;

import com.ProgWebII.biotrack.dto.request.MeasureRequest;
import com.ProgWebII.biotrack.service.MeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medidas")
public class MeasureController {
  @Autowired
  private MeasureService measureService;

  @PostMapping("/{userId}")
  public ResponseEntity<String> createMeasure(@RequestBody MeasureRequest measureRequest, @PathVariable Long userId) {
    measureService.CreateMeasure(measureRequest, userId);
    return ResponseEntity.ok("Medida criada com sucesso!");
  }
}
