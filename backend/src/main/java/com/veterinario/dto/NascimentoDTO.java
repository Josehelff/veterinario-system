package com.veterinario.dto;

import com.veterinario.entity.Animal;
import com.veterinario.entity.Nascimento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NascimentoDTO {

    private Long id;
    private Long animalId;
    private String animalBrinco;
    private String animalNome;
    private Long prenhezId;
    private LocalDate dataNascimento;
    private String brincoFilhote;
    private String nomeFilhote;
    private Animal.SexoAnimal sexoFilhote;
    private Double pesoNascimento;
    private Nascimento.StatusNascimento statusNascimento;
    private String observacoes;
}
