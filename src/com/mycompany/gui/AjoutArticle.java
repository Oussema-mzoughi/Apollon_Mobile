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
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Article;
import com.mycompany.services.serviceArticle;
import com.mycompany.utils.Statics;
import java.io.IOException;

/**
 *
 * @author Oussema
 */
public class AjoutArticle extends BaseForm{
    
    
    
    Form current;
    public AjoutArticle(Resources res)
    {
        super("Newsfeed",BoxLayout.y());
        Toolbar tb=new Toolbar(true);
        
        current = this;
        getTitleArea().setUIID("Container");
        setTitle("Ajout Article");
        getContentPane().setScrollVisible(false);
        
        
        TextField titre= new TextField("","Titre Article");
        titre.setUIID("TextFieldBlack");
        addStringValue("titre",titre);
        TextField description= new TextField("","Description Article");
        description.setUIID("TextFieldBlack");
        addStringValue("description",description);
        TextField Type= new TextField("","Type Article");
         Type.setUIID("TextFieldBlack");
        addStringValue("Type",Type);
        TextField tfImageName = new TextField("", "Image name");
        tfImageName.setUIID("TextFieldBlack");
        addStringValue("Nom de l'image",tfImageName);
       
       /* Button btnUpload = new Button("Upload");
        btnUpload.addActionListener((evt) -> {
            if (!"".equals(tfImageName.getText())) {
                MultipartRequest cr = new MultipartRequest();
                String filePath = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
                
                cr.setUrl(Statics.URL_UPLOAD);
                cr.setPost(true);
                String mime = "image/jpeg";
                try {
                    cr.addData("file", filePath, mime);
                } catch (IOException ex) {
                    Dialog.show("Error", ex.getMessage(), "OK", null);
                }
                cr.setFilename("file", tfImageName.getText() + ".jpg");//any unique name you want

                InfiniteProgress prog = new InfiniteProgress();
                Dialog dlg = prog.showInifiniteBlocking();
                cr.setDisposeOnCompletion(dlg);
                NetworkManager.getInstance().addToQueueAndWait(cr);
                Dialog.show("Success", "Image uploaded", "OK", null);
            }else{
                Dialog.show("Error", "Invalid image name", "Ok", null);
            }
        });
         add(btnUpload);
        */
        
       
        
        
        Button btnAjouter= new Button("Ajouter");
         addStringValue("",btnAjouter);
        
           btnAjouter.addActionListener((e)->{
           
           try
           {
               if(titre.getText()==""||description.getText()=="")
               {
                   Dialog.show("Veuillez vérifier les données","","Annuler","OK");
               }
               else
               {
                   InfiniteProgress ip=new InfiniteProgress();;
                   final Dialog idialog = ip.showInfiniteBlocking();
                   
                   Article a= new Article(String.valueOf(titre.getText().toString()),
                           String.valueOf(description.getText().toString()),
                           String.valueOf(Type.getText().toString()),
                           String.valueOf(tfImageName.getText().toString()));
                   
                   System.out.println("Data article == "+a);
                   
                   
                   serviceArticle.getInstance().ajoutArticle(a);
                   idialog.dispose();
                   new ListeArticle(res).show();
                   refreshTheme();
               }
           }
           catch(Exception ex)
           {
                 ex.printStackTrace();
           }
               
               
           
           });
        
    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s,"PaddedLabel"))
        .add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));

    }
    
    
}
