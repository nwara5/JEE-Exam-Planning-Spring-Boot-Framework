package com.Academia.GestionExamen.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
public class Niveau {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNiveau ;

    @Column
    private String nomNiveau;

    @OneToMany(mappedBy = "niveau", cascade = CascadeType.ALL)
    private Set<ElementPedagogique> EPs = new HashSet<>();

    public Niveau(Long idNiveau, String nomNiveau) {
        this.idNiveau = idNiveau;
        this.nomNiveau = nomNiveau;
    }


    public Niveau() {}

    public Long getIdNiveau() {
        return idNiveau;
    }

    public String getNomNiveau() {
        return nomNiveau;
    }

    public Set<ElementPedagogique> getEPs() {
        return EPs;
    }

    public void setIdNiveau(Long idNiveau) {
        this.idNiveau = idNiveau;
    }

    public void setNomNiveau(String nomNiveau) {
        this.nomNiveau = nomNiveau;
    }

    public void setEPs(Set<ElementPedagogique> EPs) {
        this.EPs = EPs;
    }

    @Override
    public String toString() {
        return "Niveau{" +
                "idNiveau=" + idNiveau +
                ", nomNiveau='" + nomNiveau + '\'' +
                ", EPs=" + EPs.toString() +
                '}';
    }
}
