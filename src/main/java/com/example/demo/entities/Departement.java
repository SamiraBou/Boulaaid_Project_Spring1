package com.example.demo.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="departements")
public class Departement {
    @Id   // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @NotBlank(message = "required")
    @Column(name = "type")
    private String type;
    @NotNull
    @NotBlank(message = "required")
    @Column(name = "nom")
    private String nom;

    public Departement() {
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getNom() {
        return nom;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
