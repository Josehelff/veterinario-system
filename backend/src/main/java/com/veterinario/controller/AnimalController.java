package com.veterinario.controller;

import com.veterinario.dto.AnimalDTO;
import com.veterinario.entity.Animal;
import com.veterinario.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animais")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"})
public class AnimalController {

    private final AnimalService animalService;

    @PostMapping
    public ResponseEntity<AnimalDTO> criarAnimal(@RequestBody AnimalDTO dto) {
        AnimalDTO criado = animalService.criarAnimal(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<AnimalDTO>> listarTodos() {
        List<AnimalDTO> animais = animalService.listarTodos();
        return ResponseEntity.ok(animais);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalDTO> obterAnimal(@PathVariable Long id) {
        AnimalDTO animal = animalService.obterAnimal(id);
        return ResponseEntity.ok(animal);
    }

    @GetMapping("/brinco/{brinco}")
    public ResponseEntity<AnimalDTO> obterPorBrinco(@PathVariable String brinco) {
        AnimalDTO animal = animalService.obterPorBrinco(brinco);
        return ResponseEntity.ok(animal);
    }

    @GetMapping("/femeas/ativas")
    public ResponseEntity<List<AnimalDTO>> listarFemeasAtivas() {
        List<AnimalDTO> femeas = animalService.listarFemeasAtivas();
        return ResponseEntity.ok(femeas);
    }

    @GetMapping("/machos/ativos")
    public ResponseEntity<List<AnimalDTO>> listarMachosAtivos() {
        List<AnimalDTO> machos = animalService.listarMachosAtivos();
        return ResponseEntity.ok(machos);
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<AnimalDTO>> listarPorTipo(@PathVariable Animal.TipoAnimal tipo) {
        List<AnimalDTO> animais = animalService.listarPorTipo(tipo);
        return ResponseEntity.ok(animais);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimalDTO> atualizarAnimal(@PathVariable Long id, @RequestBody AnimalDTO dto) {
        AnimalDTO atualizado = animalService.atualizarAnimal(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAnimal(@PathVariable Long id) {
        animalService.deletarAnimal(id);
        return ResponseEntity.noContent().build();
    }
}
