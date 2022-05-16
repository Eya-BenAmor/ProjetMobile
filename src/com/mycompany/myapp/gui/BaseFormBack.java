/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

/**
 *
 * @author MSI
 */
public class BaseFormBack extends Form {
    public BaseFormBack() {
    }

    public BaseFormBack(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public BaseFormBack(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }
    
    
    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
    
    public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    protected void addSideMenu(Resources res) {
        Toolbar tb = getToolbar();
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
      
    
       
     
   
           tb.addMaterialCommandToSideMenu("Gestion des admins", FontImage.	MATERIAL_CARD_TRAVEL, e ->  new AddAdmin(res).show() );
            tb.addMaterialCommandToSideMenu("Gestion des randonnées ", FontImage.	MATERIAL_CARD_TRAVEL, e ->  new BackRando(res).show());
       tb.addMaterialCommandToSideMenu("Gestion des formations", FontImage.	MATERIAL_CARD_TRAVEL, e -> new ListFormationAdmin(res).show() );
        tb.addMaterialCommandToSideMenu("Gestion des compétitions ", FontImage.	MATERIAL_CARD_TRAVEL, e -> new ListCompetitionForm(res).show() );
       
       tb.addMaterialCommandToSideMenu("Gestion des plats", FontImage.	MATERIAL_CARD_TRAVEL, e -> new ListPlatForm(res).show() );
     
       tb.addMaterialCommandToSideMenu("Déconnecter", FontImage.MATERIAL_EXIT_TO_APP, e -> new WalkthruForm(res).show());
        
    
}}