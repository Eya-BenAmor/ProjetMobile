/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;
import com.codename1.components.ToastBar;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Admin;
import com.mycompany.myapp.gui.NewsfeedBack;
import com.mycompany.myapp.gui.NewsfeedForm;
import com.mycompany.myapp.gui.SessionManager;
import com.mycompany.myapp.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author SALAH
 */
public class ServiceAdmin {
    
    public static ServiceAdmin instance = null ;
    private ConnectionRequest req;
    public boolean resultOK;
    public String result = "";
    public ArrayList<Admin> list;
      String json;
    
    public static ServiceAdmin getInstance(){
        if (instance == null)
        {
            instance = new ServiceAdmin();
        }
        return instance;
    }
    
    public ServiceAdmin (){
        req = new ConnectionRequest();
    }  
        //Affichage
   public void signin(TextField email,TextField pwd,Resources res){
            String url = Statics.BASE_URL+"/admin/verifierAdminJSON?email="+email.getText().toString()+"&mdp="+pwd.getText().toString();
           req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) + "";
            
            
            try {
                    if(json.equals("Wrong")) {
                         Dialog.show("Echec d'authentification","Username ou mot de passe eronné","OK",null);
                    }
                    else {
                       
                        System.out.println("data =="+json);
                        Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                        if(user.size() > 0 ) 
                        {
                               new NewsfeedBack(res).show();
                  float id = Float.parseFloat(user.get("id").toString());
                SessionManager.setId((int)id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i
                SessionManager.setPrenom(user.get("prenom").toString());
                SessionManager.setMdp(user.get("pwd").toString());
                SessionManager.setNom(user.get("nom").toString());
                SessionManager.setEmail(user.get("email").toString());
             SessionManager.setRole("admin");
                        }
                
                        else{
                    System.out.println("lee");
                        }
                
                    }
                        
              
                
              
                    
                }catch(Exception ex){
                    ex.printStackTrace();
                }
           
            });
        NetworkManager.getInstance().addToQueueAndWait(req);
        }
    
    
       public String getByEmailAdmin(String email){
            
            String url = Statics.BASE_URL+"/admin/getAdminByEmail?email="+email;
         
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
             json = new String(req.getResponseData()) + "";
            
            
            try {
            
          
                System.out.println("data =="+json);
                
                Map<String,Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
            
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    return json;
    }
     public void signup(TextField nom, TextField prenom, TextField email, TextField mdp, TextField confirm_mdp, Resources res) {
       
       String url=Statics.BASE_URL+"/admin/ajouterAdminAction?nom="+nom.getText().toString()+"&prenom="+prenom.getText().toString()+
        "&email="+email.getText().toString()+"&mdp="+mdp.getText().toString()+"&confirm_mdp="+confirm_mdp.getText().toString();
        
        req.setUrl(url);
        
        if (nom.getText().equals(" ") || prenom.getText().equals(" ") || mdp.getText().equals(" ") || email.getText().equals(" ") || confirm_mdp.getText().equals(" ")){
            Dialog.show("erreur","veuillez remplir les champs","ok",null);
        }
        
        req.addResponseListener((e)->{
            byte[]data = (byte[]) e.getMetaData();
            String responseData = new String(data);
            
            System.out.println("data ===>"+responseData);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
            
    }
    
      
    
}
