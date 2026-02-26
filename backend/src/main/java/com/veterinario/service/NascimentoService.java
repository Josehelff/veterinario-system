package com.veterinario.service;

import com.veterinario.dto.NascimentoDTO;
import com.veterinario.entity.Animal;
import com.veterinario.entity.Nascimento;
import com.veterinario.entity.Prenhez;
import com.veterinario.repository.AnimalRepository;
import com.veterinario.repository.NascimentoRepository;
import com.veterinario.repository.PrenhezRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class NascimentoService {

    private final NascimentoRepository nascimentoRepository;
    private final AnimalRepository animalRepository;
    private final PrenhezRepository prenhezRepository;

    public NascimentoDTO criarNascimento(NascimentoDTO dto) {
        Animal animal = animalRepository.findById(dto.getAnimalId())
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));

        Prenhez prenhez = null;
        if (dto.getPrenhezId() != null) {
            prenhez = prenhezRepository.findById(dto.getPrenhezId())
                    .orElseThrow(() -> new RuntimeException("Prenhez não encontrada"));
        }

        Nascimento nascimento = Nascimento.builder()
                .animal(animal)
                .prenhez(prenhez)
                .dataNascimento(dto.getDataNascimento())
                .brincoFilhote(dto.getBrincoFilhote())
                .nomeFilhote(dto.getNomeFilhote())
                .sexoFilhote(dto.getSexoFilhote())
                .pesoNascimento(dto.getPesoNascimento())
                .statusNascimento(dto.getStatusNascimento() != null ? dto.getStatusNascimento() : Nascimento.StatusNascimento.VIVO)
                .observacoes(dto.getObservacoes())
                .build();

        Nascimento salvo = nascimentoRepository.save(nascimento);
        return converterParaDTO(salvo);
    }

    public NascimentoDTO atualizarNascimento(Long id, NascimentoDTO dto) {
        Nascimento nascimento = nascimentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nascimento não encontrado"));

        nascimento.setNomeFilhote(dto.getNomeFilhote());
        nascimento.setPesoNascimento(dto.getPesoNascimento());
        nascimento.setStatusNascimento(dto.getStatusNascimento());
        nascimento.setObservacoes(dto.getObservacoes());

        Nascimento atualizado = nascimentoRepository.save(nascimento);
        return converterParaDTO(atualizado);
    }

    public NascimentoDTO obterNascimento(Long id) {
        Nascimento nascimento = nascimentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nascimento não encontrado"));
        return converterParaDTO(nascimento);
    }

    public List<NascimentoDTO> listarTodos() {
        return nascimentoRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public List<NascimentoDTO> listarPorAnimal(Long animalId) {
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));
        return nascimentoRepository.findByAnimalOrderByDataDesc(animal)
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public List<NascimentoDTO> listarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return nascimentoRepository.findByDataNascimentoBetween(dataInicio, dataFim)
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public void deletarNascimento(Long id) {
        nascimentoRepository.deleteById(id);
    }

    private NascimentoDTO converterParaDTO(Nascimento nascimento) {
        return NascimentoDTO.builder()
                .id(nascimento.getId())
                .animalId(nascimento.getAnimal().getId())
                .animalBrinco(nascimento.getAnimal().getBrinco())
                .animalNome(nascimento.getAnimal().getNome())
                .prenhezId(nascimento.getPrenhez() != null ? nascimento.getPrenhez().getId() : null)
                .dataNascimento(nascimento.getDataNascimento())
                .brincoFilhote(nascimento.getBrincoFilhote())
                .nomeFilhote(nascimento.getNomeFilhote())
                .sexoFilhote(nascimento.getSexoFilhote())
                .pesoNascimento(nascimento.getPesoNascimento())
                .statusNascimento(nascimento.getStatusNascimento())
                .observacoes(nascimento.getObservacoes())
                .build();
    }
}
