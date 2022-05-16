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
import com.mycompany.myapp.entities.ParticForm;
import com.mycompany.myapp.services.ServiceParticForm;


/**
 *
 * @author seifi
 */
public class ModifierPFForm extends BaseFormBack{
    Form current;
    public ModifierPFForm(Resources res , ParticForm p){
     super ("Newsfeed",BoxLayout.y());
    Toolbar tb = new Toolbar(true);
    current = this;
    setToolbar(tb);
    getTitleArea().setUIID("Container");
    setTitle("ajout publications");
    getContentPane().setScrollVisible(false);
    
    super.addSideMenu(res);
    String a=Integer.toString(p.getAge());
   String b=Integer.toString(p.getNumero());
    TextField nom = new TextField(p.getNom(),"nom",20,TextField.ANY);
     TextField prenom = new TextField(p.getPrenom(),"prenom",20,TextField.ANY);
    TextField age = new TextField(a,"age",20,TextField.ANY);
     TextField exp = new TextField(p.getExp(),"exp",20,TextField.ANY);
     TextField so_domaine = new TextField(p.getSo_domaine(),"domaine",20,TextField.ANY);
     TextField so_ass = new TextField(p.getSo_ass(),"association",20,TextField.ANY);
    TextField Numero = new TextField(b,"Numero",20,TextField.ANY);
     //Integer.parseInt(tfdomaine.getText())
     nom.setUIID("NewsTopLine");
     prenom.setUIID("NewsTopLine");
    age.setUIID("NewsTopLine");
     exp.setUIID("NewsTopLine");
     so_domaine.setUIID("NewsTopLine");
     so_ass.setUIID("NewsTopLine");
    Numero.setUIID("NewsTopLine");
     
     nom.setSingleLineTextArea(true);
      prenom.setSingleLineTextArea(true);
     age.setSingleLineTextArea(true);
      exp.setSingleLineTextArea(true);
      
       so_domaine.setSingleLineTextArea(true);
      so_ass.setSingleLineTextArea(true);
      Numero.setSingleLineTextArea(true);
      
      
   
   
      
      Button btnModifier = new Button("Modifier");
      btnModifier.setUIID("Button");
      
      btnModifier.addPointerPressedListener(l -> {
      
           
           int c=Integer.parseInt(age.getText()); 
            int d =Integer.parseInt(Numero.getText()); 
   
   
      p.setNom(nom.getText());
       p.setPrenom(prenom.getText());
       
       p.setAge(c);
       p.setExp(exp.getText());
        p.setSo_domaine(so_domaine.getText());
       p.setNumero(d);
      
      
     
      
      // appel fn modif
      
      if (ServiceParticForm.getInstance().modifierPF(p)){
      
      new ListFormationAdmin(res).show();
      
      
      }
       });
      
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
       
        new ListFormationAdmin(res).show();
       
       
       });
       
       
       Label l2 = new Label("");
        Label l1 = new Label("");
        
        Container content = BoxLayout.encloseY(
        l1,l2,
                
        new FloatingHint (nom),
        createLineSeparator(),
        new FloatingHint (prenom),
         createLineSeparator(),
         new FloatingHint (age),
       createLineSeparator(),
        new FloatingHint (exp),
         createLineSeparator(),
         /*new FloatingHint (so_domaine),
         createLineSeparator(),
         new FloatingHint (so_ass),*/
         createLineSeparator(),
        new FloatingHint (Numero),
         createLineSeparator(),
         btnModifier,
         btnAnnuler
         
        
        );
        
       add(content);
       show();
      
      
    
    
    }
    
}
