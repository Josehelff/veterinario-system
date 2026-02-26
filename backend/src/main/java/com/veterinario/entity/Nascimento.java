package com.veterinario.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "nascimentos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Nascimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "prenhez_id")
    private Prenhez prenhez;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false)
    private String brincoFilhote;

    @Column
    private String nomeFilhote;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Animal.SexoAnimal sexoFilhote;

    @Column
    private Double pesoNascimento;

    @Column
    @Enumerated(EnumType.STRING)
    private StatusNascimento statusNascimento;

    @Column
    private String observacoes;

    public enum StatusNascimento {
        VIVO, MORTO, ABORTADO
    }
}
