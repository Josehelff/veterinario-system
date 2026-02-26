package com.veterinario.repository;

import com.veterinario.entity.Nascimento;
import com.veterinario.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface NascimentoRepository extends JpaRepository<Nascimento, Long> {

    List<Nascimento> findByAnimal(Animal animal);

    @Query("SELECT COUNT(n) FROM Nascimento n WHERE n.statusNascimento = 'VIVO'")
    Long countNascimentosVivos();

    @Query("SELECT n FROM Nascimento n WHERE n.dataNascimento BETWEEN :dataInicio AND :dataFim")
    List<Nascimento> findByDataNascimentoBetween(@Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim);

    @Query("SELECT n FROM Nascimento n WHERE n.animal = :animal ORDER BY n.dataNascimento DESC")
    List<Nascimento> findByAnimalOrderByDataDesc(@Param("animal") Animal animal);
}
