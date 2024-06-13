package com.Academia.GestionExamen.dao;

import com.Academia.GestionExamen.entities.ExamenRapport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRapportRepo extends JpaRepository<ExamenRapport, Long> {
}
