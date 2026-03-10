package com.veterinario.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "animais")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String brinco;

    @Column
    private String nome;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoAnimal tipo;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SexoAnimal sexo;

    @Column
    private LocalDate dataNascimento;

    @Column
    private String raca;

    @Column
    private Double peso;

    @Column
    private String proprietario;

    @Column(length = 1000)
    private String observacoes;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusAnimal status;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Inseminacao> inseminacoes;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Prenhez> prenhezes;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Nascimento> nascimentos;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Desmame> desmames;

    public enum TipoAnimal {
        BOVINO, EQUINO, CAPRINO, OVINO, SUINO, OUTRO
    }

    public enum SexoAnimal {
        MACHO, FEMEA
    }

    public enum StatusAnimal {
        ATIVO, INATIVO, VENDIDO, MORTO, FALECIDO
    }
}
