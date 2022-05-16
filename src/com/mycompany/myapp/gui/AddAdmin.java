/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.services.ServiceAdmin;
import com.mycompany.myapp.services.ServiceUser;


/**
 *
 * @author Yahya
 */
public class AddAdmin extends BaseForm{
    public AddAdmin(Resources res){
        super(new BorderLayout());
        
        if(!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout)getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
        setUIID("SignIn");
        
        add(BorderLayout.NORTH, new Label(res.getImage("Logo.png"), "LogoLabel"));  
                
        TextField nom = new TextField("", "Nom", 20, TextField.ANY);
        TextField prenom = new TextField("", "Prenom", 20, TextField.ANY);
        TextField email = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
        TextField cin = new TextField("", "CIN", 20, TextField.NUMERIC);
        TextField mdp = new TextField("", "Password", 20, TextField.PASSWORD);
        TextField confrim_mdp = new TextField("", "Confirm Password", 20, TextField.PASSWORD);
        nom.setSingleLineTextArea(false);
        prenom.setSingleLineTextArea(false);
        email.setSingleLineTextArea(false);
        cin.setSingleLineTextArea(false);
        mdp.setSingleLineTextArea(false);
        confrim_mdp.setSingleLineTextArea(false);
        Button next = new Button("Suivant");
          Button signIn = new Button("Connecter");
        signIn.addActionListener(e -> new SignInForm(res).show());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Avez-vous un compte déjà?");
        Container content = BoxLayout.encloseY(
                new Label("Inscrire", "LogoLabel"),
                new FloatingHint(nom),
                createLineSeparator(),
                new FloatingHint(prenom),
                createLineSeparator(),
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(mdp),
                createLineSeparator(),
                new FloatingHint(confrim_mdp),
                createLineSeparator()
        );
      
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        next.requestFocus();
        next.addActionListener((e) ->{
            ServiceAdmin.getInstance().signup(nom, prenom, email, mdp, confrim_mdp, res);
            Dialog.show("succes","account created","ok",null);
            new SignInForm(res).show();
        });
    }
}