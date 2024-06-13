package com.Academia.GestionExamen.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity(name="Type_Élément_Pédagogique")
public class TypeElementPedagogique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idType_EP ;

    @Column(name="type", nullable = false)
    private String type_EP ;

    @OneToMany(mappedBy ="typeElementPedagogique", cascade = CascadeType.ALL)
    private Set<ElementPedagogique> typeElementPedagogique ;

    public TypeElementPedagogique(String titre) {
        this.type_EP = titre;
    }

    public TypeElementPedagogique() {}

    public Long getIdType_EP() {
        return idType_EP;
    }

    public void setIdType_EP(Long idType_EP) {
        this.idType_EP = idType_EP;
    }

    public String getType_EP() {
        return type_EP;
    }

    public void setType_EP(String type_EP) {
        this.type_EP = type_EP;
    }

    public Set<ElementPedagogique> getTypeElementPedagogique() {
        return typeElementPedagogique;
    }

    public void setTypeElementPedagogique(Set<ElementPedagogique> typeElementPedagogique) {
        this.typeElementPedagogique = typeElementPedagogique;
    }

    @Override
    public String toString() {
        return "TypeElementPedagogique{" +
                "idType_EP=" + idType_EP +
                ", type_EP='" + type_EP + '\'' +
                '}';
    }
}
