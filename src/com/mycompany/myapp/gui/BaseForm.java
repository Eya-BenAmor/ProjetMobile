

package com.mycompany.myapp.gui;

import com.mycompany.myapp.gui.WalkthruForm;
import com.mycompany.myapp.gui.BackRando;
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
 * Base class for the forms with common functionality
 *
 * @author Shai Almog
 */
public class BaseForm extends Form {

    public BaseForm() {
    }

    public BaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public BaseForm(String title, Layout contentPaneLayout) {
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
        
       
       
        
     tb.addMaterialCommandToSideMenu("Profile", FontImage.MATERIAL_SETTINGS, e -> new ProfileForm(res).show());
        tb.addMaterialCommandToSideMenu("Randonnées", FontImage.MATERIAL_CARD_TRAVEL, e -> new ListerRando(res).show());
            tb.addMaterialCommandToSideMenu("Formations", FontImage.MATERIAL_CARD_TRAVEL, e -> new ListFormationForm(res).show());
           
       tb.addMaterialCommandToSideMenu("Compétitions", FontImage.MATERIAL_CARD_TRAVEL,  e -> new ListfrontCompetitionForm(res).show());
       
      tb.addMaterialCommandToSideMenu("Plats", FontImage.MATERIAL_CARD_TRAVEL, e -> new ListfrontPlatForm(res).show());
      tb.addMaterialCommandToSideMenu("Publications", FontImage.MATERIAL_CARD_TRAVEL, e -> new ListPublicationForm(res).show());
     
   
        
      tb.addMaterialCommandToSideMenu("Déconnecter", FontImage.MATERIAL_EXIT_TO_APP, e -> new WalkthruForm(res).show());
        
    
}}
