package com.Academia.GestionExamen.dao;

import com.Academia.GestionExamen.entities.ElementPedagogique;
import com.Academia.GestionExamen.entities.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ElementPedagogiqueRepo extends JpaRepository<ElementPedagogique, Long> {

    @Modifying
    @Query("UPDATE Élément_Pédagogique e SET e.titre = :newTitre WHERE e.titre = :oldTitre")
    void modifierTitreEP(@Param("oldTitre") String oldTitre, @Param("newTitre") String newTitre);

}
