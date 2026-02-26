package com.veterinario.repository;

import com.veterinario.entity.Prenhez;
import com.veterinario.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PrenhezRepository extends JpaRepository<Prenhez, Long> {

    List<Prenhez> findByAnimal(Animal animal);

    List<Prenhez> findByStatus(Prenhez.StatusPrenhez status);

    @Query("SELECT COUNT(p) FROM Prenhez p WHERE p.status = 'CONFIRMADA'")
    Long countPrenhezesConcluidas();

    @Query("SELECT p FROM Prenhez p WHERE p.status = 'CONFIRMADA' AND p.previsaoNascimento BETWEEN :dataInicio AND :dataFim")
    List<Prenhez> findPrevisaoNascimentoBetween(@Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim);

    @Query("SELECT p FROM Prenhez p WHERE p.animal = :animal ORDER BY p.dataConfirmacao DESC")
    List<Prenhez> findByAnimalOrderByDataDesc(@Param("animal") Animal animal);
}
