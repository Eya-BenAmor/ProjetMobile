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
import com.mycompany.myapp.entities.Commentaire;
import com.mycompany.myapp.entities.Participant;
import com.mycompany.myapp.entities.Publication;
import com.mycompany.myapp.entities.Randonnee;
import com.mycompany.myapp.gui.SessionManager;
import static com.mycompany.myapp.services.ServiceUser.resultOK;
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
public class ServiceCommentaire {

    public ArrayList<Commentaire> commentaire;
    public static ServiceCommentaire instance = null;
    public static boolean resultOk = true;

    private ConnectionRequest req;

    public static ServiceCommentaire getInstance() {

        if (instance == null) {
            instance = new ServiceCommentaire();
        }
        return instance;
    }

    public ServiceCommentaire() {

        req = new ConnectionRequest();

    }

    // ajout 
    public boolean ajoutCommentaire(Commentaire com) {

        String url = Statics.BASE_URL + "/ajouterJsonCommentaire?contenu=" + com.getContenu() + "&id_pub=" + com.getId_pub();
        req.setUrl(url);
        req.setPost(false);
        req.addArgument("contenu", com.getContenu() + "");
        req.addArgument("id_pub", com.getId_pub() + "");

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
    // affichage 

    public ArrayList<Commentaire> parseCommentaires(String jsonText) {
        try {
            commentaire = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Commentaire t = new Commentaire();

                float id = Float.parseFloat(obj.get("id").toString());

                t.setId((int) id);

                t.setContenu(obj.get("contenu").toString());
                LinkedHashMap<String, Double> r = (LinkedHashMap<String, Double>) obj.get("publication");

                float id_pub = Float.parseFloat(r.get("id").toString());

                t.setId_pub((int) id_pub);

                commentaire.add(t);
            }

        } catch (IOException ex) {

        }
        return commentaire;
    }
  public ArrayList<Commentaire> getAllCommentaireByPub(int id){
        req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"/listerJsonCommentaireByPub/"+id;
     
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                commentaire = parseCommentaires(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return commentaire;
    }
}
