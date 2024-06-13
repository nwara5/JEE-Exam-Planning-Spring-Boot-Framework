package com.Academia.GestionExamen.entities;

import jakarta.persistence.*;

@Entity
public class ExamenRapport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRapport ;


    @OneToOne
    @JoinColumn(name = "idExamen")
    private Examen examen ;

    private String pathRapport ;

    public ExamenRapport(Long idRapport, Examen examen, String pathRapport) {
        this.idRapport = idRapport;
        this.examen = examen;
        this.pathRapport = pathRapport;
    }

    public ExamenRapport() {

    }


    public Long getIdRapport() {
        return idRapport;
    }

    public void setIdRapport(Long idRapport) {
        this.idRapport = idRapport;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }

    public String getPathRapport() {
        return pathRapport;
    }

    public void setPathRapport(String pathRapport) {
        this.pathRapport = pathRapport;
    }
}
