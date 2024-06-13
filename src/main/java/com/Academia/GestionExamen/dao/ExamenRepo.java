package com.Academia.GestionExamen.dao;

import com.Academia.GestionExamen.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
public interface ExamenRepo extends JpaRepository<Examen,Long> {

    @Modifying
    @Query("DELETE FROM Examen e WHERE e.IdExam = :id")
    void deleteExamen(@Param("id") Long id);

    @Modifying
    @Query("DELETE FROM Examen e WHERE e.IdExam = :idEXam")
    void reset_salle_Examen(@Param("idEXam") Long idEXam);



    @Modifying
    @Query("UPDATE Examen e SET e.dureeReelle = :dureeReelle WHERE e.Esession = :session AND e.semestre = :semestre AND e.date= :date")
    void saisirDureeReelle(@Param("date") String date,
                           @Param("semestre") Semestre semestre,
                           @Param("session") Session session);


    @Query("SELECT e FROM Examen e WHERE e.IdExam= :idExam")
    Examen findExamById(@Param("idExam") Long idExam);


}
