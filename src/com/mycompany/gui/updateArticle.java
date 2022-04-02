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
public class updateArticle extends BaseForm{
    
    
     super("Newsfeed",BoxLayout.y());
        Toolbar tb=new Toolbar(true);
        
        current = this;
        getTitleArea().setUIID("Container");
        setTitle("Ajout Article");
        getContentPane().setScrollVisible(false);
    
}
