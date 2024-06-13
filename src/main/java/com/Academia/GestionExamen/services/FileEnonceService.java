package com.Academia.GestionExamen.services;

import com.Academia.GestionExamen.dao.FileEnonceRepo;
import com.Academia.GestionExamen.entities.ExamenEnonce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileEnonceService {

    @Autowired
    private FileEnonceRepo fileEnonceRepo ;

    public void saveEnonce(ExamenEnonce examenEnonce){
        fileEnonceRepo.save(examenEnonce);}
}
