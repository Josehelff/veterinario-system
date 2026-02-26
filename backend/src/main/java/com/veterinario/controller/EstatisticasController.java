package com.veterinario.controller;

import com.veterinario.dto.EstatisticasDTO;
import com.veterinario.service.EstatisticasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatisticas")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"})
public class EstatisticasController {

    private final EstatisticasService estatisticasService;

    @GetMapping
    public ResponseEntity<EstatisticasDTO> obterEstatisticas() {
        EstatisticasDTO estatisticas = estatisticasService.obterEstatisticas();
        return ResponseEntity.ok(estatisticas);
    }
}
