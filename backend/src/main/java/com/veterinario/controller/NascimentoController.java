package com.veterinario.controller;

import com.veterinario.dto.NascimentoDTO;
import com.veterinario.service.NascimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/nascimentos")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"})
public class NascimentoController {

    private final NascimentoService nascimentoService;

    @PostMapping
    public ResponseEntity<NascimentoDTO> criarNascimento(@RequestBody NascimentoDTO dto) {
        NascimentoDTO criado = nascimentoService.criarNascimento(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<NascimentoDTO>> listarTodos() {
        List<NascimentoDTO> nascimentos = nascimentoService.listarTodos();
        return ResponseEntity.ok(nascimentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NascimentoDTO> obterNascimento(@PathVariable Long id) {
        NascimentoDTO nascimento = nascimentoService.obterNascimento(id);
        return ResponseEntity.ok(nascimento);
    }

    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<NascimentoDTO>> listarPorAnimal(@PathVariable Long animalId) {
        List<NascimentoDTO> nascimentos = nascimentoService.listarPorAnimal(animalId);
        return ResponseEntity.ok(nascimentos);
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<NascimentoDTO>> listarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        List<NascimentoDTO> nascimentos = nascimentoService.listarPorPeriodo(dataInicio, dataFim);
        return ResponseEntity.ok(nascimentos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NascimentoDTO> atualizarNascimento(@PathVariable Long id, @RequestBody NascimentoDTO dto) {
        NascimentoDTO atualizado = nascimentoService.atualizarNascimento(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarNascimento(@PathVariable Long id) {
        nascimentoService.deletarNascimento(id);
        return ResponseEntity.noContent().build();
    }
}
