package com.Academia.GestionExamen.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "Élément_Pédagogique")
public class ElementPedagogique {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    @Column(name = "id élément pédagogique")
    private Long idEP;


    @Column(name="titre_élément_pédagogique",nullable = false)
    private String titre ;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_niveau")
    private Niveau niveau ;



    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="TypeElémentPédagogique")
    private TypeElementPedagogique typeElementPedagogique ;
    public ElementPedagogique(String titre, Niveau niveau, Enseignant enseignant) {
        this.titre = titre;
        this.niveau = niveau;
    }

    public ElementPedagogique() {}

    public Long getIdEP() {
        return idEP;
    }
    public void setIdEP(Long idEP) {
        this.idEP = idEP;
    }
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public Niveau getNiveau() {
        return niveau;
    }
    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }
    public TypeElementPedagogique getTypeElementPedagogique() {
        return typeElementPedagogique;
    }
    public void setTypeElementPedagogique(TypeElementPedagogique typeElementPedagogique) {
        this.typeElementPedagogique = typeElementPedagogique;
    }
    @Override
    public String toString() {
        return "ElementPedagogique{" +
                "idEP=" + idEP +
                ", titre='" + titre + '\'' +
                ", niveau=" + niveau.toString() +
                ", typeElementPedagogique=" + typeElementPedagogique.toString() +
                '}';
    }
}


