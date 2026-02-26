package com.veterinario.dto;

import com.veterinario.entity.Desmame;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DesmameDTO {

    private Long id;
    private Long animalId;
    private String animalBrinco;
    private String animalNome;
    private Long nascimentoId;
    private LocalDate dataNascimento;
    private Integer diasDesmame;
    private LocalDate previsaoDesmame;
    private LocalDate dataDesmameReal;
    private Double pesoDesmame;
    private String observacoes;
    private Desmame.StatusDesmame status;
}
