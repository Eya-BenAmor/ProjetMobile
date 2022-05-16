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
import com.mycompany.entities.Plat;
import com.mycompany.myapp.utils.Statics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author msi
 */
public class ServicePlat {
    
    public static ServicePlat instance =null;
    public static boolean resultOk = true;
    
    private ConnectionRequest req;
    
    public static ServicePlat getInstance(){
    
    if ( instance == null )
    instance = new ServicePlat();
    return instance;}
    
    
    public ServicePlat(){
    
    req = new ConnectionRequest();
    
    }
    // ajout 
    public void ajoutPlat (Plat plat){
    
   String url=Statics.BASE_URL+"/plat/addplat?nom="+plat.getNom()+"&description="+plat.getDescription()+"&prix="+plat.getPrix();
    req.setUrl(url);
    req.addResponseListener((e) -> {
    
    String str = new String(req.getResponseData());
            System.out.println("data=="+str);
    
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    








 ////////////////////////////////////////////////////////////////////////////////////////////////////
    
      // affichage 
    
    public ArrayList<Plat>affichagePlat(){
    ArrayList<Plat> result = new ArrayList<>();
    String url=Statics.BASE_URL+"/plat/displayplat";
    req.setUrl(url);
    
   req.addResponseListener(new ActionListener<NetworkEvent>(){
   
   @Override
   public void actionPerformed(NetworkEvent evt ){
   
   JSONParser jsonp;
   jsonp = new JSONParser();
   
   try {
   
   Map<String,Object>plat=jsonp.parseJSON(new CharArrayReader(new String (req.getResponseData()).toCharArray()));
   List<Map<String,Object>> listOfMaps =(List<Map<String,Object>>) plat.get("root");
   for (Map<String,Object> obj : listOfMaps){
   
   Plat p = new Plat();
   float id =Float.parseFloat(obj.get("id").toString());
   String Nom =obj.get("nom").toString();
   String description =obj.get("description").toString();
   float prix =Float.parseFloat(obj.get("prix").toString());


   
   p.setId((int)id);
   p.setNom(Nom);
   p.setDescription(description);
    p.setPrix((int)prix);

   
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
   
 public boolean deletePlat(int id) {
       String url=Statics.BASE_URL+"/plat/deleteplat?id="+id;
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
     
       public boolean modifierPlat(Plat Plat){
        
            String url=Statics.BASE_URL+"/plat/updateplat?id="+Plat.getId()+"&nom="+Plat.getNom()+"&description="+Plat.getDescription()+"&prix="+Plat.getPrix();
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

