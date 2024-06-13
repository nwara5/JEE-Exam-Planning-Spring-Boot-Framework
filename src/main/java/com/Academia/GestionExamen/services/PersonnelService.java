package com.Academia.GestionExamen.services;


import com.Academia.GestionExamen.entities.Departement;
import com.Academia.GestionExamen.entities.Enseignant;
import com.Academia.GestionExamen.entities.Filiere;

import java.util.HashSet;
import java.util.Set;

import com.Academia.GestionExamen.entities.Groupe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;




@Service
public class PersonnelService {

    private final AdministrateurService adminService;
    private final EnseignantService enseignantRepo;
    private final GroupService groupservice;
    private final ElementPedagogiqueService  elementpedagogiqueservice;

    @Autowired
    private EntityManager entityManager;

    //Related to groupe
    @Autowired
    public PersonnelService(AdministrateurService adminServ,  EnseignantService enseigRepo,GroupService groupservice,ElementPedagogiqueService  elementpedagogiqueservice){
        this.adminService = adminServ;
        this.enseignantRepo = enseigRepo;
        this.groupservice = groupservice;
        this.elementpedagogiqueservice=elementpedagogiqueservice;
    }

    @Transactional
    public void addEnseignantToGroup(long idensei,  long idgroupe) {
        Enseignant enseignant = enseignantRepo.findById(idensei);
        Groupe groupe = groupservice.findbyid(idgroupe);
        groupe.addEnseignant(enseignant);
        Set<Enseignant> list =groupe.getEnseignants();
        int length = list.size()+1;
        groupe.setNbrMembre(length);
        entityManager.persist(groupe);
    }
    //Ajouter un administrateur
    public void saveAdministrateur(String nom , String prenom) {
        adminService.save(nom,prenom);
    }



    //Modifier le nom d'un Administrateur
    @Transactional
    public void updateNomAdminstrateur(String nom, String newnom) {
        adminService.updateNom(nom , newnom);
    }

    //Modifier le prenom d'un utilisateur
    @Transactional
    public void updatePrenomAdminstrateur(String prenom, String newPrenom) {
        adminService.updateNom(prenom , newPrenom);
    }

    //generer un groupe des enseignant aleatoirement
    @Transactional
    public void grpRandom(String nom,int number) {
        Set<Enseignant> ens = enseignantRepo.grpRandom(number);
        Set<Enseignant> mergedEns = new HashSet<>();
        for (Enseignant e : ens) {
            System.out.println(e);
            mergedEns.add(entityManager.merge(e));
        }
        groupservice.createGrp(nom, number, mergedEns);
    }

    @Transactional
    public void grpParfiliere(String nom , int number,Filiere filiere) {
        // Retrieve a set of Enseignant entities based on filiere and number
        Set<Enseignant> ens = enseignantRepo.grpParFiliere(filiere.getIdFiliere(), number);

        // Create a new GroupeEnseignants entity
        Groupe grp = new Groupe(nom, number);

        // Add each Enseignant to the GroupeEnseignants
        for (Enseignant e : ens) {
            grp.addEnseignant(e);
        }
        // Persist the GroupeEnseignants entity
        entityManager.persist(grp);
    }

    //generer un groupe des enseignant par departement
    @Transactional
    public void grpParDepartement(String nom , int number,Departement departement) {
        Set<Enseignant> ens = enseignantRepo.grpParDepartement(departement , number);
        groupservice.createGrp(nom,number,ens);
    }

    //Modifier le prenom d'un enseignant
    @Transactional
    public void updatePrenomEnseignant(String prenom,String newprenom) {
        enseignantRepo.updatePrenom(prenom , newprenom);
    }

    //Modifier le nom d'un enseignant
    @Transactional
    public void updateNomEnseignant(String nom,String newNom) {
        enseignantRepo.updateNom(nom , newNom);
    }

    @Transactional
    public Groupe getGroupeEnseignant(Long id) {
        Groupe groupeEnseignants = groupservice.findbyid(id);
        System.out.println(groupeEnseignants.getEnseignants());
        return groupeEnseignants;
    }


}
