package com.veterinario.repository;

import com.veterinario.entity.Desmame;
import com.veterinario.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DesmameRepository extends JpaRepository<Desmame, Long> {

    List<Desmame> findByAnimal(Animal animal);

    List<Desmame> findByStatus(Desmame.StatusDesmame status);

    @Query("SELECT COUNT(d) FROM Desmame d WHERE d.status = 'REALIZADO'")
    Long countDesmamesRealizados();

    @Query("SELECT d FROM Desmame d WHERE d.previsaoDesmame BETWEEN :dataInicio AND :dataFim")
    List<Desmame> findByPrevisaoDesmameBetween(@Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim);

    @Query("SELECT d FROM Desmame d WHERE d.animal = :animal ORDER BY d.dataNascimento DESC")
    List<Desmame> findByAnimalOrderByDataDesc(@Param("animal") Animal animal);
}
