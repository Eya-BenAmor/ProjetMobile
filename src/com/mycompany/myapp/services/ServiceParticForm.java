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
import com.mycompany.myapp.entities.ParticForm;

import static com.mycompany.myapp.services.ServiceFormation.resultOk;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author seifi
 */
public class ServiceParticForm {
    
    public static ServiceParticForm instance =null;
    
    private ConnectionRequest req;
    
    public static ServiceParticForm getInstance(){
    
    if(instance== null)
    instance=new ServiceParticForm();
        
    return instance;
    
    }

    public ServiceParticForm(){
        
        req=new ConnectionRequest();
    }
    
   
    //ajout
    public void ajoutParticForm(ParticForm pf){
       String url=Statics.BASE_URL+"/part/addpf?nom="+pf.getNom()+"&prenom="+pf.getPrenom()+"&age="+pf.getAge()+"&exp="+pf.getExp()+"&so_domaine="+pf.getSo_domaine()+"&so_ass="+pf.getSo_ass()+"&Numero="+pf.getNumero()+"&idform="+pf.getId_formation();
       req.setUrl(url);
       req.addResponseListener((e)->{
       
       String str= new String(req.getResponseData());
           System.out.println("data=.="+str);
          
       
       });
       NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    
    
    
    
    //affichage
    public ArrayList<ParticForm> afficherpf(){
                    
        ArrayList<ParticForm> result = new ArrayList<>();
    String url=Statics.BASE_URL+"/part/displayPF";
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
   
   ParticForm pf = new ParticForm();
   
   
 float id =Float.parseFloat(obj.get("id").toString());
        
        String nom =obj.get("nom").toString();
        String prenom =obj.get("prenom").toString();
        float age = Float.parseFloat(obj.get("age").toString());
        String exp =obj.get("exp").toString();
//        String so_domaine =obj.get("so_domaine").toString();
   // String so_ass =obj.get("so_ass").toString();
        float Numero = Float.parseFloat(obj.get("Numero").toString());
        
        
        pf.setNom(nom );
        pf.setPrenom(prenom );
        pf.setAge((int)age );
        pf.setExp(exp );
        //pf.setSo_domaine(so_domaine );
       // pf.setSo_ass(so_ass );
        pf.setNumero((int)Numero );
        pf.setId((int)id);
       

   
   result.add(pf);
   
   }
   }catch (Exception ex){
   ex.printStackTrace();
   
   }
   
   }
   
   
   });
   
    
      NetworkManager.getInstance().addToQueueAndWait(req);
      return result;
    
        
}
 public boolean deletepf(int id ){

 String url=Statics.BASE_URL+"/part/deletePF?id="+id;
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
 
 
  public boolean modifierPF(ParticForm f){
        
            String url=Statics.BASE_URL+"/part/updatePF?id="+f.getId()+"&nom="+f.getNom()+"&Prenom"+f.getPrenom()+"&age="+f.getAge()+"&exp="+f.getExp()+"&so_domaine="+f.getSo_domaine()+"&so_ass="+f.getSo_ass()+"&Numero="+f.getNumero();
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
