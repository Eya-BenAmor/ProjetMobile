/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Categorie;
import com.mycompany.myapp.utils.Statics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author msi
 */
public class ServiceCategorie {
    
    public static ServiceCategorie instance =null;
    public static boolean resultOk = true;
    
    private ConnectionRequest req;
    
    public static ServiceCategorie getInstance(){
    
    if ( instance == null )
    instance = new ServiceCategorie();
    return instance;}
    
    
    public ServiceCategorie(){
    
    req = new ConnectionRequest();
    
    }
    // ajout 
    public void ajoutCategorie (Categorie categorie){
    
   String url=Statics.BASE_URL+"/categorie/addcategorie?nom="+categorie.getNom()+"&description="+categorie.getDescription();
    req.setUrl(url);
    req.addResponseListener((e) -> {
    
    String str = new String(req.getResponseData());
            System.out.println("data=="+str);
    
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    








 ////////////////////////////////////////////////////////////////////////////////////////////////////
    
      // affichage 
    
    public ArrayList<Categorie>affichageCategorie(){
    ArrayList<Categorie> result = new ArrayList<>();
    String url=Statics.BASE_URL+"/categorie/displaycategorie";
    req.setUrl(url);
    
   req.addResponseListener(new ActionListener<NetworkEvent>(){
   
   @Override
   public void actionPerformed(NetworkEvent evt ){
   
   JSONParser jsonp;
   jsonp = new JSONParser();
   
   try {
   
   Map<String,Object>mapCategorie=jsonp.parseJSON(new CharArrayReader(new String (req.getResponseData()).toCharArray()));
   List<Map<String,Object>> listOfMaps =(List<Map<String,Object>>) mapCategorie.get("root");
   for (Map<String,Object> obj : listOfMaps){
   
   Categorie p = new Categorie();
   float id =Float.parseFloat(obj.get("id").toString());
   String Nom =obj.get("nom").toString();
   String description =obj.get("description").toString();


   
   p.setId((int)id);
   p.setNom(Nom);
   p.setDescription(description);

   
   result.add(p);
   
   }
   }catch (Exception ex){
   ex.printStackTrace();
   
   }
   
   }
   
   
   });
   
    
      NetworkManager.getInstance().addToQueueAndWait(req);
      return result;
    
    
  
    
    
    }

    ////////////////////////////////////////////////////////////////////////////
    //delete
   
 public boolean deleteCategorie(int id) {
       String url=Statics.BASE_URL+"/categorie/deletecategorie?id="+id;
    req.setUrl(url);
    
    
       req.addResponseListener(new ActionListener<NetworkEvent>(){
   
   @Override
   public void actionPerformed(NetworkEvent evt ){
   
   req.removeResponseCodeListener(this);
   }
   });
 NetworkManager.getInstance().addToQueueAndWait(req);
      return resultOk;
    
    }    
  /////////////////////////////////////////////////////////
     //update 
     
       public boolean modifierCategorie(Categorie Categorie){
        
            String url=Statics.BASE_URL+"/categorie/updatecategorie?id="+Categorie.getId()+"&nom="+Categorie.getNom()+"&description="+Categorie.getDescription();
            req.setUrl(url);
    
    
       req.addResponseListener(new ActionListener<NetworkEvent>(){
   
   @Override
   public void actionPerformed(NetworkEvent evt ){
   
   resultOk = req.getResponseCode()== 200; // response http
   req.removeResponseListener(this);
   }
   });
       
        NetworkManager.getInstance().addToQueueAndWait(req); // execution request 
      return resultOk;
        
        }





























    
}