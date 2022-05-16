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
public class Participant {
     private int id,age,user_id,randonnee_id;

    private String tel,classe,maladie;

    public void setMaladie(String maladie) {
        this.maladie = maladie;
    }

    public String getMaladie() {
        return maladie;
    }

    public Participant() {
       
    }

    public int getAge() {
        return age;
    }

    public String getTel() {
        return tel;
    }

    public String getClasse() {
        return classe;
    }

    public int getUser_id() {
        return user_id;
    }

    public Participant(int age, String tel, String classe, int user_id, int randonnee_id) {
        this.age = age;
        this.tel = tel;
        this.classe = classe;
        this.user_id = user_id;
        this.randonnee_id = randonnee_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getRandonnee_id() {
        return randonnee_id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setRandonnee_id(int randonnee_id) {
        this.randonnee_id = randonnee_id;
    }

    @Override
    public String toString() {
        return "Participant{" + "age=" + age + ", user_id=" + user_id + ", randonnee_id=" + randonnee_id + ", tel=" + tel + ", classe=" + classe + ", maladie=" + maladie + '}';
    }

  
  

    public Participant(int age, String tel, String classe, String maladie) {
        this.age = age;
        this.tel = tel;
        this.classe = classe;
        this.maladie = maladie;
    }

    public Participant(int age, String tel,String maladie ,String classe, int randonnee_id, int user_id) {
        this.age = age;
        this.user_id = user_id;
        this.randonnee_id = randonnee_id;
        this.tel = tel;
        this.classe = classe;
        this.maladie = maladie;
    }

    public Participant(int id, int age, int user_id, int randonnee_id, String tel, String classe, String maladie) {
        this.id = id;
        this.age = age;
        this.user_id = user_id;
        this.randonnee_id = randonnee_id;
        this.tel = tel;
        this.classe = classe;
        this.maladie = maladie;
    }
    
   
}
