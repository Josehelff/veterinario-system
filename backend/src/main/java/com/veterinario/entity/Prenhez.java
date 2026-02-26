package com.veterinario.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prenhezes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prenhez {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "inseminacao_id")
    private Inseminacao inseminacao;

    @Column(nullable = false)
    private LocalDate dataConfirmacao;

    @Column(nullable = false)
    private Integer periodoGestacao;

    @Column(nullable = false)
    private LocalDate previsaoNascimento;

    @Column
    private String observacoes;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPrenhez status;

    public enum StatusPrenhez {
        CONFIRMADA, ABORTADA, FINALIZADA
    }
}
