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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Elsa Martel
 */
public class AffichageAPITweet extends HttpServlet{
    
    public static final String CHAMP_TWEET = "tweetAAnalyser";
    public static final String CHAMP_FILE = "fileUpload";
    
    public static final String CHEMIN = "chemin";
    public static final int TAILLE_TAMPON = 10240;
    
    public static final String ATT_TWEET = "tweet";
    //public static final String ATT_RESULTAT = "resultat";
    public static final String ATT_MESSAGE = "message";
    public static final String ATT_ERREUR = "erreur";
    
    public static final String VUE = "/WEB-INF/affichageAPITweet.jsp";
    
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        
        //variable pour le formulaire de saisie de tweet
        String tweet = request.getParameter(CHAMP_TWEET);
        String nom = request.getParameter(CHAMP_FILE);
        System.out.println("nom " + nom);
        
        //variable pour le formulaire de fichier
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        
        System.out.println("isMultipart " + isMultipart);
        
        //Mettre les valeurs à null
        if(tweet == "" ){
            tweet = null;
        }

        //Preparation des résultats
        Map<String, String> listeTweet = new HashMap<String, String>();
        String message = ""; 
        boolean erreur = true; 
        String resultat = "";

        //cas ou les 2 formulaires sont vides
        if(tweet == null && !isMultipart ){
            message = "No tweet";
            erreur = true;
        }
        //cas ou il y a un tweet
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
