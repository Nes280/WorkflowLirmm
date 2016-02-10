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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elsa Martel
 */
public class AffichageAPITweet extends HttpServlet{
    
    public static final String CHAMP_TWEET = "tweetAAnalyser";
    
    public static final String ATT_TWEET = "tweet";
    public static final String ATT_RESULTAT = "resultat";
    public static final String ATT_MESSAGE = "message";
    public static final String ATT_ERREUR = "erreur";
    
    public static final String VUE = "/WEB-INF/affichageAPITweet.jsp";
    
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        String tweet = request.getParameter(CHAMP_TWEET);
        String message; 
        boolean erreur; 
        String resultat = "";

        if(tweet.isEmpty()){
            message = "No tweet";
            erreur = true;
        }
        else{
            message = "Analyse du tweet";
            erreur = false;
            AnalyseDeSentiments a = new AnalyseDeSentiments();
            try {
                resultat = a.start(tweet);
            } catch (Exception ex) {
                Logger.getLogger(AffichageAPITweet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        String breadcrumbs = "<li><a href=\"/index\">Home</a></li>";
        request.setAttribute( "title", "Tweet" );
        request.setAttribute( "topMenuName", "WorkFlow" );
        request.setAttribute( "breadcrumbs", breadcrumbs );
        request.setAttribute(ATT_TWEET, tweet);
        request.setAttribute(ATT_RESULTAT, resultat);
        request.setAttribute(ATT_MESSAGE, message);
        request.setAttribute(ATT_ERREUR, erreur);
        this.getServletContext().getRequestDispatcher(VUE).forward( request, response );
    }
}

//<%@page import="analysedesentiments.AnalyseDeSentiments"%>
