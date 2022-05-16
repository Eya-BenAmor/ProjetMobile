/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author MSI
 */
public class NewsfeedBack extends BaseFormBack {

    public NewsfeedBack(Resources res) {
        
         super(new BorderLayout());
        
        if(!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout)getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
        setUIID("SignIn");
        
        super.addSideMenu(res);
       
    
  
             
   add(BorderLayout.NORTH, new SpanLabel("Bienvenue dans votre espace admin "));        
         add(BorderLayout.SOUTH, new Label(res.getImage("Logo.png"), "LogoLabel"));  
         
}
}