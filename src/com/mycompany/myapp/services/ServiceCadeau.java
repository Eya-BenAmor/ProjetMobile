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
import com.mycompany.entities.Cadeau;
import com.mycompany.entities.Competition;
import com.mycompany.myapp.utils.Statics;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mezen Bayounes
 */
public class ServiceCadeau {
     public static ServiceCadeau instance =null;
    public static boolean resultOk = true;
    
    private ConnectionRequest req;
    
    public static ServiceCadeau getInstance(){
    
    if ( instance == null )
    instance = new ServiceCadeau();
    return instance;}
    
    
    public ServiceCadeau(){
    
    req = new ConnectionRequest();
    
    }
    
    
      
   // ajout 
    public void ajoutCadeau (Cadeau cadeau){
    
    String url=Statics.BASE_URL+"/cadeau/addcadeau?nom="+cadeau.getNom()+"&categorie_cadeau="+cadeau.getCategorie()+"&description_cadeau="+cadeau.getDescription();
    req.setUrl(url);
    req.addResponseListener((e) -> {
    
    String str = new String(req.getResponseData());
            System.out.println("data=="+str);
    
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    }  
    
    
   ////////////////////////////////////////////////////////////////////////////////////////////////////
    
       
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    
      // affichage 
    
    public ArrayList<Cadeau>affichageCadeau(){
    ArrayList<Cadeau> result = new ArrayList<>();
    String url=Statics.BASE_URL+"/cadeau/displaycadeau";
    req.setUrl(url);
    
   req.addResponseListener(new ActionListener<NetworkEvent>(){
   
   @Override
   public void actionPerformed(NetworkEvent evt ){
   
   JSONParser jsonp;
   jsonp = new JSONParser();
   
   try {
   
   Map<String,Object>mapCompetition=jsonp.parseJSON(new CharArrayReader(new String (req.getResponseData()).toCharArray()));
   List<Map<String,Object>> listOfMaps =(List<Map<String,Object>>) mapCompetition.get("root");
   for (Map<String,Object> obj : listOfMaps){
   
   Cadeau p = new Cadeau();
   float id =Float.parseFloat(obj.get("id").toString());
   String Nom =obj.get("nom").toString();
   String categorie =obj.get("categorieCadeau").toString();
   String description =obj.get("descriptionCadeau").toString();


   
   p.setId((int)id);
   p.setNom(Nom);
   p.setCategorie(categorie);
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
   
 public boolean deleteCadeau(int id) {
       String url=Statics.BASE_URL+"/cadeau/deletecadeau?id="+id;
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
     
       public boolean modifierCadeau(Cadeau Cadeau){
        
            String url=Statics.BASE_URL+"/cadeau/updatecadeau?id="+Cadeau.getId()+"&nom="+Cadeau.getNom()+"&categorie="+Cadeau.getCategorie()+"&description="+Cadeau.getDescription();
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
