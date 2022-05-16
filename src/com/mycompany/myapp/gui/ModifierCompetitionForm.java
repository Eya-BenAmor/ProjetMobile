/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingHint;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Competition;
import com.mycompany.myapp.services.ServiceCompetition;


/**
 *
 * @author Mezen Bayounes
 */
public class ModifierCompetitionForm extends BaseForm {
    
    
    
      Form current;
    public ModifierCompetitionForm(Resources res , Competition p){
     super ("Newsfeed",BoxLayout.y());
    Toolbar tb = new Toolbar(true);
    current = this;
    setToolbar(tb);
    getTitleArea().setUIID("Container");
    setTitle("modifier Competition");
    getContentPane().setScrollVisible(false);
    
    super.addSideMenu(res);
    
    TextField Nom = new TextField(p.getNom(),"Nom",20,TextField.ANY);
    TextField distance = new TextField(Integer.toString(p.getDistance()));
    TextField prix= new TextField(Integer.toString(p.getPrix()));
    Picker date=new Picker();
     date.setType(Display.PICKER_TYPE_DATE);
     addStringValue("Date", date);
      date.setUIID("TextFieldBlack");

    
       SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
    distance.setUIID("NewsTopLine2");
     Nom.setUIID("NewsTopLine2");
     prix.setUIID("NewsTopLine2");
     date.setUIID("NewsTopLine2");

     
     Nom.setSingleLineTextArea(true);
      distance.setSingleLineTextArea(true);
                  prix.setSingleLineTextArea(true);

      
      Button btnModifier = new Button("Modifier");
      btnModifier.setUIID("Button");
      
      btnModifier.addPointerPressedListener(l -> {
      
      p.setNom(Nom.getText());
      p.setDistance(Integer.parseInt(distance.getText()));
      p.setPrix(Integer.parseInt(prix.getText()));
      p.setDate(format.format(date.getDate()));
      
     
      
      // appel fn modif
      
      if (ServiceCompetition.getInstance().modifierCompetition(p)){
      
      new ListCompetitionForm(res).show();
      
      
      }
       });
      
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
       
        new ListCompetitionForm(res).show();
       
       
       });
       
       
      Label a = new Label("");
Label b = new Label("");
Label c = new Label("");
Label d = new Label("");
Label e = new Label("");

Label f = new Label ();
        
        
        Container content = BoxLayout.encloseY(
      
                
       f,a,b,new FloatingHint (Nom),
                
        createLineSeparator(),new FloatingHint (distance),
        createLineSeparator(),new FloatingHint (prix),
       
         btnModifier,
         btnAnnuler
         
        
        );
        
       add(content);
       show();
      
      
    
    
    }
    
    
     private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
    
    
    
}
