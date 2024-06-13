package com.Academia.GestionExamen.dao;

import  com.Academia.GestionExamen.entities.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonnelRepo extends JpaRepository<Personnel, Long> {
    
}

