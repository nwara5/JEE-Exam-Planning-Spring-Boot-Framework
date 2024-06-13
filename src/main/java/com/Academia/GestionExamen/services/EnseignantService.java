package com.Academia.GestionExamen.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


import com.Academia.GestionExamen.dao.DepartementRepo;
import com.Academia.GestionExamen.dao.FiliereRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.Academia.GestionExamen.dao.EnseignantRepo;
import com.Academia.GestionExamen.entities.Departement;
import com.Academia.GestionExamen.entities.Enseignant;
import com.Academia.GestionExamen.entities.Filiere;


@Service
public class EnseignantService {

	@Autowired
	private final EnseignantRepo EnseignantRepo ;
	private FiliereRepo filiereRepo ;
    private DepartementRepo departementRepo ;
    public EnseignantService(EnseignantRepo enseigrepo) {
        this.EnseignantRepo = enseigrepo;
    }

    //Modfier le nom
    public  void updateNom(String nom, String newnom) {
        EnseignantRepo.updateNom( nom, newnom);
        System.out.println("Nom Updated successfully!");
    }

    //Modifier le prenom
    public  void updatePrenom(String prenom, String newPrenom) {
        EnseignantRepo.updatePrenom( prenom, newPrenom);
        System.out.println("Prenom Updated successfully!");
    }

    public void save2(Enseignant enseignant){
        EnseignantRepo.save(enseignant);
    }
    @Modifying
    @Transactional
    public void deleteensei(long id) {

        EnseignantRepo.delete(id);
        System.out.println("deleted successfully!");
    }
    @Transactional
    // Ajouter un enseignant
    public void save(String nom, String prenom, Long filiereId, Long departementId) {
        Filiere filiere = filiereRepo.findById(filiereId)
                .orElseThrow(() -> new IllegalArgumentException("Filiere not found with id: " + filiereId));
        Departement departement = departementRepo.findById(departementId)
                .orElseThrow(() -> new IllegalArgumentException("Departement not found with id: " + departementId));

        Enseignant enseignant = new Enseignant();
        enseignant.setNom(nom);
        enseignant.setPrenom(prenom);
        enseignant.setFiliere(filiere);
        enseignant.setDépartement(departement);

        EnseignantRepo.save(enseignant);
        System.out.println("Inserted successfully!");
    }

    //Creer un groupe des enseignants par filiere
    public Set<Enseignant> grpParFiliere(long filiere,int number) {
        Set<Long> ids = EnseignantRepo.grpParFiliere(filiere ,number);
        Set<Enseignant> ens = new HashSet<Enseignant>();
        for (long i : ids){
            System.out.println(i);
            Enseignant ensei =findById(i);
            ens.add(ensei);
        }
        return ens;
    }

    //Creer un groupe des enseignants par departement
    public Set<Enseignant> grpParDepartement(Departement departement,int number) {
        Set<Long> ids = EnseignantRepo.grpParDepartement(departement.getIdDépartement(),number);
        Set<Enseignant> ens = new HashSet<Enseignant>();
        for (long i : ids){
            Enseignant ensei =findById(i);
            ens.add(ensei);
        }
        return ens;
    }

    //Creer un groupe des enseignants aleatoirement
    public Set<Enseignant> grpRandom(int number) {
        Set<Long> ids = EnseignantRepo.grpRandom(number);
        Set<Enseignant> ens = new HashSet<Enseignant>();
        for (long i : ids){
            Enseignant ensei =findById(i);
            ens.add(ensei);
        }
        return ens;
    }
    public Enseignant findById(long id ){
        return EnseignantRepo.findById(id).orElseThrow(() -> new RuntimeException("Enseignant not found"));
    }

    public List<Enseignant> afficherall() {
        return EnseignantRepo.findAll();
    }
}

