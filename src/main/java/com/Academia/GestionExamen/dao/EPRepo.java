package com.Academia.GestionExamen.dao;

import com.Academia.GestionExamen.entities.ElementPedagogique;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EPRepo extends JpaRepository<ElementPedagogique,Long> {
}
