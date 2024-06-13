package com.Academia.GestionExamen.entities;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Salle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSalle;

    @Column(nullable = false,unique = false)
    private String NomSalle ;

    @Column(nullable = false,unique = false)
    private int Capacite ;

    @ManyToMany(cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
    @JoinTable(name = "Salle_surveillant",
            joinColumns = @JoinColumn(name = "id_salle"),
            inverseJoinColumns = @JoinColumn(name = "id_surveillant"))
    private Set<Surveillant> surveillants = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "Salle_Admin",
            joinColumns = @JoinColumn(name = "id_salle"),
            inverseJoinColumns = @JoinColumn(name = "id_admin"))
    private Set<Administrateur> admins ;

    @ManyToMany(mappedBy = "salles", cascade = CascadeType.ALL)
    private Set<Examen> examens = new HashSet<>();


    public Salle(int Cap , String NomSalle) {
        this.Capacite = Cap ;
        this.NomSalle = NomSalle;
    }

    public Salle() {}



    public String getNomSalle() {
        return NomSalle;
    }

    public Long getIdSalle() {return idSalle;}

    public Set<Examen> getExamens() {
        return examens;
    }

    public void setExamens(Set<Examen> examens) {
        this.examens = examens;
    }

    public int getCapacite() {return Capacite;}

    public void setIdSalle(Long idSalle) {this.idSalle = idSalle;}

    public void setCapacite(int capacite) {Capacite = capacite;}

    public Set<Surveillant> getSurveillants() {
        return surveillants;
    }

    public void setSurveillants(Set<Surveillant> surveillants) {
        this.surveillants = surveillants;
    }

    public void setIdDep(String newNomSalle) {
        this.NomSalle = NomSalle ;
    }

    public void setNomDep(int newCap) {
        this.Capacite = newCap;
    }

    public void setNomSalle(String nomSalle) {
        NomSalle = nomSalle;
    }

    public Set<Administrateur> getAdmins() {
        return admins;
    }

    public void setAdmins(Set<Administrateur> admins) {
        this.admins = admins;
    }

    @Override
    public String toString() {
        return "Salle{" +
                "idSalle=" + idSalle +
                ", NomSalle='" + NomSalle + '\'' +
                ", Capacite=" + Capacite +
                '}';
    }
}
