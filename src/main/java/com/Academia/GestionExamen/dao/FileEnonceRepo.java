package com.Academia.GestionExamen.dao;

import com.Academia.GestionExamen.entities.ExamenEnonce;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileEnonceRepo extends JpaRepository<ExamenEnonce, Long> {
}
