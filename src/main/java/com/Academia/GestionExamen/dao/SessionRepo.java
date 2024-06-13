package com.Academia.GestionExamen.dao;

import com.Academia.GestionExamen.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SessionRepo extends JpaRepository<Session,Long> {
    @Query("SELECT s FROM Session s WHERE s.NomSession = :titreSession")
    Session findByTitreSession(@Param("titreSession") String titreSession);
}
