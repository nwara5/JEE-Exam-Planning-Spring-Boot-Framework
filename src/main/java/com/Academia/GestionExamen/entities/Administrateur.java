package com.Academia.GestionExamen.entities;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Administrateur")
@DiscriminatorValue("Admin")
public class Administrateur extends Personnel{

    public Administrateur(String nom, String prenom) {
        super(nom, prenom);
    }

    @ManyToMany(mappedBy = "admins")
    private Set<Salle> salles = new HashSet<>();
    private int nbrControle ;

    public Administrateur() {}

    public Set<Salle> getSalles() {
        return salles;
    }

    public void setSalles(Set<Salle> salles) {
        this.salles = salles;
    }

    public int getNbrControle() {
        return nbrControle;
    }

    public void setNbrControle(int nbrControle) {
        this.nbrControle = nbrControle;
    }


    @Override
    public String toString() {
        return "Administrateur{" +
                ", idPers=" + idPers +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }
}
