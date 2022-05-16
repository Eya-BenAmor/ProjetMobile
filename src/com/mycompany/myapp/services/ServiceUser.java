/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.gui.NewsfeedForm;
import com.mycompany.myapp.gui.ProfileForm;
import com.mycompany.myapp.gui.SessionManager;
import com.mycompany.myapp.utils.Statics;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Yahya
 */
public class ServiceUser {

    public ArrayList<User> User;
      String json;
    public static ServiceUser instance = null;
    public static boolean resultOK;
    private ConnectionRequest req;

    private ServiceUser() {
         req = new ConnectionRequest();
    }

    public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }

    public void signup(TextField nom, TextField prenom, TextField email, TextField mdp, TextField confrim_mdp, Resources res) {
       
       String url=Statics.BASE_URL+"/user/signup?nom="+nom.getText().toString()+"&prenom="+prenom.getText().toString()+
        "&email="+email.getText().toString()+"&mdp="+mdp.getText().toString()+"&confrim_mdp="+confrim_mdp.getText().toString();
        
        req.setUrl(url);
        
        if (nom.getText().equals(" ") && prenom.getText().equals(" ") && mdp.getText().equals(" ") && email.getText().equals(" ")){
            Dialog.show("erreur","veuillez remplir les champs","ok",null);
        }
        
        req.addResponseListener((e)->{
            byte[]data = (byte[]) e.getMetaData();
            String responseData = new String(data);
            
            System.out.println("data ===>"+responseData);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
            
    }
    
    public String getPasswordByEmail(String email,Resources rs){
            String url = Statics.BASE_URL+"/user/getPasswordByEmail?email="+email;
            req = new ConnectionRequest(url,false); // false url mazel matabaatech l server 
            req.setUrl(url);
            req.addResponseListener((e) ->{
                
                JSONParser j = new JSONParser();
                
                String json = new String(req.getResponseData()) + "";
                
                try{
                    
                    System.out.println("data =="+json);
                        Map<String,Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));
                        
                 
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            
            });
            
            NetworkManager.getInstance().addToQueueAndWait(req);
            return json;
        }
    
          public void signin(TextField email,TextField mdp,Resources res){
        String url = Statics.BASE_URL+"/user/verifierUserJSON?email="+email.getText().toString()+"&mdp="+mdp.getText().toString();
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) + "";
            
            
            try {
                    if(json.equals("Wrong")) {
                         Dialog.show("Echec d'authentification","Email ou mot de passe eronné","OK",null);
                    }
                    else {
                       
                        System.out.println("data =="+json);
                        Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                        if(user.size() > 0 ) 
                        {
                          new NewsfeedForm(res).show();     
                  float id = Float.parseFloat(user.get("id").toString());
                SessionManager.setId((int)id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i
                SessionManager.setPrenom(user.get("prenom").toString());
                SessionManager.setMdp(user.get("mdp").toString());
                SessionManager.setNom(user.get("nom").toString());
                SessionManager.setEmail(user.get("email").toString());
                SessionManager.setRole("user");
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
    
    
    
    
    
        public void signup(TextField nom,TextField prenom,TextField adresse,TextField numtel,TextField email,TextField password,TextField confirmPassword,Resources res){
        
       
        
        String url = Statics.BASE_URL+"/user/signup?nom="+nom.getText().toString()+"&prenom="+prenom.getText().toString()+"&address="+adresse.getText().toString()+
                "&numtelc="+numtel.getText().toString()+"&email="+email.getText().toString()+"&mdp="+password.getText().toString();
        
        req.setUrl(url);
        
        //controler saisie
        if(nom.getText().equals(" ")  && prenom.getText().equals(" ")  &&adresse.getText().equals(" ")  && numtel.getText().equals(" ")  &&
                password.getText().equals(" ") && email.getText().equals(" ")) {
            
            Dialog.show("Erreur","Veuillez remplir les champs","ok",null);
        }
        //hethy wa9t tsir execution ta3 url
        req.addResponseListener((e)-> {
            
            // njib data ly7atithom fi form
            byte[]data = (byte[]) e.getMetaData(); //lazm awl haja nhathrhom fe meta dat ya3ni nekhdou id ta3 kol textfield
            String responseData = new String(data);//va3dika nakhou content
            
            System.out.println("data ===>"+responseData);
            
            
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
         }
        
            public String getByEmail(String email){
            
            String url = Statics.BASE_URL+"/user/getUserByEmail?email="+email;
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
            
    
    public static void EditUser(int id, String nom, String prenom, String mdp, String email){
        
   String url = Statics.BASE_URL+"/user/updateUser?id="+id+"&nom="+nom+"&prenom="+prenom+"&email="+email+"&mdp="+mdp+"&confrim_mdp="+mdp;
                MultipartRequest req = new MultipartRequest();
                
                req.setUrl(url);
                req.setPost(true);
            //    req.addArgument("id", String.valueOf(SessionManager.getId()));
                req.addArgument("nom", nom);
                req.addArgument("prenom", prenom);
                req.addArgument("email", email);
                req.addArgument("mdp", mdp);
                System.out.println(email);
                req.addResponseListener((response)-> {
                    
                    byte[] data = (byte[]) response.getMetaData();
                    String s = new String(data);
                    System.out.println(s);
                                       
                });
                NetworkManager.getInstance().addToQueueAndWait(req);
    
    }
    
      // affichage 
    
    public ArrayList<User>affichageUsers(){
    ArrayList<User> result = new ArrayList<>();
    String url=Statics.BASE_URL+"/displayusers";
    req.setUrl(url);
    
   req.addResponseListener(new ActionListener<NetworkEvent>(){
   
   @Override
   public void actionPerformed(NetworkEvent evt ){
   
   JSONParser jsonp;
   jsonp = new JSONParser();
   
   try {
   
   Map<String,Object>mapUsers=jsonp.parseJSON(new CharArrayReader(new String (req.getResponseData()).toCharArray()));
   List<Map<String,Object>> listOfMaps =(List<Map<String,Object>>) mapUsers.get("root");
   for (Map<String,Object> obj : listOfMaps){
   
   User u = new User();
   float id =Float.parseFloat(obj.get("id").toString());
   String nom =obj.get("nom").toString();
   String prenom =obj.get("prenom").toString();
   String email =obj.get("email").toString();
   String mdp =obj.get("mdp").toString();
   String confrim_mdp =obj.get("confrim_mdp").toString();
   
   u.setId((int)id);
   u.setNom(nom);
   u.setPrenom(prenom);
   u.setEmail(email);
   u.setMdp(mdp);
   u.setConfirm_mdp(confrim_mdp);
   
   result.add(u);
   
   }
   }catch (Exception ex){
   ex.printStackTrace();
   
   }
   }
 });
   
      NetworkManager.getInstance().addToQueueAndWait(req);
      return result;
    
    }
    
        public boolean deleteUser(int id ){

 String url=Statics.BASE_URL+"/user/deleteUser?id="+id;
    req.setUrl(url);
    
    
       req.addResponseListener(new ActionListener<NetworkEvent>(){
   
   @Override
   public void actionPerformed(NetworkEvent evt ){
   
   req.removeResponseCodeListener(this);
   }
   });
 NetworkManager.getInstance().addToQueueAndWait(req);
        boolean resultOk = false;
      return resultOk;
    
    }
        
        
        public String emailParti(int id)
        {
            
             String url = Statics.BASE_URL+"/user/getEmailParti/"+id;
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
        
        
        
   
}
    