package com.Academia.GestionExamen.dao;

import com.Academia.GestionExamen.entities.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FiliereRepo extends JpaRepository<Filiere, Long> {

    @Modifying
    @Query("UPDATE Filiere f SET f.nomFiliere =: newtitre WHERE f.nomFiliere =: oldTtitre")
    public void modifyFiliere(@Param("newTitre") String newTitre, @Param("oldTitre") String oldTitre);

    @Query("SELECT f FROM Filiere f WHERE f.nomFiliere = :nomFiliere")
    Filiere findByTitreFiliere(@Param("nomFiliere") String nomFiliere);
}
