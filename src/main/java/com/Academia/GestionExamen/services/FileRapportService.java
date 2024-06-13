package com.Academia.GestionExamen.services;

import com.Academia.GestionExamen.dao.FileRapportRepo;
import com.Academia.GestionExamen.entities.ExamenPV;
import com.Academia.GestionExamen.entities.ExamenRapport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileRapportService {
    @Autowired
    private FileRapportRepo fileRapportRepo ;

    public void saveRapport(ExamenRapport examenRapport){
        fileRapportRepo.save(examenRapport);}

}
