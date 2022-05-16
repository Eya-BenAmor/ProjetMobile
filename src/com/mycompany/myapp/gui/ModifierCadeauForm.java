/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Cadeau;
import com.mycompany.entities.Competition;
import com.mycompany.myapp.services.ServiceCadeau;


/**
 *
 * @author Mezen Bayounes
 */
public class ModifierCadeauForm extends BaseForm{

    
    
      Form current;
    public ModifierCadeauForm(Resources res , Cadeau p){
     super ("Newsfeed",BoxLayout.y());
    Toolbar tb = new Toolbar(true);
    current = this;
    setToolbar(tb);
    getTitleArea().setUIID("Container");
    setTitle("modifier Cadeau");
    getContentPane().setScrollVisible(false);
    
    super.addSideMenu(res);
    
    TextField nom = new TextField(p.getNom(),"Nom",20,TextField.ANY);
        TextField categorie = new TextField(p.getCategorie(),"categorie",20,TextField.ANY);

    TextField description = new TextField(p.getDescription(),"description",20,TextField.ANY);


    
    
    description.setUIID("NewsTopLine2");
     nom.setUIID("NewsTopLine2");
     categorie.setUIID("NewsTopLine2");

     
     categorie.setSingleLineTextArea(true);
      nom.setSingleLineTextArea(true);
                  description.setSingleLineTextArea(true);

      
      Button btnModifier = new Button("Modifier");
      btnModifier.setUIID("Button");
      
      btnModifier.addPointerPressedListener(l -> {
      
      p.setNom(nom.getText());
      p.setCategorie(categorie.getText());
      p.setDescription(description.getText());

      
     
      
      // appel fn modif
      
      if (ServiceCadeau.getInstance().modifierCadeau(p)){
      
      new ListCadeauForm(res).show();
      
      
      }
       });
      
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
       
        new ListCadeauForm(res).show();
       
       
       });
       
       
      Label a = new Label("");
Label b = new Label("");
Label c = new Label("");
Label d = new Label("");
Label e = new Label("");

Label f = new Label ();
        
        
        Container content = BoxLayout.encloseY(
      
                
       f,a,b,new FloatingHint (nom),
                
        createLineSeparator(),new FloatingHint (description),
        createLineSeparator(),new FloatingHint (categorie),
       
         btnModifier,
         btnAnnuler
         
        
        );
        
       add(content);
       show();
      
      
    
    
    }

    
    
    
    
    
    
}
