package com.Academia.GestionExamen.services;

import com.Academia.GestionExamen.dao.DepartementRepo;
import com.Academia.GestionExamen.entities.Departement;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class DepartementService {

    @Autowired
    public DepartementRepo departementRepo ;

    @Autowired
    public DepartementService(DepartementRepo departementRepo) {
        this.departementRepo = departementRepo;
    }

    public void createDepartement(Departement dep){
        departementRepo.save(dep);
    }

    public Departement findbyid(long id){
        return departementRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Departement not found with id: " + id));
    }

    public List<Departement> findAllDepartements(){return departementRepo.findAll();}

    public List<Departement> findALL(){
        return departementRepo.findAll();
    };
}
