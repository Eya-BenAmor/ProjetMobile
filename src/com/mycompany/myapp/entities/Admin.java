/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author MSI
 */
public class Admin {
    
    private int id;
    private String nom,prenom,email,mdp,confrim_mdp;

    public Admin(int id) {
        this.id = id;
    }

    public Admin() {
    }

    public Admin(String nom, String prenom, String email, String mdp, String confrim_mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.confrim_mdp = confrim_mdp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getConfirm_mdp() {
        return confrim_mdp;
    }

    public void setConfirm_mdp(String confrim_mdp) {
        this.confrim_mdp = confrim_mdp;
    }

    @Override
    public String toString() {
                return "User{" + "nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", mdp=" + mdp + ", confrim_mdp=" + confrim_mdp + '}';
    }
    
    
    
}
