package com.veterinario.dto;

import com.veterinario.entity.Animal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnimalDTO {

    private Long id;
    private String brinco;
    private String nome;
    private Animal.TipoAnimal tipo;
    private Animal.SexoAnimal sexo;
    private LocalDate dataNascimento;
    private String raca;
    private Double peso;
    private String observacoes;
    private Animal.StatusAnimal status;
}
