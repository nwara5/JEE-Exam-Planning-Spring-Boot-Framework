package com.Academia.GestionExamen.services;

import com.Academia.GestionExamen.dao.SalleRepo;
import com.Academia.GestionExamen.entities.Administrateur;
import com.Academia.GestionExamen.entities.Salle;
import com.Academia.GestionExamen.entities.Surveillant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SalleService {

    @Autowired
    private SalleRepo salleRepo ;
    @Autowired
    public SalleService(SalleRepo salleRepo) {
        this.salleRepo = salleRepo;
    }

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Salle> findById(Long idsalle){
        return salleRepo.findById(idsalle);
    }
    @Transactional
    public void update(Salle salle){
        salleRepo.save(salle);
    }
    @Transactional
    public List<Salle> findAllSalles(){
        return salleRepo.findAll();
    }


    public Salle getSalleByNom(String nomSalle){
        return salleRepo.getSallesByNom(nomSalle);
    }



    public void affecterSalleSurv(Salle salle, Set<Surveillant> surveillants){salle.setSurveillants(surveillants);}

    public void affecterSalleAdmin(Salle salle, Administrateur administrateur){
        salle.getAdmins().clear();
        salle.getAdmins().add(administrateur);}
}
