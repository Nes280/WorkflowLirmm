/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lirmm.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import analysedesentiments.AnalyseDeSentiments;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Elsa Martel
 */
public class AffichageAPITweet extends HttpServlet{
    
    public static final String CHAMP_TWEET = "tweetAAnalyser";
    public static final String CHAMP_FILE = "fileUpload";
    public static final String CHAMP_CHOIX = "choix";
    
    public static final String CHEMIN = "chemin";
    public static final int TAILLE_TAMPON = 10240;
    
    public static final String ATT_TWEET = "tweet";
    public static final String ATT_MESSAGE = "message";
    public static final String ATT_ERREUR = "erreur";
    
    public static final String VUE = "/WEB-INF/affichageAPITweet.jsp";
    
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        
        //variable pour le formulaire de saisie de tweet
        String tweet = request.getParameter(CHAMP_TWEET);
        String nom = request.getParameter(CHAMP_FILE);
        String choix = request.getParameter(CHAMP_CHOIX);
        //System.out.println("nom " + nom);
        
        //variable pour le formulaire de fichier
        //boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        //System.out.println("isMultipart " + isMultipart);

        //Preparation des résultats
        Map<String, String> listeTweet = new HashMap<String, String>();
        String message = ""; 
        boolean erreur = true; 
        String resultat = "";
        
        //on utilise le formulaire de saisie de texte
        if(choix.equals("saisieTexte"))
        {
            //il n'y a pas de tweet à traiter
            if(tweet == null ){
                message = "No tweet";
                erreur = true;
            }
            //on a un tweet à traiter
            else if (tweet != null){
                message = "Analyse du tweet";
                erreur = false;
                String delim = "\n";
                String[] tokens = tweet.split(delim);
                AnalyseDeSentiments a = new AnalyseDeSentiments();

                for(int i = 0; i < tokens.length; i++){
                    try {
                        resultat = a.start(tokens[i]);
                        listeTweet.put(tokens[i], resultat);
                    } catch (Exception ex) {
                        Logger.getLogger(AffichageAPITweet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }  
            }
        }
        //on utilise le formulaire d'upload de fichier
        else if(choix.equals("uploadFile")){
            System.out.println("uploadFile");
                        ServletFileUpload.isMultipartContent(request);
            File savedFile = new File("./fichiers/");
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(TAILLE_TAMPON);
            ServletContext servletContext = this.getServletConfig().getServletContext();
            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repository);
            
            ServletFileUpload upload = new ServletFileUpload(factory);
            List items;
            try {
                items = upload.parseRequest(request);
                Iterator iter = items.iterator();
                while (iter.hasNext()) {
                   FileItem item = (FileItem) iter.next();
                   if (item.isFormField()) {

                    } 
                    else {
                        if (!item.getName().trim().equals("")){
                               File fullFile = new File(item.getName());
                               fullFile.getName();
                              // c'est ici qu'il faudra changer le répertoire de  destination
                               savedFile = new File("./fichiers/", fullFile.getName());
                               item.write(savedFile);
                        }
                    }
                }
            } catch (FileUploadException ex) {
                Logger.getLogger(AffichageAPITweet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(AffichageAPITweet.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
        String breadcrumbs = "<li><a href=\"/index\">Home</a></li>";
        request.setAttribute( "title", "Tweet" );
        request.setAttribute( "topMenuName", "WorkFlow" );
        request.setAttribute( "breadcrumbs", breadcrumbs );
        request.setAttribute(ATT_TWEET, listeTweet);
        request.setAttribute(ATT_MESSAGE, message);
        request.setAttribute(ATT_ERREUR, erreur);
        this.getServletContext().getRequestDispatcher(VUE).forward( request, response );
    }

} 

//<%@page import="analysedesentiments.AnalyseDeSentiments"%>
