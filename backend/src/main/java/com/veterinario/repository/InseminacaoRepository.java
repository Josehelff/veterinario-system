package com.veterinario.repository;

import com.veterinario.entity.Inseminacao;
import com.veterinario.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InseminacaoRepository extends JpaRepository<Inseminacao, Long> {

    List<Inseminacao> findByAnimal(Animal animal);

    List<Inseminacao> findByStatus(Inseminacao.StatusInseminacao status);

    @Query("SELECT COUNT(i) FROM Inseminacao i WHERE i.status = 'REALIZADA'")
    Long countInseminacoesConcluidas();

    @Query("SELECT COUNT(i) FROM Inseminacao i WHERE i.status = 'REALIZADA' AND i.resultadoDiagnostico = 'PRENHA'")
    Long countInseminacoesPrenhas();

    @Query("SELECT i FROM Inseminacao i WHERE i.animal = :animal ORDER BY i.dataInseminacao DESC")
    List<Inseminacao> findByAnimalOrderByDataDesc(@Param("animal") Animal animal);

    @Query("SELECT i FROM Inseminacao i WHERE i.dataInseminacao BETWEEN :dataInicio AND :dataFim")
    List<Inseminacao> findByDataInseminacaoBetween(@Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim);
}
