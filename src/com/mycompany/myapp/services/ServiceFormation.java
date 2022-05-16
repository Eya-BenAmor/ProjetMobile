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
import com.mycompany.myapp.entities.Formation;
import com.mycompany.myapp.entities.ParticForm;

import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.Map;

/**
 *
 * @author seifi
 */
public class ServiceFormation {
     
    public static ServiceFormation instance =null;
    
    private ConnectionRequest req;
     public static boolean resultOk = true;
    
    public static ServiceFormation getInstance(){
    
    if(instance== null)
    instance=new ServiceFormation();
        
    return instance;
    
    }

    public ServiceFormation(){
        
        req=new ConnectionRequest();
    }
    
   
    //ajout
    public void ajoutformation(Formation formation){
       String url=Statics.BASE_URL+"/formation/addF?nomeq="+formation.getNomeq()+"&domaine="+formation.getDomaine()+"&duree="+formation.getDuree()+"&nomform="+formation.getNomform()+"&plan="+formation.getPlan()+"&date="+formation.getDate();
       req.setUrl(url);
       req.addResponseListener((e)->{
       
       String str= new String(req.getResponseData());
           System.out.println("data=.="+str);
          
       
       });
       NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    //affichage
    public ArrayList<Formation> afficherformation(){
        
        
        ArrayList<Formation> result = new ArrayList<>();
    String url=Statics.BASE_URL+"/formation/displayF";
    req.setUrl(url);
       req.addResponseListener(new ActionListener<NetworkEvent>(){
   
   @Override
   public void actionPerformed(NetworkEvent evt ){
   
   JSONParser jsonp;
   jsonp = new JSONParser();
   
   try {
   
   Map<String,Object>mapFormations=jsonp.parseJSON(new CharArrayReader(new String (req.getResponseData()).toCharArray()));
   List<Map<String,Object>> listOfMaps =(List<Map<String,Object>>) mapFormations.get("root");
   for (Map<String,Object> obj : listOfMaps){
   
   Formation p = new Formation();
   
   
   float id =Float.parseFloat(obj.get("id").toString());
        
        String nomeq =obj.get("nomeq").toString();
        String domaine =obj.get("domaine").toString();
        String duree =obj.get("duree").toString();
        String nomform =obj.get("nomform").toString();
        String plan =obj.get("plan").toString();
        String date =obj.get("date").toString();
        
        p.setNomeq(nomeq );
        p.setDomaine(domaine );
        p.setDuree(duree );
        p.setNomform(nomform );
        p.setPlan(plan );
        p.setDate(date );
       
        
   p.setId((int)id);
   
   
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
    
    //Detaile
    public Formation detaileformation(int  id,Formation formation)
    {
        
        String url=Statics.BASE_URL+"/formation/detailF"+id;
                req.setUrl(url);
                
                String str=new String(req.getResponseData());
                
                
                
                 req.addResponseListener(((evt)->{
                    
                        
                        JSONParser jsonp=new JSONParser();
                        
                        try{
                            Map<String,Object>obj=jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                            
                            
                                formation.setNomeq(obj.get("nomeq").toString());
                                formation.setDomaine(obj.get("domaine").toString());
                                formation.setNomform(obj.get("nomform").toString());
                                formation.setPlan(obj.get("plan").toString());
                                formation.setDate(obj.get("date").toString());
                            
                     
                 }catch(IOException ex ){
                            System.out.println("erur relier au sql "+ex.getMessage());
                 }
                        
                        System.out.println("data="+str);
        
    }));
                 NetworkManager.getInstance().addToQueueAndWait(req);
        
        return formation;
    }

    public void ajoutParticForm(ParticForm pf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    //delete
     public boolean deleteFormation(int id ){

 String url=Statics.BASE_URL+"/formation/deleteF?id="+id;
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
    
//modifier
     public boolean modifierFormation(Formation f){
        
            String url=Statics.BASE_URL+"/formation/updateF?id="+f.getId()+"&nomeq="+f.getNomeq()+"&domaine="+f.getDomaine()+"&duree="+f.getDuree()+"&nomform="+f.getNomform()+"&plan="+f.getPlan()+"&date="+f.getDate();
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
