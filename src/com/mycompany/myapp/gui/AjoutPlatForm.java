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
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
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
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Categorie;
import com.mycompany.entities.Plat;
import com.mycompany.myapp.services.ServicePlat;

/**
 *
 * @author msi
 */
public class AjoutPlatForm extends BaseFormBack {
    Form current;
    public AjoutPlatForm(Resources res ){
        
    
    super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Plats");
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
        RadioButton Publications = RadioButton.createToggle("Catégories", barGroup);
        Publications.setUIID("SelectBar");
        RadioButton pub = RadioButton.createToggle("Plats", barGroup);
        pub.setUIID("SelectBar");
         RadioButton ajoutFormation = RadioButton.createToggle("+Catégorie", barGroup);
        ajoutFormation.setUIID("SelectBar");
         RadioButton ajoutparticipant = RadioButton.createToggle("+Plat", barGroup);
        ajoutparticipant.setUIID("SelectBar");
        
    
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
       Publications.addActionListener((e) -> {
        InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipD1g= ip.showInfiniteBlocking();
        new ListCategorieForm(res).show();
     
        refreshTheme();
        
    });
         pub.addActionListener((e) -> {
        InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipD1g= ip.showInfiniteBlocking();
        new ListPlatForm(res).show();
     
        refreshTheme();
        
    });
         
           ajoutFormation.addActionListener((e) -> {
        InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipD1g= ip.showInfiniteBlocking();
        new AjoutCategorieForm(res).show();
     
        refreshTheme();
        
    });
          ajoutparticipant.addActionListener((e) -> {
        InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipD1g= ip.showInfiniteBlocking();
        new AjoutPlatForm(res).show();
     
        refreshTheme();
        
    });
        
        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(4,Publications,  pub,ajoutFormation,ajoutparticipant),
                FlowLayout.encloseBottom(arrow)
        ));
        
         ajoutparticipant.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition( ajoutparticipant, arrow);
        });
        bindButtonSelection(Publications, arrow);
     bindButtonSelection(pub, arrow);
        bindButtonSelection(ajoutFormation, arrow);
           bindButtonSelection(ajoutparticipant, arrow);
      
       
        
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
    
    //
    
    
    TextField Nom = new TextField("","saisir le Nom  !");
    Nom.setUIID("TextFieldBlack");
    addStringValue("nom",Nom);
    
     TextField description= new TextField("","saisir la description  !");
    description.setUIID("TextFieldBlack");
    addStringValue("description",description);
    TextField prix= new TextField("","saisir la prix  !");
    prix.setUIID("TextFieldBlack");
    addStringValue("prix",prix);
    
    

    
    
    Button btnAjouter = new Button("Ajouter");
    addStringValue("",btnAjouter);
    
       
    btnAjouter.addActionListener((e) -> {
    
    try {
        if (Nom.getText().equals("") || description.getText().equals("")|| prix.getText().equals(""))
        {
        Dialog.show("vérifier vos données","","Annuler","ok");
        
        }
        else {
            InfiniteProgress ip = new InfiniteProgress();;
            final Dialog iDialog = ip.showInfiniteBlocking();
            
                                

            Plat p = new Plat(
            String.valueOf(Nom.getText()).toString(),
            String.valueOf(description.getText())
                    ,Integer.parseInt(prix.getText())
                    
                  
           );


            System.out.println("Plat =="+p);
            
            ServicePlat.getInstance().ajoutPlat(p);
            iDialog.dispose();
            
            // Affichage 
            
            //new ListCategorieForm(res).show();
            
           new ListPlatForm(res).show();
     
    
            refreshTheme();
        }
            
            
            
            
    
    
    }catch( Exception ex) {
    
    ex.printStackTrace();
    }
    
    
    });
    
    
    
    
    }

    private void addStringValue(String s, Component v) {
       add(BorderLayout.west(new Label(s,"PaddedLabel"))
       
       .add(BorderLayout.CENTER,v));
       add(createLineSeparator(0xeeeeee));
       
       
       
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
    
    
}
    

