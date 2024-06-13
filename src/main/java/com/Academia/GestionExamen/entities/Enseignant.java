package com.Academia.GestionExamen.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Enseignant")
@DiscriminatorValue("Enseignant")
public class Enseignant extends Personnel{
    public Enseignant(String nom, String prenom, Filiere filiere, Departement département) {
        super(nom, prenom);
        this.filiere = filiere ;
        this.département = département;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "GroupeEnseignants",
            joinColumns = @JoinColumn(name = "IdEnseignant"),
            inverseJoinColumns = @JoinColumn(name = "IdGroupe")
    )
    private Set<Groupe> Groupes = new HashSet<>();

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name="idDépartement")
    private Departement département ;

    @ManyToOne ( cascade = CascadeType.ALL)
    @JoinColumn(name="idFiliere")
    private Filiere filiere ;


    @OneToMany(mappedBy = "enseignant",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Examen> examen ;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "enseignant")
    private Set<ElementPedagogique> EPs = new HashSet<>();



    public Enseignant() {}

    public Set<Groupe> getGroupes() {
        return Groupes;
    }

    public void setGroupes(Set<Groupe> groupes) {
        Groupes = groupes;
    }

    public Set<ElementPedagogique> getEPs() {
        return EPs;
    }

    public void setEPs(Set<ElementPedagogique> EPs) {
        this.EPs = EPs;
    }


    public Departement getDépartement() {
        return département;
    }

    public void setDépartement(Departement département) {
        this.département = département;
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    public Set<Examen> getExamen() {return examen;}

    public void setExamen(Set<Examen> examen) {this.examen = examen;}


    @Override
    public String toString() {
        return "Enseignant{" +
                "departement=" + département.toString() +
                ", filiere=" + filiere.toString() +
                ", idPers=" + idPers +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }
}
