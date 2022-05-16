/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.mycompany.myapp.gui.ListerRando;
import com.mycompany.myapp.gui.ListerParticipant;
import com.mycompany.myapp.gui.BaseForm;
import com.codename1.components.FloatingHint;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Participant;
import com.mycompany.myapp.services.ServiceParticipant;

/**
 *
 * @author MSI
 */
public class ModifierParticipant extends BaseForm {
      Form current;
    public ModifierParticipant(Resources res , Participant p){
       
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
        RadioButton histo = RadioButton.createToggle("Historiques", barGroup);
        histo.setUIID("SelectBar");
       
    
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
        rando.addActionListener((e) -> {
        InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipD1g= ip.showInfiniteBlocking();
      new ListerRando(res).show();
     
        refreshTheme();
        
    });
       
          histo.addActionListener((e) -> {
        InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipD1g= ip.showInfiniteBlocking();
      new ListerParticipant(res).show();
     
        refreshTheme();
        
    });
        
        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(2,rando, histo),
                FlowLayout.encloseBottom(arrow)
        ));
        
        histo.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(histo, arrow);
        });
        bindButtonSelection(rando, arrow);
        bindButtonSelection(histo, arrow);
       
       
        
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
    
    // appel affichage 
  
  
    
    
     
   TextField age = new TextField(String.valueOf(p.getAge()), "idc", 20, TextField.ANY);
        age.setUIID("TextFieldBlack");

        TextField tel = new TextField(p.getTel(),"tel",20,TextField.ANY);
        tel.setUIID("TextFieldBlack");

        

     
   age.setSingleLineTextArea(true);
      tel.setSingleLineTextArea(true);
      
      
       Picker p0 = new Picker();
        p0.setUIID("TextFieldBlack");
        p0.setStrings(characters);
        p0.setSelectedString("cliquez !");
        addStringValue("Souffrez-vous des maladies?", p0);

        Picker p1 = new Picker();
        p1.setUIID("TextFieldBlack");
        p1.setStrings(characters2);
        p1.setSelectedString("cliquez !");
        addStringValue("Je suis un randonneur", p1);

        addStringValueImage(res.getImage("tick.png"), "Age", FlowLayout.encloseRightMiddle(age));
        addStringValueImage(res.getImage("tick.png"), "Téléphone", FlowLayout.encloseRightMiddle(tel));
      
     

    
 
     
  
      
     Button bn = new Button("Modifier", res.getImage("next.png"));
   Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
       
        new ListerParticipant(res).show();
     
        refreshTheme();  
       
       });
       
        add(bn);
         add( btnAnnuler);
      
      bn.addPointerPressedListener(l -> {
      
    
       
        
      
   
      float a = Float.parseFloat(age.getText());
              
                  p.setAge((int)a); 
 
       p.setTel(tel.getText());
        p.setMaladie(p0.getText());
         p.setClasse(p1.getText());
       
      
      
      
      // appel fn modif
      
      if (ServiceParticipant.getInstance().modifierParticipant(p)){
        
      new ListerParticipant(res).show();
      
      
      }
       });
  
      
       
       
     
    
    
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

 
    private static String[] characters = {"oui", "non"};
    private static String[] characters2 = {"Debutant", "intermediaire","habitue"};
    
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
    
}
