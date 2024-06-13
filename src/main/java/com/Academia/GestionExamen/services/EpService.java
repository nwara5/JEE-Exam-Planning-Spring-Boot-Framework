package com.Academia.GestionExamen.services;

import com.Academia.GestionExamen.dao.EPRepo;
import com.Academia.GestionExamen.entities.ElementPedagogique;
import com.Academia.GestionExamen.entities.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpService {

    @Autowired
    private EPRepo epRepo;

    public EpService(EPRepo epRepo) {
        this.epRepo = epRepo;
    }

    public List<ElementPedagogique> findAllEps(){
        return epRepo.findAll();
    }

}
