package com.Academia.GestionExamen.entities;

import jakarta.persistence.*;
import jdk.jfr.Enabled;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Semestre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdSemestre ;

    @Column(nullable = false)
    private String NomSemestre ;

     @OneToMany(mappedBy="semestre", cascade = CascadeType.ALL)
     private Set<Examen> Examens = new HashSet<Examen>();

    public Semestre(String titre) {this.NomSemestre = titre;}

    public Semestre(){}

    public Long getIdSemestre() {return IdSemestre;}

    public String getNomSemestre() {return NomSemestre;}

    public void setIdSemestre(Long idSemestre) {IdSemestre = idSemestre;}

    public void setNomSemestre(String titre) {this.NomSemestre = titre;}

    public void setExamens(Set<Examen> examens) {Examens = examens;}

    @Override
    public String toString() {
        return NomSemestre ;
    }

    public Set<Examen> getExamens() {return Examens;}
}
