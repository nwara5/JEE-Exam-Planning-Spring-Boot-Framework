package com.Academia.GestionExamen.dao;

import com.Academia.GestionExamen.entities.Semestre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SemestreRepo extends JpaRepository<Semestre,Long> {

    @Query("SELECT s FROM Semestre s WHERE s.NomSemestre = :titreSemestre")
    Semestre findByTitreSemestre(@Param("titreSemestre") String titreSemestre);
}
