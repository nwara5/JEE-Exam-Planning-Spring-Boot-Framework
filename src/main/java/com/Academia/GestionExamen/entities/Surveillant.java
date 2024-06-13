package com.Academia.GestionExamen.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Surveillant")
@DiscriminatorValue("Surveillant")
public class Surveillant extends Enseignant{


    @ManyToMany(mappedBy = "surveillants",cascade = CascadeType.ALL)
    private Set<Salle> salles ;

    private int nbrSurveillance ;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "EmploiSurveillant",
            joinColumns = @JoinColumn(name = "id_Pers"),
            inverseJoinColumns = @JoinColumn(name = "idTempt"))
    private Set<EmploiDuTemps> empts = new HashSet<>();


    public Surveillant(String nom, String prenom, Filiere filiere, Departement département, Set<Salle> salles, int nbrSurveillance) {
        super(nom, prenom, filiere, département);
        this.salles = salles;

        this.nbrSurveillance = nbrSurveillance;
    }

    public int getNbrSurveillance() {return nbrSurveillance;}

    public Set<EmploiDuTemps> getEmpts() {return empts;}

    public void setEmpts(Set<EmploiDuTemps> empts) {this.empts = empts;}

    public void setNbrSurveillance(int nbrSurveillance) {
        this.nbrSurveillance = nbrSurveillance;
    }

    public Surveillant(Set<Salle> salles) {
        this.salles = salles;
    }

    public Surveillant() {}

    public Set<Salle> getSalles() {
        return salles;
    }

    public void setSalles(Set<Salle> salles) {
        this.salles = salles;
    }

    @Override
    public String toString() {
        return "Surveillant{" +
                ", idPers=" + idPers +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }
}
