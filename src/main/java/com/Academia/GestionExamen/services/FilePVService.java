package com.Academia.GestionExamen.services;

import com.Academia.GestionExamen.dao.FilePVRepo;
import com.Academia.GestionExamen.entities.ExamenPV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilePVService {

    @Autowired
    private FilePVRepo filePVRepo;

    public void savePV(ExamenPV examenPV){
        filePVRepo.save(examenPV);}


}
