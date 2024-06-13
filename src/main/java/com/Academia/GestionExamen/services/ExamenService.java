package com.Academia.GestionExamen.services;

import com.Academia.GestionExamen.dao.ExamenRepo;
import com.Academia.GestionExamen.dao.FileRapportRepo;
import com.Academia.GestionExamen.dao.SalleRepo;
import com.Academia.GestionExamen.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ExamenService {
    @Autowired
    private ExamenRepo examenRepo ;

    @Autowired
    private SalleRepo salleRepo;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private SalleService salleService;

    private SurveillantService surveillantService;
    private FilePVService filePVService;
    private FileEnonceService fileEnonceService;
    private FileRapportService fileRapportService;

    @Autowired
    private AdministrateurService administrateurService;

    public ExamenService(ExamenRepo examenRepo, SalleRepo salleRepo, EntityManager entityManager, SalleService salleService, SurveillantService surveillantService, FilePVService filePVService, FileEnonceService fileEnonceService, FileRapportService fileRapportService, AdministrateurService administrateurService) {
        this.examenRepo = examenRepo;
        this.salleRepo = salleRepo;
        this.entityManager = entityManager;
        this.salleService = salleService;
        this.surveillantService = surveillantService;
        this.filePVService = filePVService;
        this.fileEnonceService = fileEnonceService;
        this.fileRapportService = fileRapportService;
        this.administrateurService = administrateurService;
    }

    @Transactional
    public void insertExamen(Examen exam){
        Enseignant enseignant = exam.getEnseignant();
        if (enseignant != null) {
            exam.setEnseignant(enseignant);
        }
        examenRepo.save(exam);
    }
    @Transactional
    public void deleteExamen(Long idExam){
        examenRepo.deleteExamen(idExam);
    }



    @Transactional
    public void affecterSurveillantsRand(Long idExam, Salle salle, Set<Surveillant> surveillants) {
        Examen examen = examenRepo.findExamById(idExam);
        salleService.affecterSalleSurv(salle, surveillants);
        entityManager.detach(salle);
        Salle managedSalle = entityManager.find(Salle.class, salle.getIdSalle());
        if (managedSalle != null) {
            examen.getSalles().add(managedSalle);
        } else {
            examen.getSalles().add(salle);
        }
        entityManager.merge(examen);
    }

    public boolean areSurveillantsDisjoint(Set<Surveillant> surveillantsSalle1, Set<Surveillant> surveillantsSalle2, Set<Surveillant> surveillantsSalle3) {
        Set<Surveillant> intersection12 = new HashSet<>(surveillantsSalle1);
        intersection12.retainAll(surveillantsSalle2);
        Set<Surveillant> intersection13 = new HashSet<>(surveillantsSalle1);
        intersection13.retainAll(surveillantsSalle3);
        Set<Surveillant> intersection23 = new HashSet<>(surveillantsSalle2);
        intersection23.retainAll(surveillantsSalle3);
        return intersection12.isEmpty() && intersection13.isEmpty() && intersection23.isEmpty();
    }

    public boolean areAdminsDisjoint(Set<Administrateur> set1, Set<Administrateur> set2, Set<Administrateur> set3) {
        Set<Administrateur> intersection12 = new HashSet<>(set1);
        intersection12.retainAll(set2);
        Set<Administrateur> intersection13 = new HashSet<>(set1);
        intersection13.retainAll(set3);
        Set<Administrateur> intersection23 = new HashSet<>(set2);
        intersection23.retainAll(set3);
        return intersection12.isEmpty() && intersection13.isEmpty() && intersection23.isEmpty();
    }

    @Transactional
    public void affecterSurveillantsGrp(Long idExam, Salle salle, Set<Surveillant> surveillants) {
        Examen examen = examenRepo.findExamById(idExam);
        salle = entityManager.merge(salle);
        Set<Surveillant> managedSurveillants = new HashSet<>();
        for (Surveillant surveillant : surveillants) {
            managedSurveillants.add(entityManager.find(Surveillant.class, surveillant.getIdPers()));
        }

        salleService.affecterSalleSurv(salle, managedSurveillants);
        examen.getSalles().add(salle);
        entityManager.merge(examen);
    }


    @Transactional
    public void affecterAdminsASalles(Long idExam, Set<Salle> salles, Set<Administrateur> administrateurs) {
        Examen examen = examenRepo.findExamById(idExam);
        Iterator<Salle> salleIterator = salles.iterator();
        Iterator<Administrateur> adminIterator = administrateurs.iterator();

        while (salleIterator.hasNext() && adminIterator.hasNext()) {
            Salle salle = salleIterator.next();
            Administrateur administrateur = adminIterator.next();
            Administrateur managedAdmin = administrateurService.findAdminById(administrateur.getIdPers());

            salleService.affecterSalleAdmin(salle, managedAdmin);
            entityManager.merge(salle);
        }
        entityManager.merge(examen);
    }


    @Transactional
    public List<Examen> listExamens(){
        return examenRepo.findAll();
    }


    @Transactional
    public void updateExamen(Long idExam, Float DureeReelle){
        Examen examen = examenRepo.findExamById(idExam);
        examen.setDureeReelle(DureeReelle);
        examenRepo.save(examen);
    }
    @Transactional
    public Examen findExamById(Long idExam){
        return examenRepo.findExamById(idExam);
    }

    @Transactional
    public void setPVtoExamen(Examen examen, String filepath){
        ExamenPV examenPV = new ExamenPV();
        examenPV.setExamen(examen);
        examenPV.setPathPV(filepath);
        filePVService.savePV(examenPV);

    }
    @Transactional
    public void setEnoncetoExamen(Examen examen, String  filepath){
        ExamenEnonce examenEnonce = new ExamenEnonce();
        examenEnonce.setExamen(examen);
        examenEnonce.setPathEnonce(filepath);
        fileEnonceService.saveEnonce(examenEnonce);
    }

    @Transactional
    public void setRapporttoExamen(Examen examen, String  filepath){
        ExamenRapport examenRapport = new ExamenRapport();
        examenRapport.setExamen(examen);
        examenRapport.setPathRapport(filepath);
        fileRapportService.saveRapport(examenRapport);
    }

}
