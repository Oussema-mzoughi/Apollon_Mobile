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
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
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
            addButton(art.getTitre(),art.getDescription(),art.getType(),art.getImg(),art.getDate());
        }
        
    }

    private void addButton(String titre, String description, String Type, String img,String date) {
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
        
       
        
      
       
        cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(BoxLayout.encloseX(titr)
        ,BoxLayout.encloseX(typ),BoxLayout.encloseX(image)));
  
        add(cnt);
    }
    
    
    
    
}
