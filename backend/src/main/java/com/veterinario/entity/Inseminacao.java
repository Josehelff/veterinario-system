package com.veterinario.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "inseminacoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inseminacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;

    @Column(nullable = false)
    private LocalDate dataInseminacao;

    @Column
    private String reprodutor;

    @Column
    private String observacoes;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusInseminacao status;

    @Column
    private LocalDate dataDiagnostico;

    @Column
    @Enumerated(EnumType.STRING)
    private ResultadoDiagnostico resultadoDiagnostico;

    public enum StatusInseminacao {
        REALIZADA, CANCELADA, REPETIDA
    }

    public enum ResultadoDiagnostico {
        PRENHA, NAO_PRENHA, PENDENTE
    }
}
