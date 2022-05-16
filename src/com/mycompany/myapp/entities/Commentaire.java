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
public class Commentaire {

  
     private int id,id_pub;
    private String contenu;

    public Commentaire() {
    }

    public Commentaire(int id, String contenu, int id_pub) {
        this.id = id;
        this.contenu = contenu;
        this.id_pub = id_pub;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_pub() {
        return id_pub;
    }

    public String getContenu() {
        return contenu;
    }

    public void setId_pub(int id_pub) {
        this.id_pub = id_pub;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

  

  

    public Commentaire(String contenu, int id_pub) {
        this.contenu = contenu;
        this.id_pub = id_pub;
    }
    
}
