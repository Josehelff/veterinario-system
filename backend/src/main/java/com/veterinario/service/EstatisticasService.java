package com.veterinario.service;

import com.veterinario.dto.EstatisticasDTO;
import com.veterinario.entity.Animal;
import com.veterinario.entity.Desmame;
import com.veterinario.entity.Inseminacao;
import com.veterinario.entity.Nascimento;
import com.veterinario.entity.Prenhez;
import com.veterinario.repository.AnimalRepository;
import com.veterinario.repository.DesmameRepository;
import com.veterinario.repository.InseminacaoRepository;
import com.veterinario.repository.NascimentoRepository;
import com.veterinario.repository.PrenhezRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EstatisticasService {

    private final AnimalRepository animalRepository;
    private final InseminacaoRepository inseminacaoRepository;
    private final PrenhezRepository prenhezRepository;
    private final NascimentoRepository nascimentoRepository;
    private final DesmameRepository desmameRepository;

    public EstatisticasDTO obterEstatisticas() {
        EstatisticasDTO dto = new EstatisticasDTO();

        // Animais
        List<Animal> todosAnimais = animalRepository.findAll();
        dto.setTotalAnimais((long) todosAnimais.size());
        dto.setTotalFemeas(todosAnimais.stream()
                .filter(a -> a.getSexo() == Animal.SexoAnimal.FEMEA)
                .count());
        dto.setTotalMachos(todosAnimais.stream()
                .filter(a -> a.getSexo() == Animal.SexoAnimal.MACHO)
                .count());
        dto.setAnimaisAtivos(todosAnimais.stream()
                .filter(a -> a.getStatus() == Animal.StatusAnimal.ATIVO)
                .count());

        // Inseminações
        List<Inseminacao> todasInseminacoes = inseminacaoRepository.findAll();
        dto.setTotalInseminacoes((long) todasInseminacoes.size());
        dto.setInseminacoesConcluidas(todasInseminacoes.stream()
                .filter(i -> i.getStatus() == Inseminacao.StatusInseminacao.REALIZADA)
                .count());
        dto.setInseminacoesPendentes(todasInseminacoes.stream()
                .filter(i -> i.getResultadoDiagnostico() == Inseminacao.ResultadoDiagnostico.PENDENTE)
                .count());

        // Prenhezes
        List<Prenhez> todasPrenhezes = prenhezRepository.findAll();
        dto.setTotalPrenhezes((long) todasPrenhezes.size());
        dto.setPrenhezesConcluidas(todasPrenhezes.stream()
                .filter(p -> p.getStatus() == Prenhez.StatusPrenhez.CONFIRMADA)
                .count());
        dto.setPrenhezesCanceladas(todasPrenhezes.stream()
                .filter(p -> p.getStatus() == Prenhez.StatusPrenhez.ABORTADA)
                .count());

        // Nascimentos
        List<Nascimento> todosNascimentos = nascimentoRepository.findAll();
        dto.setTotalNascimentos((long) todosNascimentos.size());
        dto.setNascimentosVivos(todosNascimentos.stream()
                .filter(n -> n.getStatusNascimento() == Nascimento.StatusNascimento.VIVO)
                .count());
        dto.setNascimentosMortos(todosNascimentos.stream()
                .filter(n -> n.getStatusNascimento() == Nascimento.StatusNascimento.MORTO)
                .count());

        // Desmames
        List<Desmame> todosDesmames = desmameRepository.findAll();
        dto.setTotalDesmames((long) todosDesmames.size());
        dto.setDesmamesRealizados(todosDesmames.stream()
                .filter(d -> d.getStatus() == Desmame.StatusDesmame.REALIZADO)
                .count());
        dto.setDesmamesPendentes(todosDesmames.stream()
                .filter(d -> d.getStatus() == Desmame.StatusDesmame.PENDENTE)
                .count());

        // Taxas
        dto.setTaxaConcepcao(calcularTaxaConcepcao(todasInseminacoes));
        dto.setTaxaPrenhez(calcularTaxaPrenhez(todasInseminacoes));
        dto.setFaltasNoDG(calcularFaltasNoDG(todasInseminacoes));

        return dto;
    }

    /**
     * Taxa de Concepção = Total de vacas prenhas / Total de vacas inseminadas
     */
    private Double calcularTaxaConcepcao(List<Inseminacao> inseminacoes) {
        if (inseminacoes.isEmpty()) {
            return 0.0;
        }

        long totalInseminadas = inseminacoes.stream()
                .filter(i -> i.getStatus() == Inseminacao.StatusInseminacao.REALIZADA)
                .count();

        long prenhas = inseminacoes.stream()
                .filter(i -> i.getResultadoDiagnostico() == Inseminacao.ResultadoDiagnostico.PRENHA)
                .count();

        return totalInseminadas > 0 ? (prenhas * 100.0) / totalInseminadas : 0.0;
    }

    /**
     * Taxa de Prenhez = Quantidade de vacas prenhas / Quantidade de vacas que passaram no DG
     */
    private Double calcularTaxaPrenhez(List<Inseminacao> inseminacoes) {
        if (inseminacoes.isEmpty()) {
            return 0.0;
        }

        long comDiagnostico = inseminacoes.stream()
                .filter(i -> i.getResultadoDiagnostico() != Inseminacao.ResultadoDiagnostico.PENDENTE)
                .count();

        long prenhas = inseminacoes.stream()
                .filter(i -> i.getResultadoDiagnostico() == Inseminacao.ResultadoDiagnostico.PRENHA)
                .count();

        return comDiagnostico > 0 ? (prenhas * 100.0) / comDiagnostico : 0.0;
    }

    /**
     * Faltou no DG = Vacas inseminadas - Vacas que passaram no DG
     */
    private Long calcularFaltasNoDG(List<Inseminacao> inseminacoes) {
        long totalInseminadas = inseminacoes.stream()
                .filter(i -> i.getStatus() == Inseminacao.StatusInseminacao.REALIZADA)
                .count();

        long comDiagnostico = inseminacoes.stream()
                .filter(i -> i.getResultadoDiagnostico() != Inseminacao.ResultadoDiagnostico.PENDENTE)
                .count();

        return totalInseminadas - comDiagnostico;
    }
}
