package com.veterinario.dto;

import com.veterinario.entity.Inseminacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InseminacaoDTO {

    private Long id;
    private Long animalId;
    private String animalBrinco;
    private String animalNome;
    private LocalDate dataInseminacao;
    private String reprodutor;
    private String observacoes;
    private Inseminacao.StatusInseminacao status;
    private LocalDate dataDiagnostico;
    private Inseminacao.ResultadoDiagnostico resultadoDiagnostico;
}
