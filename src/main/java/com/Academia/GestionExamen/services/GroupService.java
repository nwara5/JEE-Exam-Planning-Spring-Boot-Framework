package com.Academia.GestionExamen.services;

import java.util.List;
import java.util.Set;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import com.Academia.GestionExamen.dao.GroupRepo;
import com.Academia.GestionExamen.entities.Enseignant;
import com.Academia.GestionExamen.entities.Groupe;

@Service
public class GroupService {
	
	@Autowired
	private final GroupRepo grouprepo ;
	
    public GroupService(GroupRepo grouprepo) {
        this.grouprepo = grouprepo;
    }
    public void createGrp(String nom, int number,Set<Enseignant> CollectionEns) {
    	Groupe grpEnseignants = new Groupe(nom,number);
    	grpEnseignants.setEnseignants(CollectionEns);
        grouprepo.save(grpEnseignants);
    	System.out.println("groupe inserted successfully!");
    }

    public List<Groupe> findAllGroupes(){return grouprepo.findAllGroupes();}



    public Groupe findbyid(long id){
        return grouprepo.findById(id).orElseThrow(() -> new EntityNotFoundException("GroupeEnseignants not found with id: " + id));
    }

    @Transactional
    public void Update(Groupe cgrp ){
        grouprepo.save(cgrp);
    }

    @Modifying
    @Transactional
    public void delete(Groupe grp) {
        grouprepo.delete(grp);
        System.out.println("groupe deleted successfully!");
    }
}
