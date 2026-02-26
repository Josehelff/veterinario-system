package com.veterinario.controller;

import com.veterinario.dto.InseminacaoDTO;
import com.veterinario.service.InseminacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/inseminacoes")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"})
public class InseminacaoController {

    private final InseminacaoService inseminacaoService;

    @PostMapping
    public ResponseEntity<InseminacaoDTO> criarInseminacao(@RequestBody InseminacaoDTO dto) {
        InseminacaoDTO criada = inseminacaoService.criarInseminacao(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }

    @GetMapping
    public ResponseEntity<List<InseminacaoDTO>> listarTodas() {
        List<InseminacaoDTO> inseminacoes = inseminacaoService.listarTodas();
        return ResponseEntity.ok(inseminacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InseminacaoDTO> obterInseminacao(@PathVariable Long id) {
        InseminacaoDTO inseminacao = inseminacaoService.obterInseminacao(id);
        return ResponseEntity.ok(inseminacao);
    }

    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<InseminacaoDTO>> listarPorAnimal(@PathVariable Long animalId) {
        List<InseminacaoDTO> inseminacoes = inseminacaoService.listarPorAnimal(animalId);
        return ResponseEntity.ok(inseminacoes);
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<InseminacaoDTO>> listarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        List<InseminacaoDTO> inseminacoes = inseminacaoService.listarPorPeriodo(dataInicio, dataFim);
        return ResponseEntity.ok(inseminacoes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InseminacaoDTO> atualizarInseminacao(@PathVariable Long id, @RequestBody InseminacaoDTO dto) {
        InseminacaoDTO atualizada = inseminacaoService.atualizarInseminacao(id, dto);
        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarInseminacao(@PathVariable Long id) {
        inseminacaoService.deletarInseminacao(id);
        return ResponseEntity.noContent().build();
    }
}
