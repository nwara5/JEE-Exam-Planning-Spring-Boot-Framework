package com.Academia.GestionExamen.services;

import com.Academia.GestionExamen.dao.ElementPedagogiqueRepo;
import com.Academia.GestionExamen.entities.ElementPedagogique;
import com.Academia.GestionExamen.entities.Enseignant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElementPedagogiqueService {

    @Autowired
    private ElementPedagogiqueRepo elementPedagogiqueRepo ;

    public ElementPedagogiqueService(ElementPedagogiqueRepo elementPedagogiqueRepo) {
        this.elementPedagogiqueRepo = elementPedagogiqueRepo;
    }

    //Ajouter element perdagogique
    public void createEP(ElementPedagogique ep){
        elementPedagogiqueRepo.save(ep);
    }

    //Supprimer element pedagogique
    public void deleteEP(ElementPedagogique ep){
        elementPedagogiqueRepo.delete(ep);
    }

    //Modifier le titre element perdagogique
    public void modifierEP(String oldTitre, String newTitre){
        elementPedagogiqueRepo.modifierTitreEP(oldTitre, newTitre);
    }

}
