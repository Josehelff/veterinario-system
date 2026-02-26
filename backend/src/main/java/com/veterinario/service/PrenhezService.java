package com.veterinario.service;

import com.veterinario.dto.PrenhezDTO;
import com.veterinario.entity.Animal;
import com.veterinario.entity.Inseminacao;
import com.veterinario.entity.Prenhez;
import com.veterinario.repository.AnimalRepository;
import com.veterinario.repository.InseminacaoRepository;
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
public class PrenhezService {

    private final PrenhezRepository prenhezRepository;
    private final AnimalRepository animalRepository;
    private final InseminacaoRepository inseminacaoRepository;

    public PrenhezDTO criarPrenhez(PrenhezDTO dto) {
        Animal animal = animalRepository.findById(dto.getAnimalId())
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));

        Inseminacao inseminacao = null;
        if (dto.getInseminacaoId() != null) {
            inseminacao = inseminacaoRepository.findById(dto.getInseminacaoId())
                    .orElseThrow(() -> new RuntimeException("Inseminação não encontrada"));
        }

        LocalDate previsaoNascimento = dto.getDataConfirmacao()
                .plusDays(dto.getPeriodoGestacao());

        Prenhez prenhez = Prenhez.builder()
                .animal(animal)
                .inseminacao(inseminacao)
                .dataConfirmacao(dto.getDataConfirmacao())
                .periodoGestacao(dto.getPeriodoGestacao())
                .previsaoNascimento(previsaoNascimento)
                .observacoes(dto.getObservacoes())
                .status(Prenhez.StatusPrenhez.CONFIRMADA)
                .build();

        Prenhez salva = prenhezRepository.save(prenhez);
        return converterParaDTO(salva);
    }

    public PrenhezDTO atualizarPrenhez(Long id, PrenhezDTO dto) {
        Prenhez prenhez = prenhezRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prenhez não encontrada"));

        prenhez.setObservacoes(dto.getObservacoes());
        prenhez.setStatus(dto.getStatus());

        Prenhez atualizada = prenhezRepository.save(prenhez);
        return converterParaDTO(atualizada);
    }

    public PrenhezDTO obterPrenhez(Long id) {
        Prenhez prenhez = prenhezRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prenhez não encontrada"));
        return converterParaDTO(prenhez);
    }

    public List<PrenhezDTO> listarTodas() {
        return prenhezRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public List<PrenhezDTO> listarPorAnimal(Long animalId) {
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));
        return prenhezRepository.findByAnimalOrderByDataDesc(animal)
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public List<PrenhezDTO> listarPrevisoesPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return prenhezRepository.findPrevisaoNascimentoBetween(dataInicio, dataFim)
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public List<PrenhezDTO> listarConfirmadas() {
        return prenhezRepository.findByStatus(Prenhez.StatusPrenhez.CONFIRMADA)
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public void deletarPrenhez(Long id) {
        prenhezRepository.deleteById(id);
    }

    private PrenhezDTO converterParaDTO(Prenhez prenhez) {
        return PrenhezDTO.builder()
                .id(prenhez.getId())
                .animalId(prenhez.getAnimal().getId())
                .animalBrinco(prenhez.getAnimal().getBrinco())
                .animalNome(prenhez.getAnimal().getNome())
                .inseminacaoId(prenhez.getInseminacao() != null ? prenhez.getInseminacao().getId() : null)
                .dataConfirmacao(prenhez.getDataConfirmacao())
                .periodoGestacao(prenhez.getPeriodoGestacao())
                .previsaoNascimento(prenhez.getPrevisaoNascimento())
                .observacoes(prenhez.getObservacoes())
                .status(prenhez.getStatus())
                .build();
    }
}
