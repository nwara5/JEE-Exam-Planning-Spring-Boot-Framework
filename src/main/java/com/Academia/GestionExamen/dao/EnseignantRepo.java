package com.Academia.GestionExamen.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.Academia.GestionExamen.entities.Departement;
import com.Academia.GestionExamen.entities.Enseignant;
import com.Academia.GestionExamen.entities.Filiere;

public interface EnseignantRepo  extends JpaRepository<Enseignant, Long> {

	@Modifying
	@Query("UPDATE Enseignant  e SET e.nom = :newNom WHERE e.nom = :oldNom")
	void updateNom(@Param("oldNom") String oldNom, @Param("newNom") String newNom);

	@Modifying
	@Query("UPDATE Enseignant  e SET e.prenom = :newPrenom WHERE e.prenom = :oldPrenom")
	void updatePrenom(@Param("oldPrenom") String oldPrenom, @Param("newPrenom") String newPrenom);

	@Modifying
	@Query("UPDATE Enseignant  e SET e.nom = :newNom, e.prenom = :newPrenom , e.filiere= :newFiliere WHERE e.nom = :oldNom AND e.prenom = :oldPrenom")
	void updateP(@Param("oldNom") String oldNom, @Param("oldPrenom") String oldPrenom, @Param("newNom") String newNom, @Param("newPrenom") String newPrenom, @Param("newFiliere") String newfiliere);


	@Query(value = "SELECT Id_Personnel FROM Enseignant e ORDER BY RANDOM() OFFSET 0 LIMIT :number", nativeQuery = true)
	Set<Long>  grpRandom(@Param("number") int number);

	@Query(value = "SELECT Id_Personnel FROM Enseignant e WHERE e.id_filiere = :filiereId ORDER BY RANDOM() LIMIT :number", nativeQuery = true)
	Set<Long> grpParFiliere(@Param("filiereId") Long filiereId,@Param("number") int number);
	@Query(value = "SELECT Id_Personnel FROM Enseignant e WHERE e.id_département = :departement ORDER BY RANDOM()  LIMIT :number", nativeQuery = true)
	Set<Long> grpParDepartement(@Param("departement")  Long  departementId, @Param("number") int number);

	@Modifying
	@Query("DELETE FROM Enseignant e WHERE e.nom = :firstName AND e.prenom = :lastName")
	void deleteByNameAndLastName(@Param("firstName")String firstName, @Param("lastName")String lastName);

	@Modifying
	@Query("delete from Enseignant e where e.idPers =:idperso")
	void delete(@Param("idperso") long id);




}

