package com.Academia.GestionExamen.services;

import com.Academia.GestionExamen.entities.Groupe;
import com.Academia.GestionExamen.entities.Surveillant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Academia.GestionExamen.dao.AdminRepo;
import com.Academia.GestionExamen.entities.Administrateur;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class AdministrateurService {
    
	@Autowired
	private final AdminRepo adminRepo ;

    public AdministrateurService(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }
    
    //Ajouter un administrateur
    public  void save(String nom, String prenom) {
    	Administrateur admin = new Administrateur();
    	admin.setNom(nom);
    	admin.setPrenom(prenom);
    	adminRepo.save(admin);
    	System.out.println("admin inserted successfully!");
    }    	
    
    //Modifier le nom 
    public  void updateNom(String nom, String newnom) {
    	adminRepo.updateNom( nom, newnom);
    	System.out.println("Updated successfully!");
    }

    public Administrateur findAdminById(Long id){return  adminRepo.findAdminById(id);}
    
  //Modifier le prenom 
    public  void updatePrenom(String prenom, String newPrennom) {
    	adminRepo.updatePrenom(prenom, newPrennom);
    	System.out.println("Updated successfully!");
    }

    //Supprimer un administrateur par idAdmin
    public void delete(Long idAdmin) {adminRepo.deleteById(idAdmin);}

    @Transactional
    public Set<Administrateur> findAllAdmin(){return  adminRepo.findAllAdmin();}

    public Set<Administrateur> fetch3AdminAleatoirement(int seuil) {
        // Fetch all administrators
        Set<Administrateur> administrateurs = this.findAllAdmin();

        // Filter administrators based on the seuilSurveillance condition
        List<Administrateur> filteredAdministrateurs = administrateurs.stream()
                .filter(administrateur -> administrateur.getNbrControle() <= seuil)
                .collect(Collectors.toList());

        // Shuffle the list to ensure randomness
        Collections.shuffle(filteredAdministrateurs);

        // Select up to 3 administrators from the shuffled list
        Set<Administrateur> selectedAdministrateurs = filteredAdministrateurs.stream()
                .limit(3)
                .collect(Collectors.toSet());

        // Update the surveillance count for each selected administrator
        selectedAdministrateurs.forEach(administrateur ->
                administrateur.setNbrControle(administrateur.getNbrControle() + 1)
        );

        return selectedAdministrateurs;
    }

    public void save(Administrateur admin){
        adminRepo.save(admin);
    }
    public void deletebyid(Long idAdmin) {adminRepo.deleteById(idAdmin);}

    public List<Administrateur> afficheradmin(){
        return adminRepo.findAll();};

    }

    
 
