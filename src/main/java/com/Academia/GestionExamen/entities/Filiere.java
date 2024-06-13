package com.Academia.GestionExamen.entities;

import jakarta.persistence.*;

import javax.crypto.ExemptionMechanism;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Filiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFiliere ;

    @Column(nullable = false)
    private String nomFiliere ;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="idFiliere")
    public Set<Enseignant> Enseignants = new HashSet<Enseignant>();

    public Filiere (String nomFiliere){
        this.nomFiliere = nomFiliere ;
    }

    @OneToOne
    @JoinColumn(name = "idEnseignantFiliere")
    private Enseignant coordonnateurFiliere;

    public Filiere() {}

    public Long getIdFiliere() {return idFiliere;}

    public String getNomFiliere() {return nomFiliere;}

    public void setIdFiliere(Long idFiliere) {this.idFiliere = idFiliere;}

    public Set<Enseignant> getEnseignants() {return Enseignants;}


    public Enseignant getCoordonnateurFiliere() {
        return coordonnateurFiliere;
    }

    public void setCoordonnateurFiliere(Enseignant coordonnateurFiliere) {
        this.coordonnateurFiliere = coordonnateurFiliere;
    }

    public void setEnseignants(Set<Enseignant> enseignants) {Enseignants = enseignants;}

    @Override
    public String toString() {
        return "Filiere{" +
                "idFiliere=" + idFiliere +
                ", nomFiliere='" + nomFiliere + '\'' +
                ", coordonnateurFiliere=" + coordonnateurFiliere.toString() +
                '}';
    }

    public void setNomFiliere(String nomFiliere) {this.nomFiliere = nomFiliere;}
}


