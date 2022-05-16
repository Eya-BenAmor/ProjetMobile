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
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Competition;
import com.mycompany.myapp.utils.Statics;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mezen Bayounes
 */
public class ServiceCompetition {
     public static ServiceCompetition instance =null;
    public static boolean resultOk = true;
    
    private ConnectionRequest req;
    
    public static ServiceCompetition getInstance(){
    
    if ( instance == null )
    instance = new ServiceCompetition();
    return instance;}
    
    
    public ServiceCompetition(){
    
    req = new ConnectionRequest();
    
    }
    
    
    
   // ajout 
    public void ajoutCompetition (Competition competition){
    
    String url=Statics.BASE_URL+"/competition/addcompetition?Nom="+competition.getNom()+"&distance="+competition.getDistance()+"&prix="+competition.getPrix()+"&date="+competition.getDate();
    req.setUrl(url);
    req.addResponseListener((e) -> {
    
    String str = new String(req.getResponseData());
            System.out.println("data=="+str);
    
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    }  
    
  
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    
      // affichage 
    
    public ArrayList<Competition>affichageCompetition(){
    ArrayList<Competition> result = new ArrayList<>();
    String url=Statics.BASE_URL+"/competition/displaycompetition";
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
   
   Competition p = new Competition();
   float id =Float.parseFloat(obj.get("id").toString());
   String Nom =obj.get("Nom").toString();
    int distance =Integer.parseInt(obj.get("distance").toString());
   int prix = (int) Double.parseDouble(obj.get("prix").toString());
 String DateConverter =  obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp") + 10 , obj.get("date").toString().lastIndexOf("}"));
                        
                        Date currentTime = new Date(Double.valueOf(DateConverter).longValue()  * 1000);
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                      
                        String dateString = formatter.format(currentTime);
                        
   
   p.setId((int)id);
   p.setNom(Nom);
   p.setDistance((int)distance);
   p.setPrix((int)prix);
   p.setDate(dateString);
   
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
     
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    //delete
    
     public boolean deleteCompetition(int id ){

 String url=Statics.BASE_URL+"/competition/deletecompetition?id="+id;
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
     
       public boolean modifierCompetition(Competition competition){
        
            String url=Statics.BASE_URL+"/competition/updatecompetition?id="+competition.getId()+"&Nom="+competition.getNom()+"&distance="+competition.getDistance()+"&prix="+competition.getPrix()+"&date="+competition.getDate();
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
