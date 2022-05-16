/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
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
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;

import com.mycompany.myapp.entities.Randonnee;

import com.mycompany.myapp.services.ServiceRandonnee;
import java.util.ArrayList;

/**
 *
 * @author MSI
 */
public class AjouterRando extends BaseFormBack{
      Form current;
    public AjouterRando(Resources res) {
    
 
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
    
   
       

        TextField nom = new TextField("");
        nom.setUIID("TextFieldBlack");

        TextField destination = new TextField("");
        destination.setUIID("TextFieldBlack");
         TextField description = new TextField("");
        description.setUIID("TextFieldBlack");
       
 TextField duree = new TextField("");
        duree.setUIID("TextFieldBlack");
         TextField prix = new TextField("");
       prix.setUIID("TextFieldBlack");
       

        Picker categorie = new Picker();
        categorie.setUIID("TextFieldBlack");
        categorie.setStrings(characters);
        categorie.setSelectedString("cliquez !");
        addStringValue("Catégorie Randonnée", categorie);

     Picker date=new Picker();
     date.setType(Display.PICKER_TYPE_DATE);
     addStringValue("Date", date);
      date.setUIID("TextFieldBlack");
 addStringValueImage(res.getImage("tick.png"), "Nom", FlowLayout.encloseRightMiddle(nom));
        addStringValueImage(res.getImage("tick.png"), "Destination", FlowLayout.encloseRightMiddle(destination));
         addStringValueImage(res.getImage("tick.png"), "Description", FlowLayout.encloseRightMiddle(description));
        addStringValueImage(res.getImage("tick.png"), "Prix", FlowLayout.encloseRightMiddle(prix));
         addStringValueImage(res.getImage("tick.png"), "Durée", FlowLayout.encloseRightMiddle(duree));
     


        Button bn = new Button("Ajouter", res.getImage("next.png"));
   Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
       
        new BackRando(res).show();
     
        refreshTheme();  
       
       });
       
        add(bn);
         add( btnAnnuler);
        
        
       bn.addPointerPressedListener((ActionEvent e) -> {
               
                 
          
          
          
          
            SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
          if ((nom.getText().length()==0)||(description.getText().length()==0)||(destination.getText().length()==0)||(categorie.getText().length()==0)||(prix.getText().length()==0)||(duree.getText().length()==0))
                    Dialog.show("Alerte", "Les champs ne doivent pas etre vides", new Command("OK"));
                else
                {
                     System.out.println(format.format(date.getDate()));
                      try {
        Randonnee r = new Randonnee((int)Float.parseFloat(prix.getText().toString()),nom.getText().toString(),description.getText().toString(),destination.getText().toString(),duree.getText().toString(),categorie.getText().toString(),format.format(date.getDate()),0);
                           System.out.println("Randonnee =="+r);
                       if(ServiceRandonnee.getInstance().ajouterRandonnee(r))
                      
                            {
                                  InfiniteProgress ip = new InfiniteProgress();
                    final Dialog iDialog = ip.showInfiniteBlocking();
                           //Dialog.show("Success","Connection accepted",new Command("OK"));
                            //refreshTheme();  
                            Dialog.show("Success", "Randonnée ajoutée ", new Command("OK"));
                        iDialog.dispose();
                         ToastBar.showMessage("Bienvenue : \n" + "Randonnée ajoutée avec succes", FontImage.MATERIAL_INFO);
                        refreshTheme();
                           
                        
                        
                      
                          new BackRando(res).show();
     
                            
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException n) {
                        Dialog.show("ERROR", "Prix doit etre number", new Command("OK"));
                    } 
            
         
                }
             
        
        });
    
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
      private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }

    private void addStringValueImage(Image img, String s, Component v) {
        add(BorderLayout.west(new Label(s, img, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }

 
    private static String[] characters = {"pied", "voiture", "velo"};
   

    
    
}   
