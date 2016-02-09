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
    
    public static final String VUE = "/WEB-INF/affichageAPITweet.jsp";
    
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        String tweet = request.getParameter(CHAMP_TWEET);
        AnalyseDeSentiments a = new AnalyseDeSentiments();
        String resultat = "";
        try {
            resultat = a.start(tweet);
        } catch (Exception ex) {
            Logger.getLogger(AffichageAPITweet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String breadcrumbs = "<li><a href=\"/index\">Home</a></li>";
        request.setAttribute( "title", "Tweet" );
        request.setAttribute( "topMenuName", "WorkFlow" );
        request.setAttribute( "breadcrumbs", breadcrumbs );
        request.setAttribute("tweet", tweet);
        request.setAttribute("resultat", resultat);
        this.getServletContext().getRequestDispatcher(VUE).forward( request, response );
    }
}

//<%@page import="analysedesentiments.AnalyseDeSentiments"%>
