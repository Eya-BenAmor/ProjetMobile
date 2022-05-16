/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.mycompany.myapp.gui.AjouterRando;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Randonnee;
import com.mycompany.myapp.services.ServiceParticipant;
import com.mycompany.myapp.services.ServiceRandonnee;
import java.util.ArrayList;

/**
 *
 * @author MSI
 */
public class BackRando extends BaseFormBack{
      Form current;
    public BackRando(Resources res) {
    
 
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Randonnées");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
      
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
     
        addTab(swipe, res.getImage("rando.jpg"), spacer1, "", "", "");
       
                
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0x11111);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
        Component.setSameSize(radioContainer, spacer1);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton rando = RadioButton.createToggle("Randonnées", barGroup);
        rando.setUIID("SelectBar");
         RadioButton parti = RadioButton.createToggle("Participants", barGroup);
        parti.setUIID("SelectBar");
        RadioButton stat = RadioButton.createToggle("Statistiques", barGroup);
        stat.setUIID("SelectBar");
       
    
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
        rando.addActionListener((e) -> {
        InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipD1g= ip.showInfiniteBlocking();
      new BackRando(res).show();
     
        refreshTheme();
        
    });
       
          parti.addActionListener((e) -> {
        InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipD1g= ip.showInfiniteBlocking();
    //  new BackRando(res).show();
     
        refreshTheme();
        
    });
            stat.addActionListener((e) -> {
        InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipD1g= ip.showInfiniteBlocking();
   new StatRando(res).show();
     
        refreshTheme();
        
    });
        
        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3,rando, parti,stat),
                FlowLayout.encloseBottom(arrow)
        ));
        
        rando.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(rando, arrow);
        });
        bindButtonSelection(rando, arrow);
        bindButtonSelection(parti, arrow);
       
       bindButtonSelection(stat, arrow);
        
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
    
    // appel affichage 
  
    
    
 
          Button bn = new Button("Ajouter une randonnée", res.getImage("next.png"));
  
       bn.addActionListener(e -> {
       
        new AjouterRando(res).show();
     
        refreshTheme();  
       
       });
       
        add(bn);
       
    
    // appel affichage 
    ArrayList<Randonnee> list = ServiceRandonnee.getInstance().getAllRandonnees();
    
    for(Randonnee p : list ) {
        
        
    String urlImage= ("logo.jpeg");
    Image PlaceHolder= Image.createImage(120,90);
    EncodedImage enc=EncodedImage.createFromImage(PlaceHolder, false);
    URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage,URLImage.RESIZE_SCALE);
            
            
    addButton(urlim,p,res);
    
    ScaleImageLabel image = new ScaleImageLabel(urlim);
    Container containerImg = new Container();
    image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
    
    
    }
    
    }
    
    
      private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            new SpanLabel(text, "LargeWhiteText"),
                            FlowLayout.encloseIn(likes, comments),
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
    
    public void bindButtonSelection(Button btn,Label l){
    
    
    btn.addActionListener(e ->  {
    
        if(btn.isSelected()) {
        
        updateArrowPosition(btn,l);
        
        }
    
    
    });
    }

    private void updateArrowPosition(Button btn, Label l) {
        l.getUnselectedStyle().setMargin(LEFT,btn.getX() + btn.getWidth() / 2 - l.getWidth() / 2  );
        l.getParent().repaint();
        
        
        
    }

    private void addButton(Image img,Randonnee r,Resources res) {
        int height =Display.getInstance().convertToPixels(11.5f);
        
        int width =Display.getInstance().convertToPixels(5f);
        
        Button image = new Button(img.fill(width,height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        
     Label nomText = new Label ("nom:"+r.getNomRando(),"NewsTopLine2");
       Label destination = new Label ("Destination:"+r.getDestination(),"NewsTopLine2");
      Label categorie = new Label ("Catégorie:"+r.getCategorieRando(),"NewsTopLine2");
        
        Label duree = new Label ("Duree:"+r.getDureeRando(),"NewsTopLine2");
      
        Label prix = new Label ("Prix:"+r.getPrix(),"NewsTopLine2");
     Label date = new Label ("Date:"+r.getDateRando(),"NewsTopLine2");
   
        
    
             
               
               
               
               
                   Label lSupprimer= new Label(" ");
             lSupprimer.setUIID("NewsTopLine");
             Style supprimerStyle = new Style(lSupprimer.getUnselectedStyle());
             supprimerStyle.setFgColor(0xf21f1f);
             
             FontImage supprimerImage =FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprimerStyle);
             lSupprimer.setIcon(supprimerImage);
             lSupprimer.setTextPosition(RIGHT);
             
             
             // appel service
             lSupprimer.addPointerPressedListener(l -> {
             
             Dialog dig = new Dialog("suppression ");
             if (dig.show("suppression","voulez-vous supprimer cette randonnée?","Annuler","oui")){
             
             dig.dispose();
             }
             else {
                       dig.dispose();
                     
                     if(ServiceRandonnee.getInstance().supprimerRandonnee(r.getId()))  {
                      new BackRando(res).show();
                     
               refreshTheme();  
                     
                     }
                     }
             
               new BackRando(res).show();
                     
               refreshTheme();  
             
             
             });
             
             // update 
             
             
                Label lModifier= new Label(" ");
           lModifier.setUIID("NewsTopLine");
             Style modifierStyle = new Style(lModifier.getUnselectedStyle());
             modifierStyle.setFgColor(0xf7ad02);
             
             FontImage mFontImage=FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
                 lModifier.setIcon(mFontImage);
             lModifier.setTextPosition(LEFT);
           
                        // appel service modifier 
             lModifier.addPointerPressedListener(l -> {
             
             
                new ModifierRando(res,r).show();
             
             
             });
               
               Label lRechercher= new Label(" ");
           lRechercher.setUIID("NewsTopLine");
             Style rechercherStyle = new Style(lRechercher.getUnselectedStyle());
             rechercherStyle.setFgColor(0xf7ad02);
             
             FontImage mFont2Image=FontImage.createMaterial(FontImage.MATERIAL_IMAGE_SEARCH, rechercherStyle);
                 lRechercher.setIcon(mFont2Image);
             lRechercher.setTextPosition(LEFT);
           
                        // appel service modifier 
             lRechercher.addPointerPressedListener(l -> {
             
             //System.out.println("modifier");
                
          new ListerParticipantsByRando(res,r).show();
             
             
             
             });
               
               
               
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
               
               
               
               
             
              cnt.add(BorderLayout.CENTER,BoxLayout.encloseY
        (BoxLayout.encloseX(nomText,lSupprimer,lModifier,lRechercher),
                BoxLayout.encloseX(destination),
                BoxLayout.encloseX(categorie),
               BoxLayout.encloseX(date),
                BoxLayout.encloseX(duree),
                BoxLayout.encloseX(prix)
                
                ));
              
                     
        add(cnt);
        
    }
    
}   