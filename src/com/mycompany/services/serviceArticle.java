/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.mycompany.entities.Article;
import com.mycompany.utils.Statics;
import java.util.ArrayList;
import com.codename1.io.JSONParser;

import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Oussema
 */
public class serviceArticle {
    
    public static serviceArticle instance =null;

    private ConnectionRequest req;
     public static serviceArticle getInstance()
     {
         if(instance==null)
             instance = new serviceArticle();
         return instance;
    }
    
     
    public serviceArticle() {
        
        req=new ConnectionRequest();
        
    }
    
    public void ajoutArticle(Article article)
    {
        String url=Statics.BASE_URL+"/article/add_article_api?titre="+article.getTitre()+"&description="+article.getDescription()
                +"&type="+article.getType()+"&img="+article.getImg();
        req.setUrl(url);
        req.addResponseListener((e) -> {
        String str=new String(req.getResponseData());
        System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    
    public ArrayList<Article> affichageArticle()
    {
        ArrayList<Article> result=new ArrayList<>();
        String url=Statics.BASE_URL+"/article/list_article_api";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>(){
        
            public void actionPerformed(NetworkEvent evt)
            {
            JSONParser jsonp;
            jsonp=new JSONParser();
            
            try
            {
                Map<String,Object>mapArticles = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                List<Map<String,Object>> listOfMaps=(List<Map<String,Object>>) mapArticles.get("root");
                
                for(Map<String,Object> obj : listOfMaps)
                {
                    Article art=new Article();
                    float id=Float.parseFloat(obj.get("id").toString());
                    
                    String img=obj.get("img").toString();
                    String titre=obj.get("titre").toString();
                    String description=obj.get("Description").toString();
                    String type=obj.get("Type").toString(); 
                    
                    art.setDescription(description);
                    art.setId((int) id);
                    art.setTitre(titre);
                    art.setImg(img);
                    art.setType(type);
                    
                    result.add(art);
                    
                    
                }
            
            }catch (Exception ex)
            {
                  ex.printStackTrace();
            }
            }
            });
               
            
      NetworkManager.getInstance().addToQueueAndWait(req);
      return result;
    }
    
    
    
    public Article singleArticle(int id,Article article)
    {
        String url=Statics.BASE_URL+"/article/single_article_api?id="+id;
        req.setUrl(url);
        String str=new String(req.getResponseData());
        req.addResponseListener(((evt)->{
            
             JSONParser jsonp;
            jsonp=new JSONParser();
            
            try
            {
             Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                    String img=obj.get("img").toString();
                    String titre=obj.get("titre").toString();
                    String description=obj.get("description").toString();
                    String type=obj.get("type").toString(); 
                    
                    article.setDescription(description);
                    article.setId((int) id);
                    article.setTitre(titre);
                    article.setImg(img);
                    article.setType(type);
            }
            catch (IOException ex)
            {
                System.out.println("error related to sql: ( "+ex.getMessage()+" )");
            }
            
             System.out.println("data ===  "+str);
            
            
        }));
            NetworkManager.getInstance().addToQueueAndWait(req);
            return article;
    }
    
    
     public void deleteArticle(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Statics.BASE_URL+"/article/deletearticle_api/"+id);
        con.setPost(false);
        con.addResponseListener((evt) -> {
            System.out.println(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
    
    
}
