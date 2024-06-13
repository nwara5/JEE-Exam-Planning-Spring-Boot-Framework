package com.Academia.GestionExamen.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name="GroupeDesEnseignants")
public class Groupe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGroupeEnseignant ;

    private String nomGrp ;
    
    private int nbrMembre ;

    @ManyToMany(mappedBy = "Groupes", cascade = CascadeType.ALL)
    private Set<Enseignant> enseignants = new HashSet<>();

    public Groupe(String nomGrp, int nbrMembre) {
        this.nomGrp = nomGrp;
        this.nbrMembre = nbrMembre ;
    }

    public Groupe() {}

    public Long getId() {
        return idGroupeEnseignant;
    }

    public String getNomGrp() {
        return nomGrp;
    }

    public void setId(Long id) {
        this.idGroupeEnseignant = id;
    }

    public void setNomGrp(String nomGrp) {
        this.nomGrp = nomGrp;
    }
    
    public int getNbrMembre() {return nbrMembre;}

    public void setIdGroupeEnseignant(Long idGroupeEnseignant) {this.idGroupeEnseignant = idGroupeEnseignant;}

    public void setEnseignants(Set<Enseignant> enseignants) {this.enseignants = enseignants;}

    public Long getIdGroupeEnseignant() {return idGroupeEnseignant;}

    @Override
    public String toString() {
        return "GroupeEnseignants{" +
                "idGroupeEnseignant=" + idGroupeEnseignant +
                ", nomGrp='" + nomGrp + '\'' +
                ", nbrMembre=" + nbrMembre +
                ", enseignants=" + enseignants.toString() +
                '}';
    }

    public Set<Enseignant> getEnseignants() {return enseignants;}

    public void setNbrMembre(int newNbrMembre) {this.nbrMembre = newNbrMembre;}

    public void addEnseignant(Enseignant enseignant) {
        this.enseignants.add(enseignant);
        enseignant.getGroupes().add(this);
}}
