package com.veterinario.controller;

import com.veterinario.dto.PrenhezDTO;
import com.veterinario.service.PrenhezService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/prenhezes")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"})
public class PrenhezController {

    private final PrenhezService prenhezService;

    @PostMapping
    public ResponseEntity<PrenhezDTO> criarPrenhez(@RequestBody PrenhezDTO dto) {
        PrenhezDTO criada = prenhezService.criarPrenhez(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }

    @GetMapping
    public ResponseEntity<List<PrenhezDTO>> listarTodas() {
        List<PrenhezDTO> prenhezes = prenhezService.listarTodas();
        return ResponseEntity.ok(prenhezes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrenhezDTO> obterPrenhez(@PathVariable Long id) {
        PrenhezDTO prenhez = prenhezService.obterPrenhez(id);
        return ResponseEntity.ok(prenhez);
    }

    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<PrenhezDTO>> listarPorAnimal(@PathVariable Long animalId) {
        List<PrenhezDTO> prenhezes = prenhezService.listarPorAnimal(animalId);
        return ResponseEntity.ok(prenhezes);
    }

    @GetMapping("/confirmadas")
    public ResponseEntity<List<PrenhezDTO>> listarConfirmadas() {
        List<PrenhezDTO> prenhezes = prenhezService.listarConfirmadas();
        return ResponseEntity.ok(prenhezes);
    }

    @GetMapping("/previsoes")
    public ResponseEntity<List<PrenhezDTO>> listarPrevisoesPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        List<PrenhezDTO> prenhezes = prenhezService.listarPrevisoesPorPeriodo(dataInicio, dataFim);
        return ResponseEntity.ok(prenhezes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrenhezDTO> atualizarPrenhez(@PathVariable Long id, @RequestBody PrenhezDTO dto) {
        PrenhezDTO atualizada = prenhezService.atualizarPrenhez(id, dto);
        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPrenhez(@PathVariable Long id) {
        prenhezService.deletarPrenhez(id);
        return ResponseEntity.noContent().build();
    }
}
