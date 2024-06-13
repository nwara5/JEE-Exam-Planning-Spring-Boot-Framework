package com.Academia.GestionExamen.services;

import com.Academia.GestionExamen.dao.SemestreRepo;
import com.Academia.GestionExamen.entities.Semestre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SemestreService {

    @Autowired
    private SemestreRepo semestreRepo ;

    public List<Semestre> findAllSemestres(){
        return semestreRepo.findAll();
    }

    public Semestre getSemestreByName(String NomSemestre){
        return semestreRepo.findByTitreSemestre(NomSemestre);
    }
}
