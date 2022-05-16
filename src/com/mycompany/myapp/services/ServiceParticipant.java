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
import com.codename1.ui.FontImage;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Participant;
import com.mycompany.myapp.entities.Randonnee;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.gui.SessionManager;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MSI
 */
public class ServiceParticipant {

   
     public ArrayList<Participant> participant;
    
    public static ServiceParticipant instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceParticipant() {
         req = new ConnectionRequest();
    }

    public static ServiceParticipant getInstance() {
        if (instance == null) {
            instance = new ServiceParticipant();
        }
        return instance;
    }

     public boolean ajouterParticipant(Participant p){
        String url = Statics.BASE_URL +"/ajouterJsonParticipant?age="+p.getAge()+"&tel="+p.getTel()+"&maladie="+p.getMaladie()+"&classe="+p.getClasse()+"&randonnee="+p.getRandonnee_id()+"&user="+p.getUser_id();
     req.setUrl(url);
      req.setPost(false);
       req.addArgument("age", p.getAge()+"");
       req.addArgument("tel",p.getTel()+"");
        req.addArgument("maladie", p.getMaladie()+"");
         req.addArgument("classe ", p.getClasse()+"");
         req.addArgument("randonnee_id ", p.getRandonnee_id()+"");
         req.addArgument("user_id", p.getUser_id()+"");
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

    public ArrayList<Participant> parseParticipants(String jsonText){
        try {
            participant=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Participant t = new Participant();
             
                 float id = Float.parseFloat(obj.get("id").toString());
              
                  t.setId((int)id);
                    float age = Float.parseFloat(obj.get("age").toString());
              
                  t.setAge((int)age);
                    
                    t.setTel(obj.get("tel").toString());
                      t.setMaladie(obj.get("maladie").toString());
                     t.setClasse(obj.get("classe").toString());
                 LinkedHashMap<String,Double> r =  (LinkedHashMap<String,Double>) obj.get("randonnee");
              
                    LinkedHashMap<String,Double> u =  (LinkedHashMap<String,Double>) obj.get("user");
                 
                  float rando = Float.parseFloat(r.get("id").toString());
              
                
                 t.setRandonnee_id((int)rando);
                  float user = Float.parseFloat(u.get("id").toString());
                 
              
                  t.setUser_id((int)user);
              
      
                    
                     
                 
                  
               
                 
                
                participant.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return  participant;
    }
    
    public ArrayList<Participant> getAllParticipants(){
        req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"/listerJsonParticipant/"+SessionManager.getId();
     
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                participant = parseParticipants(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return participant;
    }
    
    
    
     public ArrayList<Participant> getAllParticipantsByRando(int id){
        req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"/listerJsonParticipantByRando/"+id;
     
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                participant = parseParticipants(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return participant;
    }
    
    
    
    
          public boolean SupprimerParticipant(int id ){

 String url=Statics.BASE_URL+"/supprimerJsonParticipant/"+id;
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
    
public boolean modifierParticipant(Participant p)
    {
        
         String url2=Statics.BASE_URL+"/modifierJsonParticipant/"+p.getId()+"?age="+p.getAge()+"&tel="+p.getTel()+"&maladie="+p.getMaladie()+"&classe="+p.getClasse();
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
        ToastBar.showMessage("Participation modifiée", FontImage.MATERIAL_ACCESS_TIME);
        NetworkManager.getInstance().addToQueueAndWait(req);//execution te3 request
        return resultOK;
   
    
    }





    }
     
    
        
    
    

