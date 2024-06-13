package com.Academia.GestionExamen.dao;

import com.Academia.GestionExamen.entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementRepo extends JpaRepository<Departement, Long> {
}
