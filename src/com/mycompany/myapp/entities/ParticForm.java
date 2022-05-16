/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author seifi
 */
public class ParticForm {
    private int id,age,Numero,id_formation;
    private String nom,prenom,exp,so_domaine,so_ass;

    public ParticForm(int id, int age, int Numero, String nom, String prenom, String exp, String so_domaine, String so_ass) {
        this.id = id;
        this.age = age;
        this.Numero = Numero;
        this.nom = nom;
        this.prenom = prenom;
        this.exp = exp;
        this.so_domaine = so_domaine;
        this.so_ass = so_ass;
    }
    
    
    public ParticForm( int age, int Numero, String nom, String prenom, String exp, String so_domaine, String so_ass ,int id_formation) {
        this.age = age;
        this.Numero = Numero;
        this.nom = nom;
        this.prenom = prenom;
        this.exp = exp;
        this.so_domaine = so_domaine;
        this.so_ass = so_ass;
        this.id_formation = id_formation;
    }
     public ParticForm( int age, int Numero, String nom, String prenom, String exp, String so_domaine, String so_assn) {
        this.age = age;
        this.Numero = Numero;
        this.nom = nom;
        this.prenom = prenom;
        this.exp = exp;
        this.so_domaine = so_domaine;
        this.so_ass = so_ass;
        
    }

    public ParticForm() {
       
    }

    
    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public int getNumero() {
        return Numero;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getExp() {
        return exp;
    }

    public String getSo_domaine() {
        return so_domaine;
    }

    public String getSo_ass() {
        return so_ass;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setNumero(int Numero) {
        this.Numero = Numero;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public void setSo_domaine(String so_domaine) {
        this.so_domaine = so_domaine;
    }

    public void setSo_ass(String so_ass) {
        this.so_ass = so_ass;
    }

    public void setId_formation(int id_formation) {
        this.id_formation = id_formation;
    }

    public int getId_formation() {
        return id_formation;
    }

    @Override
    public String toString() {
        return "ParticForm{" + "age=" + age + ", Numero=" + Numero + ", nom=" + nom + ", prenom=" + prenom + ", exp=" + exp + ", so_domaine=" + so_domaine + ", so_ass=" + so_ass + '}';
    }
    
}
