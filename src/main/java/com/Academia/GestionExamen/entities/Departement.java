package com.Academia.GestionExamen.entities;


import jakarta.persistence.*;

import java.util.Set;

@Entity(name = "Département")
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdDépartement ;

    @Column(nullable = false)
    private String NomDépartement ;

    @OneToMany(mappedBy = "département", cascade = CascadeType.ALL)
    private Set<Enseignant> enseignants ;

    public Departement(String nomDépartement) {
        NomDépartement = nomDépartement;
    }

    public Departement() {

    }

    public Long getIdDépartement() {
        return IdDépartement;
    }

    public void setIdDépartement(Long idDépartement) {
        IdDépartement = idDépartement;
    }

    public String getNomDépartement() {
        return NomDépartement;
    }

    public void setNomDépartement(String nomDépartement) {
        NomDépartement = nomDépartement;
    }

    public Set<Enseignant> getEnseignants() {
        return enseignants;
    }

    public void setEnseignants(Set<Enseignant> enseignants) {
        this.enseignants = enseignants;
    }

    @Override
    public String toString() {
        return "Département{" +
                "IdDep=" + IdDépartement +
                ", NomDep='" + NomDépartement + '\'' +
                '}';
    }
}
