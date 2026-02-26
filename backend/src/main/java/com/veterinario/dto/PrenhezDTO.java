package com.veterinario.dto;

import com.veterinario.entity.Prenhez;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrenhezDTO {

    private Long id;
    private Long animalId;
    private String animalBrinco;
    private String animalNome;
    private Long inseminacaoId;
    private LocalDate dataConfirmacao;
    private Integer periodoGestacao;
    private LocalDate previsaoNascimento;
    private String observacoes;
    private Prenhez.StatusPrenhez status;
}
