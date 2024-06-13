package com.Academia.GestionExamen.dao;

import com.Academia.GestionExamen.entities.ExamenPV;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilePVRepo extends JpaRepository<ExamenPV,Long> {
}
