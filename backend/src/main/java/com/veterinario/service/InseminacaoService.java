package com.veterinario.service;

import com.veterinario.dto.InseminacaoDTO;
import com.veterinario.entity.Animal;
import com.veterinario.entity.Inseminacao;
import com.veterinario.repository.AnimalRepository;
import com.veterinario.repository.InseminacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class InseminacaoService {

    private final InseminacaoRepository inseminacaoRepository;
    private final AnimalRepository animalRepository;

    public InseminacaoDTO criarInseminacao(InseminacaoDTO dto) {
        Animal animal = animalRepository.findById(dto.getAnimalId())
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));

        Inseminacao inseminacao = Inseminacao.builder()
                .animal(animal)
                .dataInseminacao(dto.getDataInseminacao())
                .reprodutor(dto.getReprodutor())
                .observacoes(dto.getObservacoes())
                .status(Inseminacao.StatusInseminacao.REALIZADA)
                .resultadoDiagnostico(Inseminacao.ResultadoDiagnostico.PENDENTE)
                .build();

        Inseminacao salva = inseminacaoRepository.save(inseminacao);
        return converterParaDTO(salva);
    }

    public InseminacaoDTO atualizarInseminacao(Long id, InseminacaoDTO dto) {
        Inseminacao inseminacao = inseminacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inseminação não encontrada"));

        inseminacao.setReprodutor(dto.getReprodutor());
        inseminacao.setObservacoes(dto.getObservacoes());
        inseminacao.setStatus(dto.getStatus());
        inseminacao.setDataDiagnostico(dto.getDataDiagnostico());
        inseminacao.setResultadoDiagnostico(dto.getResultadoDiagnostico());

        Inseminacao atualizada = inseminacaoRepository.save(inseminacao);
        return converterParaDTO(atualizada);
    }

    public InseminacaoDTO obterInseminacao(Long id) {
        Inseminacao inseminacao = inseminacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inseminação não encontrada"));
        return converterParaDTO(inseminacao);
    }

    public List<InseminacaoDTO> listarTodas() {
        return inseminacaoRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public List<InseminacaoDTO> listarPorAnimal(Long animalId) {
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));
        return inseminacaoRepository.findByAnimalOrderByDataDesc(animal)
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public List<InseminacaoDTO> listarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return inseminacaoRepository.findByDataInseminacaoBetween(dataInicio, dataFim)
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public void deletarInseminacao(Long id) {
        inseminacaoRepository.deleteById(id);
    }

    private InseminacaoDTO converterParaDTO(Inseminacao inseminacao) {
        return InseminacaoDTO.builder()
                .id(inseminacao.getId())
                .animalId(inseminacao.getAnimal().getId())
                .animalBrinco(inseminacao.getAnimal().getBrinco())
                .animalNome(inseminacao.getAnimal().getNome())
                .dataInseminacao(inseminacao.getDataInseminacao())
                .reprodutor(inseminacao.getReprodutor())
                .observacoes(inseminacao.getObservacoes())
                .status(inseminacao.getStatus())
                .dataDiagnostico(inseminacao.getDataDiagnostico())
                .resultadoDiagnostico(inseminacao.getResultadoDiagnostico())
                .build();
    }
}
