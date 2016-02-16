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
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Part;

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
        System.out.println("tweet au debut " + tweet + " fin tweet");

        
        //variable pour le formulaire de fichier
        String chemin; 
        Part part;
        String file; 
        
        //formulaire du fichier
        if(tweet == "" || tweet == null){
            chemin = "d:/fichiers/";
            part= request.getPart(CHAMP_FILE);
            file = getNomFichier(part);
            System.out.println("file dans if " + file + " fin file");

        }
        else{
            file = "";
            part = null;
            chemin = "";
        }
        
        //Mettre les valeurs à null
        if(tweet == ""){
            tweet = null;
        }
        else if(file == ""){
            file = null;
        }
        
        System.out.println("tweet " + tweet + " fin tweet");
        System.out.println("file " + file + " fin file");

        //Preparation des résultats
        Map<String, String> listeTweet = new HashMap<String, String>();
        String message = ""; 
        boolean erreur = true; 
        String resultat = "";

        //cas ou les 2 formulaires sont vides
        if(tweet == null && file == null){
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
        //cas ou il y a un fichier 
        else if(file != null){
            System.out.println("il y a un fichier");
            String nomChamp = part.getName();
            /*file = file.substring(file.lastIndexOf( '/' ) + 1)
                    .substring(file.lastIndexOf('\\') + 1);*/
            System.out.println("part " + part);
            System.out.println("file " + file);
            System.out.println("chemin " + chemin);

            ecrireFichier(part, file, chemin);
            System.out.println("file " + file );
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

    
    private static String getNomFichier( Part part ) {
        /* Boucle sur chacun des paramètres de l'en-tête "content-disposition". */
        for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
            /* Recherche de l'éventuelle présence du paramètre "filename". */
            if ( contentDisposition.trim().startsWith("filename") ) {
                /* Si "filename" est présent, alors renvoi de sa valeur, c'est-à-dire du nom de fichier. */
                return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 );
            }
        }
        /* Et pour terminer, si rien n'a été trouvé... */
        return null;
    }
    
    
    private void ecrireFichier( Part part, String nomFichier, String chemin ) throws IOException {
        /* Prépare les flux. */
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try {
            /* Ouvre les flux. */
            System.out.println("entrée " + entree);
            entree = new BufferedInputStream( part.getInputStream(), TAILLE_TAMPON );
            System.out.println("entrée " + entree);

            System.out.println("sortie " + sortie);
            sortie = new BufferedOutputStream( new FileOutputStream( new File( chemin + nomFichier ) ),
                    TAILLE_TAMPON );
            System.out.println("sortie " + sortie);

            /*
             * Lit le fichier reçu et écrit son contenu dans un fichier sur le
             * disque.
             */
            byte[] tampon = new byte[TAILLE_TAMPON];
            int longueur;
            while ( ( longueur = entree.read( tampon ) ) > 0 ) {
                sortie.write( tampon, 0, longueur );
            }
        } finally {
            try {
                sortie.close();
            } catch ( IOException ignore ) {
            }
            try {
                entree.close();
            } catch ( IOException ignore ) {
            }
        }
    }
     
}

//<%@page import="analysedesentiments.AnalyseDeSentiments"%>
