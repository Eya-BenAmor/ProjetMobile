/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
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
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Commentaire;
import com.mycompany.myapp.entities.Publication;
import com.mycompany.myapp.services.ServiceCommentaire;
import java.util.ArrayList;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;

/**
 *
 * @author MSI
 */
public class ListerCommentaire extends BaseForm{
    
    
      Form current;
       
   
    public ListerCommentaire(Resources res,Publication p) {
       //super.addSideMenu(res);
     super("Newsfeed", BoxLayout.y());
            current= this;

        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Publications");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
      
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("rando.jpg"), spacer1, "", "", "");
                
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
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
        refreshTheme();
        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
         ButtonGroup barGroup = new ButtonGroup();
        RadioButton Publications = RadioButton.createToggle("Publications", barGroup);
        Publications.setUIID("SelectBar");
        RadioButton pub = RadioButton.createToggle("Statistiques", barGroup);
        pub.setUIID("SelectBar");
        RadioButton ajout = RadioButton.createToggle("Ajouter", barGroup);
       ajout.setUIID("SelectBar");

      
        // click   3 boutons  ajout stat publications 
        ajout.addActionListener((e) -> {
        InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipD1g= ip.showInfiniteBlocking();
        new AjoutPublicationForm(res).show();
     
        refreshTheme();
        
    });
        
         pub.addActionListener((e) -> {
   new StatistiquePieForm(res).show();
        
    });
         
            Publications.addActionListener((e) -> {
   new ListPublicationForm(res).show();
        
    });
        // record voice code
       
      
       
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3,Publications, pub, ajout)
         
        ));
        
        Publications.setSelected(true);

    // appel affichage 
    ArrayList<Commentaire> list = ServiceCommentaire.getInstance().getAllCommentaireByPub(p.getId());
    
    for(Commentaire c : list ) {
        
        
    String urlImage= ("rando.jpg");
    Image PlaceHolder= Image.createImage(120,90);
    EncodedImage enc=EncodedImage.createFromImage(PlaceHolder, false);
    URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage,URLImage.RESIZE_SCALE);
            
            
    addButton(urlim,c,res);
    
    ScaleImageLabel image = new ScaleImageLabel(urlim);
    Container containerImg = new Container();
    image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
    
    }
    
    
     Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(f-> {
       
        new ListPublicationForm(res).show();
   
       
       
       }); 
add(btnAnnuler);
    
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

    private void addButton(Image img, Commentaire p, Resources res) {
        int height =Display.getInstance().convertToPixels(11.5f);
        
        int width =Display.getInstance().convertToPixels(5f);
        
        Button image = new Button(img.fill(width,height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        
     Label contenu = new Label ("Contenu:"+p.getContenu(),"NewsTopLine2");
     
         
             
   
                     
                    
                     
             
            
          
     
             
             
              cnt.add(BorderLayout.CENTER,BoxLayout.encloseY
         (BoxLayout.encloseX(contenu)
              
                
                ));
              
                     
        add(cnt);
    }

      // BOUTON CONSULTER L HEURE 
      
      
      
       
    
}
