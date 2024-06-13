package com.Academia.GestionExamen.dao;

import com.Academia.GestionExamen.entities.Surveillant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface SurveillantRepo extends JpaRepository<Surveillant,Long> {



}
