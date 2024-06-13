package com.Academia.GestionExamen.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Academia.GestionExamen.entities.Groupe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface GroupRepo  extends JpaRepository<Groupe, Long> {

    @Query("SELECT g from GroupeDesEnseignants g where g.nomGrp = :nom")
    Groupe getGroupebyName(@Param("nom") String nom);


    @Query("select g from GroupeDesEnseignants g")
    List<Groupe> findAllGroupes();

}
