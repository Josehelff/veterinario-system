package com.veterinario.service;

import com.veterinario.dto.DesmameDTO;
import com.veterinario.entity.Animal;
import com.veterinario.entity.Desmame;
import com.veterinario.entity.Nascimento;
import com.veterinario.repository.AnimalRepository;
import com.veterinario.repository.DesmameRepository;
import com.veterinario.repository.NascimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DesmameService {

    private final DesmameRepository desmameRepository;
    private final AnimalRepository animalRepository;
    private final NascimentoRepository nascimentoRepository;

    public DesmameDTO criarDesmame(DesmameDTO dto) {
        Animal animal = animalRepository.findById(dto.getAnimalId())
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));

        Nascimento nascimento = null;
        if (dto.getNascimentoId() != null) {
            nascimento = nascimentoRepository.findById(dto.getNascimentoId())
                    .orElseThrow(() -> new RuntimeException("Nascimento não encontrado"));
        }

        LocalDate previsaoDesmame = dto.getDataNascimento()
                .plusDays(dto.getDiasDesmame());

        Desmame desmame = Desmame.builder()
                .animal(animal)
                .nascimento(nascimento)
                .dataNascimento(dto.getDataNascimento())
                .diasDesmame(dto.getDiasDesmame())
                .previsaoDesmame(previsaoDesmame)
                .observacoes(dto.getObservacoes())
                .status(Desmame.StatusDesmame.PENDENTE)
                .build();

        Desmame salvo = desmameRepository.save(desmame);
        return converterParaDTO(salvo);
    }

    public DesmameDTO atualizarDesmame(Long id, DesmameDTO dto) {
        Desmame desmame = desmameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Desmame não encontrado"));

        desmame.setDataDesmameReal(dto.getDataDesmameReal());
        desmame.setPesoDesmame(dto.getPesoDesmame());
        desmame.setObservacoes(dto.getObservacoes());
        desmame.setStatus(dto.getStatus());

        Desmame atualizado = desmameRepository.save(desmame);
        return converterParaDTO(atualizado);
    }

    public DesmameDTO obterDesmame(Long id) {
        Desmame desmame = desmameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Desmame não encontrado"));
        return converterParaDTO(desmame);
    }

    public List<DesmameDTO> listarTodos() {
        return desmameRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public List<DesmameDTO> listarPorAnimal(Long animalId) {
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));
        return desmameRepository.findByAnimalOrderByDataDesc(animal)
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public List<DesmameDTO> listarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return desmameRepository.findByPrevisaoDesmameBetween(dataInicio, dataFim)
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public List<DesmameDTO> listarPendentes() {
        return desmameRepository.findByStatus(Desmame.StatusDesmame.PENDENTE)
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public void deletarDesmame(Long id) {
        desmameRepository.deleteById(id);
    }

    private DesmameDTO converterParaDTO(Desmame desmame) {
        return DesmameDTO.builder()
                .id(desmame.getId())
                .animalId(desmame.getAnimal().getId())
                .animalBrinco(desmame.getAnimal().getBrinco())
                .animalNome(desmame.getAnimal().getNome())
                .nascimentoId(desmame.getNascimento() != null ? desmame.getNascimento().getId() : null)
                .dataNascimento(desmame.getDataNascimento())
                .diasDesmame(desmame.getDiasDesmame())
                .previsaoDesmame(desmame.getPrevisaoDesmame())
                .dataDesmameReal(desmame.getDataDesmameReal())
                .pesoDesmame(desmame.getPesoDesmame())
                .observacoes(desmame.getObservacoes())
                .status(desmame.getStatus())
                .build();
    }
}
