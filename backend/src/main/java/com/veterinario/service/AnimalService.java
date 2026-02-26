package com.veterinario.service;

import com.veterinario.dto.AnimalDTO;
import com.veterinario.entity.Animal;
import com.veterinario.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalDTO criarAnimal(AnimalDTO dto) {
        Animal animal = Animal.builder()
                .brinco(dto.getBrinco())
                .nome(dto.getNome())
                .tipo(dto.getTipo())
                .sexo(dto.getSexo())
                .dataNascimento(dto.getDataNascimento())
                .raca(dto.getRaca())
                .peso(dto.getPeso())
                .observacoes(dto.getObservacoes())
                .status(Animal.StatusAnimal.ATIVO)
                .build();

        Animal salvo = animalRepository.save(animal);
        return converterParaDTO(salvo);
    }

    public AnimalDTO atualizarAnimal(Long id, AnimalDTO dto) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));

        animal.setNome(dto.getNome());
        animal.setRaca(dto.getRaca());
        animal.setPeso(dto.getPeso());
        animal.setObservacoes(dto.getObservacoes());
        animal.setStatus(dto.getStatus());

        Animal atualizado = animalRepository.save(animal);
        return converterParaDTO(atualizado);
    }

    public AnimalDTO obterAnimal(Long id) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));
        return converterParaDTO(animal);
    }

    public List<AnimalDTO> listarTodos() {
        return animalRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public List<AnimalDTO> listarFemeasAtivas() {
        return animalRepository.findFemeasAtivas()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public List<AnimalDTO> listarMachosAtivos() {
        return animalRepository.findMachosAtivos()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public List<AnimalDTO> listarPorTipo(Animal.TipoAnimal tipo) {
        return animalRepository.findByTipo(tipo)
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public void deletarAnimal(Long id) {
        animalRepository.deleteById(id);
    }

    public AnimalDTO obterPorBrinco(String brinco) {
        Animal animal = animalRepository.findByBrinco(brinco)
                .orElseThrow(() -> new RuntimeException("Animal com brinco " + brinco + " não encontrado"));
        return converterParaDTO(animal);
    }

    private AnimalDTO converterParaDTO(Animal animal) {
        return AnimalDTO.builder()
                .id(animal.getId())
                .brinco(animal.getBrinco())
                .nome(animal.getNome())
                .tipo(animal.getTipo())
                .sexo(animal.getSexo())
                .dataNascimento(animal.getDataNascimento())
                .raca(animal.getRaca())
                .peso(animal.getPeso())
                .observacoes(animal.getObservacoes())
                .status(animal.getStatus())
                .build();
    }
}
