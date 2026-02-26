package com.veterinario.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstatisticasDTO {

    // Animais
    private Long totalAnimais;
    private Long totalFemeas;
    private Long totalMachos;
    private Long animaisAtivos;

    // Inseminações
    private Long totalInseminacoes;
    private Long inseminacoesConcluidas;
    private Long inseminacoesPendentes;

    // Prenhezes
    private Long totalPrenhezes;
    private Long prenhezesConcluidas;
    private Long prenhezesCanceladas;

    // Nascimentos
    private Long totalNascimentos;
    private Long nascimentosVivos;
    private Long nascimentosMortos;

    // Desmames
    private Long totalDesmames;
    private Long desmamesRealizados;
    private Long desmamesPendentes;

    // Taxas
    private Double taxaConcepção;
    private Double taxaPrenhez;
    private Long faltasNoDG;
}
