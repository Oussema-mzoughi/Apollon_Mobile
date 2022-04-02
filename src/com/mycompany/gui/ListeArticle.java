/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Article;
import com.mycompany.services.serviceArticle;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Oussema
 */
public class ListeArticle extends BaseForm{
    
       Form current;
    public ListeArticle(Resources res)
    {
        super("Newsfeed",BoxLayout.y());
        Toolbar tb=new Toolbar(true);
        
        current = this;
        getTitleArea().setUIID("Container");
        setTitle("Liste  Articles");
        getContentPane().setScrollVisible(false);
        
        ArrayList<Article>list=serviceArticle.getInstance().affichageArticle();
        
        for ( Article art : list)
        {
            addButton(art.getTitre(),art.getDescription(),art.getType(),art.getImg(),art.getDate(),art,res);
        }
        
    }

    private void addButton(String titre, String description, String Type, String img,String date,Article art,Resources res) {
        Container cnt= new Container(new BorderLayout());
        TextArea titr=new TextArea("titre :"+titre);
        titr.setUIID("NewTopLine");
        titr.setEditable(false);
        
  
        
            TextArea typ=new TextArea("Type :"+Type);
        typ.setUIID("NewTopLine");
        typ.setEditable(false);
        
        
        TextArea image=new TextArea("image :"+img);
        image.setUIID("NewTopLine");
        image.setEditable(false);
        
       
        
      
       
     
            
        Label supprimer=new Label("");
        supprimer.setUIID("NewsTopLine");
        Style supprimerStyle= new Style(supprimer.getUnselectedStyle());
        supprimerStyle.setFgColor(0xf21f1f);
        
        FontImage supprimerImage=FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprimerStyle);
        supprimer.setIcon(supprimerImage);
        supprimer.setTextPosition(RIGHT);
        
        supprimer.addPointerPressedListener(l ->{
        
          Dialog dig= new Dialog("Suppression");
          
          if(dig.show("Suppression","Vous voulez supprimer cet article ?","Annuler","Oui"))
          {
              dig.dispose();
          }
          else
          {
              dig.dispose();
             serviceArticle.getInstance().deleteArticle(art.getId());
            
                    new ListeArticle(res).show();
                   refreshTheme();
          }
        
        
        });
        
          Label modifier=new Label("");
        supprimer.setUIID("NewsTopLine");
        Style modifierStyle= new Style(modifier.getUnselectedStyle());
        modifierStyle.setFgColor(0xf7ad02);
        
        FontImage modifierImage=FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
        modifier.setIcon(modifierImage);
        modifier.setTextPosition(LEFT);
                
         modifier.addPointerPressedListener(l ->{
        
          Dialog dig= new Dialog("Modifier");
          
          if(dig.show("Modification","Vous voulez modifier cet article ?","Annuler","Oui"))
          {
              dig.dispose();
          }
          else
          {
              dig.dispose();
             serviceArticle.getInstance().updateArticle(art);
            
                    new ListeArticle(res).show();
                   refreshTheme();
          }
        
        
        });
           cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(BoxLayout.encloseX(titr),BoxLayout.encloseX(typ,supprimer,modifier)));
        //cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(BoxLayout.encloseX(supprimer)));
        add(cnt);
    }
    
    
    
    
}
