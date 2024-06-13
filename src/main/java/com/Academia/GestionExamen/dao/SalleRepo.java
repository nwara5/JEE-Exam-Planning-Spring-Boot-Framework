package com.Academia.GestionExamen.dao;

import com.Academia.GestionExamen.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SalleRepo extends JpaRepository<Salle,Long> {


    @Query("SELECT s FROM Salle s WHERE s.NomSalle = :nomSalle")
    Salle getSallesByNom(@Param("nomSalle") String nomSalle);


}
