package com.Academia.GestionExamen.services;

import com.Academia.GestionExamen.dao.FiliereRepo;
import com.Academia.GestionExamen.entities.Filiere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class FiliereService {

    @Autowired
    public FiliereRepo filiereRepo ;
    @Autowired
    public FiliereService(FiliereRepo filiereRepo) {
        this.filiereRepo = filiereRepo;
    }

    public void createFiliere(Filiere filiere){
        filiereRepo.save(filiere);
    }

    public void deleteFiliere(Filiere filiere){filiereRepo.delete(filiere);}

    public void modifyFiliere(String old, String newt){filiereRepo.modifyFiliere(old,newt);}


    public List<Filiere> findAllFilieres(){return filiereRepo.findAll();}


    public Filiere findByTitreFiliere(String NomFiliere){
        return filiereRepo.findByTitreFiliere(NomFiliere);
    }

    public Filiere findById(long id){return filiereRepo.findById(id).orElseThrow(() -> new RuntimeException("Filiere not found"));}


}
