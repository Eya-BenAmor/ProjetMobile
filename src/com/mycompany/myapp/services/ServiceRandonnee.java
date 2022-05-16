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
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Randonnee;


import com.mycompany.myapp.utils.Statics;

import java.io.IOException;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.*;
import com.codename1.io.Log;
import com.codename1.ui.FontImage;


/**
 *
 * @author MSI
 */
public class ServiceRandonnee {
    public ArrayList<Randonnee> randonnees;
    
    public static ServiceRandonnee instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceRandonnee() {
         req = new ConnectionRequest();
    }

    public static ServiceRandonnee getInstance() {
        if (instance == null) {
            instance = new ServiceRandonnee();
        }
        return instance;
    }

   public boolean ajouterRandonnee(Randonnee r){
        String url = Statics.BASE_URL +"/ajouterJson?nomRando="+r.getNomRando()+"&destination="+r.getDestination()+"&description="+r.getDescription()+"&categorieRando="+r.getCategorieRando()+"&dateRando="+r.getDateRando()+"&dureeRando="+r.getDureeRando()+"&prix="+r.getPrix()+"&note=0";
     req.setUrl(url);
      req.setPost(false);
       req.addArgument("nomRando", r.getNomRando()+"");
       req.addArgument("destination",r.getDestination()+"");
        req.addArgument("description", r.getDescription()+"");
         req.addArgument("categorieRando", r.getCategorieRando()+"");
         req.addArgument("dureeRando", r.getDureeRando()+"");
          req.addArgument("dateRando", r.getDateRando()+"");
         req.addArgument("prix", r.getPrix()+"");
          req.addArgument("note", r.getNote()+"");
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
      public ArrayList<Randonnee> parseRandonnees(String jsonText) {
        try {
            randonnees=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Randonnee t = new Randonnee();
             
                 float id = Float.parseFloat(obj.get("id").toString());
              
                  t.setId((int)id);
                    t.setNomRando(obj.get("nomRando").toString());
                    
                    t.setDestination(obj.get("destination").toString());
                      t.setDescription(obj.get("description").toString());
                     t.setDureeRando(obj.get("dureeRando").toString());
                      t.setCategorieRando(obj.get("categorieRando").toString());
                       float prix = Float.parseFloat(obj.get("prix").toString());
              
                  t.setPrix((int)prix);
                 
                  
                  //       float note = Float.parseFloat(obj.get("note").toString());
              
                //  t.setNote((float)note);
                       
                   System.out.println(t.getNote());    
                       
                          String DateConverter =  obj.get("dateRando").toString().substring(obj.get("dateRando").toString().indexOf("timestamp") + 10 , obj.get("dateRando").toString().lastIndexOf("}"));
                        
                        Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                        
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String dateString = formatter.format(currentTime);
                        t.setDateRando(dateString);
                       
                       
               
                 
                
                randonnees.add(t);
            }
            
            
        } catch ( IOException ex) {
            
        }
        return randonnees;
    }
    
    public ArrayList<Randonnee> getAllRandonnees(){
        req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"/listerJson/";
     
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                    randonnees = parseRandonnees(new String(req.getResponseData()));
               
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return randonnees;
    }
      
        public boolean supprimerRandonnee(int id) {
      

 String url=Statics.BASE_URL+"/supprimerJsonRando/"+id;
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
        
        
    public boolean modifierRandonnee(Randonnee r)
    {
        
         String url2=Statics.BASE_URL+"/modifierJson/"+r.getId()+"?nomRando="+r.getNomRando()+"&destination="+r.getDestination()+"&description="+r.getDescription()+"&categorieRando="+r.getCategorieRando()+"&dateRando="+r.getDateRando()+"&dureeRando="+r.getDureeRando()+"&prix="+r.getPrix();
            req.setUrl(url2);
      req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK= req.getResponseCode() == 200; //code success  http 200 ok
                req.removeResponseListener(this);
            }
        });
        System.out.println(url2);
        req.setUrl(url2);
        req.addResponseListener(e -> {
            String str = new String(req.getResponseData());//reponse jason 
            System.out.println("data ==> " + str);
        });
        ToastBar.showMessage("Randonnée modifiée", FontImage.MATERIAL_ACCESS_TIME);
        NetworkManager.getInstance().addToQueueAndWait(req);//execution te3 request
        return resultOK;
   
        
    }    
       
        
        
}


