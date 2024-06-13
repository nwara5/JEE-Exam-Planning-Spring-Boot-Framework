package com.Academia.GestionExamen.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Idsession ;

    @Column(nullable = false)
    private String NomSession ;

    @OneToMany(mappedBy="Esession", cascade = CascadeType.ALL)
    private Set<Examen> examens = new HashSet<>();

    public Session(String titre) {NomSession = titre;}

    public Session() {}

    public Long getIdsession() {return Idsession;}

    public String getNomSession() {return NomSession;}

    public void setExamens(Set<Examen> examens) {this.examens = examens;}

    public Set<Examen> getExamens() {return examens;}

    public void setIdsession(Long idsession) {Idsession = idsession;}

    @Override
    public String toString() {
        return NomSession;
    }

    public void setNomSession(String titre) {NomSession = titre;}
}
