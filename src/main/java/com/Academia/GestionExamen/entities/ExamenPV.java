package com.Academia.GestionExamen.entities;

import jakarta.persistence.*;

@Entity
public class ExamenPV {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPv ;


    @OneToOne
    @JoinColumn(name = "idExamen")
    private Examen examen ;

    public ExamenPV(Examen examen, String path) {
        this.examen = examen;
        this.pathPV = path;
    }

    private String pathPV ;

    public ExamenPV() {}

    public Long getIdPv() {
        return idPv;
    }

    public void setIdPv(Long idPv) {
        this.idPv = idPv;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }

    public String getPathPV() {
        return pathPV;
    }

    public void setPathPV(String pathPV) {
        this.pathPV = pathPV;
    }
}
