
package com.Academia.GestionExamen.dao;

import  com.Academia.GestionExamen.entities.Administrateur;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.Set;

public interface AdminRepo extends JpaRepository<Administrateur, Long> {
	@Modifying
	@Query("UPDATE Administrateur a SET a.nom = :newNom WHERE a.nom = :oldNom")
	void updateNom(@Param("oldNom") String oldNom, @Param("newNom") String newNom);



	@Modifying
	@Query("UPDATE Administrateur a SET a.prenom = :newPrenom WHERE a.prenom = :oldPrenom ")
	void updatePrenom(@Param("oldPrenom") String oldPrenom, @Param("newPrenom") String newPrenom);


	@Query("SELECT a from Administrateur a")
	Set<Administrateur> findAllAdmin();

	@Query("select a from Administrateur a WHERE a.idPers = :id")
	Administrateur findAdminById(@Param("id") Long id);

}
    


