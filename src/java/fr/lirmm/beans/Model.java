/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lirmm.beans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author niels
 */
public class Model {
    private String file_name;
    private String user_mail;
    private String info;
    private String user_id;
    
    public Model(String nom_fichier, String mail_user, String info, String id_user) 
    { 
        this.file_name = nom_fichier;
        this.user_mail = mail_user;
        this.info = info;
        this.user_id = id_user;
    }
    
    public int create()throws IOException
    {
        File dir = new File("./user_models/" + user_mail); 
        if(!dir.exists())
        {
          // si non on le crée
          dir.mkdir();
        }
        FileOutputStream os = new FileOutputStream("./user_models/" + user_mail + "/" + file_name + ".model");
        os.write(0);
        return 1;
    }
    
    public String getNom()
    {
        return file_name;
    }
    
    public String getMail()
    {
        return user_mail;
    }
    
    public String getInfo()
    {
        return info;
    }
    public String getId()
    {
        return user_id;
    }
}
