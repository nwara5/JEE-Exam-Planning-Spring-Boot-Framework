package com.Academia.GestionExamen.entities;

import jakarta.persistence.*;

@Entity
public class ExamenEnonce {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEnonce ;


    @OneToOne
    @JoinColumn(name = "idExamen")
    private Examen examen ;

    private String pathEnonce;
    public ExamenEnonce(Long idEnonce, Examen examen) {
        this.idEnonce = idEnonce;
        this.examen = examen;
    }

    public ExamenEnonce() {

    }

    public String getPathEnonce() {
        return pathEnonce;
    }

    public void setPathEnonce(String pathEnonce) {
        this.pathEnonce = pathEnonce;
    }

    public Long getIdEnonce() {
        return idEnonce;
    }

    public void setIdEnonce(Long idEnonce) {
        this.idEnonce = idEnonce;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }
}
