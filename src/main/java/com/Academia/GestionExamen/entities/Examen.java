package com.Academia.GestionExamen.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Examen {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long IdExam ;

    @ManyToOne
    @JoinColumn(name="idSemestre")
    private Semestre semestre ;

    @ManyToOne
    @JoinColumn(name="idSession")
    private Session Esession;


    @Column(length=10)
    private Float dureeReelle ;

    @Column(nullable = false, length=10)
    @NotNull(message = "Ce champs est obligatoire")
    private Float dureePrevue ;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotBlank(message = "Ce champs est obligatoire")
    @Column(nullable = false, length=10)
    private String date ;

    @Column(nullable = false, length=10)
    @NotBlank(message = "Ce champs est obligatoire")
    private String heureDebut ;



    @ManyToOne
    @JoinColumn(name = "IdEnsCoordinateur")
    private Enseignant enseignant ;


    @Transient
    private List<String> nomSalles;

    private String typeExamen ;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable( name = "SalleExamen",
    joinColumns = @JoinColumn(name="idExamen"),
    inverseJoinColumns = @JoinColumn(name = "idSalle"))
    private Set<Salle> salles = new HashSet<>();

    @OneToOne(mappedBy = "examen",cascade = CascadeType.ALL)
    private ExamenPV examenPV;

    @OneToOne(mappedBy = "examen",cascade = CascadeType.ALL)
    private ExamenEnonce examenEnonce;

    @OneToOne(mappedBy = "examen",cascade = CascadeType.ALL)
    private ExamenRapport examenRapport;

    public Examen(Long idExam, Semestre semestre, Session esession, Float dureeReelle, Float dureePrevue, String date, String heureDebut, Enseignant enseignant, List<String> nomSalles, String typeExamen, Set<Salle> salles, ExamenPV examenPV, ExamenEnonce examenEnonce, ExamenRapport examenRapport) {
        IdExam = idExam;
        this.semestre = semestre;
        Esession = esession;
        this.dureeReelle = dureeReelle;
        this.dureePrevue = dureePrevue;
        this.date = date;
        this.heureDebut = heureDebut;
        this.enseignant = enseignant;
        this.nomSalles = nomSalles;
        this.typeExamen = typeExamen;
        this.salles = salles;
        this.examenPV = examenPV;
        this.examenEnonce = examenEnonce;
        this.examenRapport = examenRapport;
    }

    public Examen() {}

    public String getTypeExamen() {
        return typeExamen;
    }

    public ExamenPV getExamenPV() {
        return examenPV;
    }

    public void setExamenPV(ExamenPV examenPV) {
        this.examenPV = examenPV;
    }

    public ExamenEnonce getExamenEnonce() {
        return examenEnonce;
    }

    public void setExamenEnonce(ExamenEnonce examenEnonce) {
        this.examenEnonce = examenEnonce;
    }

    public ExamenRapport getExamenRapport() {
        return examenRapport;
    }

    public void setExamenRapport(ExamenRapport examenRapport) {
        this.examenRapport = examenRapport;
    }

    public void setFileStorage(ExamenPV examenPV) {
        this.examenPV = examenPV;
    }

    public void setTypeExamen(String typeExamen) {
        this.typeExamen = typeExamen;
    }

    public List<String> getNomSalles() {
        return nomSalles;
    }

    public void setNomSalles(List<String> nomSalles) {
        this.nomSalles = nomSalles;
    }

    public Long getIdExam() {
        return IdExam ;
    }
    public Float getDureeReelle() {
        return dureeReelle ;
    }

    public String getDate() {
        return date;
    }

    public Float getDureePrevue() {
        return dureePrevue;
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public void setIdExam(Long newId) {
        this.IdExam= newId ;
    }

    public Session getEsession() {
        return Esession;
    }

    public void setEsession(Session esession) {
        Esession = esession;
    }

    public void setDureeReelle(Float newVal) {this.dureeReelle =newVal;}

    public void setDureePrevue(Float newVal) {this.dureePrevue =newVal;}

    public void setDate(String newDate) {this.date= newDate;}

    public void setHeureDebut(String newH) {this.heureDebut = newH ;}

    public Semestre getSemestre() {
        return semestre;
    }

    public Session getESession() {
        return Esession;
    }


    public Enseignant getEnseignant() {
        return enseignant;
    }


    public Set<Salle> getSalles() {
        return salles;
    }


    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
    }

    public void setESession(Session session) {
        this.Esession = session;
    }


    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }


    public void setSalles(Set<Salle> salles) {
        this.salles = salles;
    }


    @Override
    public String toString() {
        return "Examen{" +
                "IdExam=" + IdExam +
                ", semestre=" + semestre.toString() +
                ", session=" + Esession.toString() +
                ", dureePrevue=" + dureePrevue +
                ", date=" + date +
                ", heureDebut=" + heureDebut +
                '}';
    }

}
