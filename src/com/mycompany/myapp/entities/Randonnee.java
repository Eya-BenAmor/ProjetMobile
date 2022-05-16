/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author MSI
 */
public class Randonnee {
     private int id;
     private float prix;
    private String nomRando,description,destination,dureeRando,categorieRando;
    private String dateRando;
    private float note;

    public Randonnee(int id) {
        this.id = id;
    }

    
public Randonnee() {
    }

    public Randonnee(float prix, String nomRando, String description, String destination, String dureeRando, String categorieRando, String dateRando, float note) {
        this.prix = prix;
        this.nomRando = nomRando;
        this.description = description;
        this.destination = destination;
        this.dureeRando = dureeRando;
        this.categorieRando = categorieRando;
        this.dateRando = dateRando;
        this.note = note;
    }

    public Randonnee(float prix, String nomRando, String description, String destination, String dureeRando, String categorieRando, String dateRando) {
        this.prix = prix;
        this.nomRando = nomRando;
        this.description = description;
        this.destination = destination;
        this.dureeRando = dureeRando;
        this.categorieRando = categorieRando;
        this.dateRando = dateRando;
    }

    public Randonnee(int id, float prix, String nomRando, String description, String destination, String dureeRando, String categorieRando, String dateRando) {
        this.id = id;
        this.prix = prix;
        this.nomRando = nomRando;
        this.description = description;
        this.destination = destination;
        this.dureeRando = dureeRando;
        this.categorieRando = categorieRando;
        this.dateRando = dateRando;
    }
   

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

  

    

    public void setId(int id) {
        this.id = id;
    }

    public void setNomRando(String nomRando) {
        this.nomRando = nomRando;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDureeRando(String dureeRando) {
        this.dureeRando = dureeRando;
    }

    public void setDateRando(String dateRando) {
        this.dateRando = dateRando;
    }

    public int getId() {
        return id;
    }

    public String getNomRando() {
        return nomRando;
    }

    public String getDescription() {
        return description;
    }

    public String getDestination() {
        return destination;
    }

    public String getDureeRando() {
        return dureeRando;
    }

    public String getDateRando() {
        return dateRando;
    }

    @Override
    public String toString() {
        return "Randonnee{" + "prix=" + prix + ", nomRando=" + nomRando + ", description=" + description + ", destination=" + destination + ", dureeRando=" + dureeRando + ", categorieRando=" + categorieRando + ", dateRando=" + dateRando + ", note=" + note + '}';
    }

  
  

   

    public String getCategorieRando() {
        return categorieRando;
    }

    public void setCategorieRando(String categorieRando) {
        this.categorieRando = categorieRando;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

   
   
  

}
