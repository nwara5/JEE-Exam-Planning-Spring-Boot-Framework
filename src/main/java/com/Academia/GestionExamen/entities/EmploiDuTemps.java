package com.Academia.GestionExamen.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class EmploiDuTemps {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmT;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String margeTemps;

    @ManyToMany(mappedBy = "empts", cascade = CascadeType.ALL)
    private Set<Surveillant> surveillants = new HashSet<>();


    public EmploiDuTemps() {
    }

    public EmploiDuTemps(Long idEmT, String date, String margeTemps, Set<Surveillant> enseignant) {
        this.idEmT = idEmT;
        this.date = date;
        this.margeTemps = margeTemps;
        this.surveillants = enseignant;
    }

    public Long getIdEmT() {
        return idEmT;
    }

    public void setIdEmT(Long idEmT) {
        this.idEmT = idEmT;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMargeTemps() {
        return margeTemps;
    }

    public void setMargeTemps(String margeTemps) {
        this.margeTemps = margeTemps;
    }

    public Set<Surveillant> getSurveillants() {
        return surveillants;
    }

    public void setSurveillants(Set<Surveillant> surveillants) {
        this.surveillants = surveillants;
    }
}