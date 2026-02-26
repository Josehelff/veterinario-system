package com.veterinario.controller;

import com.veterinario.dto.DesmameDTO;
import com.veterinario.service.DesmameService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/desmames")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"})
public class DesmameController {

    private final DesmameService desmameService;

    @PostMapping
    public ResponseEntity<DesmameDTO> criarDesmame(@RequestBody DesmameDTO dto) {
        DesmameDTO criado = desmameService.criarDesmame(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<DesmameDTO>> listarTodos() {
        List<DesmameDTO> desmames = desmameService.listarTodos();
        return ResponseEntity.ok(desmames);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DesmameDTO> obterDesmame(@PathVariable Long id) {
        DesmameDTO desmame = desmameService.obterDesmame(id);
        return ResponseEntity.ok(desmame);
    }

    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<DesmameDTO>> listarPorAnimal(@PathVariable Long animalId) {
        List<DesmameDTO> desmames = desmameService.listarPorAnimal(animalId);
        return ResponseEntity.ok(desmames);
    }

    @GetMapping("/pendentes")
    public ResponseEntity<List<DesmameDTO>> listarPendentes() {
        List<DesmameDTO> desmames = desmameService.listarPendentes();
        return ResponseEntity.ok(desmames);
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<DesmameDTO>> listarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        List<DesmameDTO> desmames = desmameService.listarPorPeriodo(dataInicio, dataFim);
        return ResponseEntity.ok(desmames);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DesmameDTO> atualizarDesmame(@PathVariable Long id, @RequestBody DesmameDTO dto) {
        DesmameDTO atualizado = desmameService.atualizarDesmame(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDesmame(@PathVariable Long id) {
        desmameService.deletarDesmame(id);
        return ResponseEntity.noContent().build();
    }
}
