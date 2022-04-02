/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author Oussema
 */
public class Users {
    
    private int id;
    private String nom,prenom,email,tel,adresse,ville,cp,login,mdp,img,role,etat;

    
    
     public Users() {
    }
    
     
     
     
    public Users( String nom, String prenom, String email, String tel, String adresse, String ville, String cp, String login, String mdp, String img, String role, String etat) {
       
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
        this.adresse = adresse;
        this.ville = ville;
        this.cp = cp;
        this.login = login;
        this.mdp = mdp;
        this.img = img;
        this.role = role;
        this.etat = etat;
    }

   
    
}
