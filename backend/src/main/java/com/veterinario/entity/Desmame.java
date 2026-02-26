package com.veterinario.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "desmames")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Desmame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nascimento_id")
    private Nascimento nascimento;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false)
    private Integer diasDesmame;

    @Column(nullable = false)
    private LocalDate previsaoDesmame;

    @Column
    private LocalDate dataDesmameReal;

    @Column
    private Double pesoDesmame;

    @Column
    private String observacoes;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusDesmame status;

    public enum StatusDesmame {
        PENDENTE, REALIZADO, CANCELADO
    }
}
