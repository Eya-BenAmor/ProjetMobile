/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author Mezen Bayounes
 */
public class Cadeau {
    private int id;
 private String categorie;
     private String description;
     private String nom;
     private String competition;

    public Cadeau(String nom, String categorie_cadeau, String description_cadeau, String competition) {
        this.categorie = categorie_cadeau;
        this.description= description_cadeau;
        this.nom = nom;
        this.competition = competition;
    }


    
 public String getCategorie() {
        return categorie;
    }
   

    public Cadeau(String nom, String categorie_cadeau, String description_cadeau) {
       this.categorie = categorie_cadeau;
        this.description = description_cadeau;
        this.nom = nom;
    }

    public Cadeau() {
       
    }

    public Cadeau(int id, String categorie, String description) {
        this.id = id;
        this.categorie = categorie;
        this.description = description;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   
    public void setCategorie(String categorie_cadeau) {
        this.categorie = categorie_cadeau;
    }

    public Cadeau(int id,String nom,String categorie_cadeau, String description_cadeau) {
        this.id = id;
        this.categorie = categorie_cadeau;
        this.description = description_cadeau;
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description_cadeau) {
        this.description= description_cadeau;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCompetition() {
        return competition;
    }

    public void setCompetition(String competition) {
        this.competition = competition;
    }
    
}
