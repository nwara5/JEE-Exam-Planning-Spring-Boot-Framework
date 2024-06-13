package com.Academia.GestionExamen.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
@Entity
@Table(name="Personnel")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "person_type")
public class Personnel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Personnel")
    public Long idPers;

    @NotBlank(message ="this field is required")
    @Column(name="nom", nullable = false, length = 100)
    public String nom;

    @NotBlank(message ="this field is required")
    @Column(name="Prenom", nullable = false, length = 100)
    public String prenom;

    public Personnel (String nom, String prenom) {
        this.nom = nom ;
        this.prenom=prenom ;
    }

    public Personnel() {}

    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }

    public void setNom(String newNom) {
        this.nom =newNom;
    }

    public void setPrenom(String newPrenom) {
        this.prenom = newPrenom;
    }

    public void setIdPers(Long id) {
        this.idPers = id;
    }

    @Override
    public String toString() {
        return "Personnel{" +
                "idPers=" + idPers +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }

    public Long getIdPers() {
        return idPers;
    }
}

