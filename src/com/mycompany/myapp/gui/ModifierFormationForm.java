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
import com.mycompany.myapp.entities.Formation;
import com.mycompany.myapp.gui.BaseForm;
import com.mycompany.myapp.services.ServiceFormation;


/**
 *
 * @author seifi
 */
public class ModifierFormationForm extends BaseFormBack{

    Form current;
    public ModifierFormationForm(Resources res , Formation p){
     super ("Newsfeed",BoxLayout.y());
    Toolbar tb = new Toolbar(true);
    current = this;
    setToolbar(tb);
    getTitleArea().setUIID("Container");
    setTitle("ajout publications");
    getContentPane().setScrollVisible(false);
    
    super.addSideMenu(res);
    
    TextField nomeq = new TextField(p.getNomeq(),"Equipe",20,TextField.ANY);
     TextField domaine = new TextField(p.getDomaine(),"Description",20,TextField.ANY);
     TextField duree = new TextField(p.getDuree(),"Duree",20,TextField.ANY);
     TextField nomform = new TextField(p.getNomform(),"Nom Formation",20,TextField.ANY);
     TextField plan = new TextField(p.getPlan(),"Plan",20,TextField.ANY);
     TextField date = new TextField(p.getDate(),"Date",20,TextField.ANY);
     
     nomeq.setUIID("NewsTopLine");
     domaine.setUIID("NewsTopLine");
     duree.setUIID("NewsTopLine");
     nomform.setUIID("NewsTopLine");
     plan.setUIID("NewsTopLine");
     date.setUIID("NewsTopLine");
     
     nomeq.setSingleLineTextArea(true);
      domaine.setSingleLineTextArea(true);
       duree.setSingleLineTextArea(true);
      nomform.setSingleLineTextArea(true);
      
       plan.setSingleLineTextArea(true);
      date.setSingleLineTextArea(true);
      
      
   
   
      
      Button btnModifier = new Button("Modifier");
      btnModifier.setUIID("Button");
      
      btnModifier.addPointerPressedListener(l -> {
      
      p.setNomeq(nomeq.getText());
       p.setDomaine(domaine.getText());
        p.setDuree(duree.getText());
       p.setNomform(nomform.getText());
        p.setPlan(plan.getText());
       p.setDate(date.getText());
      
      
     
      
      // appel fn modif
      
      if (ServiceFormation.getInstance().modifierFormation(p)){
      
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
                
        new FloatingHint (nomeq),
        createLineSeparator(),
        new FloatingHint (domaine),
         createLineSeparator(),
         new FloatingHint (duree),
        createLineSeparator(),
        new FloatingHint (nomform),
         createLineSeparator(),
         new FloatingHint (plan),
        createLineSeparator(),
        new FloatingHint (date),
         createLineSeparator(),
         btnModifier,
         btnAnnuler
         
        
        );
        
       add(content);
       show();
      
      
    
    
    }
    
}
