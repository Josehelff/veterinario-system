package com.veterinario.repository;

import com.veterinario.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    Optional<Animal> findByBrinco(String brinco);

    List<Animal> findByTipo(Animal.TipoAnimal tipo);

    List<Animal> findBySexo(Animal.SexoAnimal sexo);

    List<Animal> findByStatus(Animal.StatusAnimal status);

    @Query("SELECT a FROM Animal a WHERE a.status = 'ATIVO' AND a.sexo = 'FEMEA'")
    List<Animal> findFemeasAtivas();

    @Query("SELECT a FROM Animal a WHERE a.status = 'ATIVO' AND a.sexo = 'MACHO'")
    List<Animal> findMachosAtivos();
}
