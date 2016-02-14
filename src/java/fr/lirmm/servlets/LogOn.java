/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lirmm.servlets;

import fr.lirmm.beans.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.lirmm.db.Names;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Niels
 */
@WebServlet(name = "LogOn", urlPatterns = {"/logOn"})
public class LogOn extends HttpServlet {

   public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        String breadcrumbs = "<li><a href=\"/logon\">Log on</a></li>";
        request.setAttribute( "title", "Index" );
        request.setAttribute( "topMenuName", "WorkFlow" );
        request.setAttribute( "breadcrumbs", breadcrumbs );
        this.getServletContext().getRequestDispatcher( "/WEB-INF/logOn.jsp" ).forward( request, response );
   }
   
   public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
       String mail = request.getParameter("mail");
       String pass = request.getParameter("pass");
       String rePass = request.getParameter("re-pass");
       String firstName = request.getParameter("Fname");
       String lastName = request.getParameter("Lname");
       String additionalInformation = "";
       int polarity = 0;
       Names test = new Names();  
     
       
       if (pass.equals(rePass)) {
           try {
               if(!test.isInDataBase(mail)) // si le mail n'est pas déjà utilisé
               {
                   User utilisateur = new User(firstName,lastName,mail,pass,false);
                   Names ajout = new Names();
                   ajout.ajouterUtilisateur(utilisateur);
                   
                   polarity = 1;
                   additionalInformation = "You can now log.";
               }
               else{ // existe déjà
                   additionalInformation = "Email already used.";
               }
           } catch (SQLException ex) {
               //
           }
       }
       else { //erreur dans les mots de passe
           additionalInformation = "Passwords must be the same.";
       }
       
       request.setAttribute( "topMenuName", "WorkFlow" );       
       request.setAttribute("info", additionalInformation);
       request.setAttribute("polarity", polarity);
       
       //redirection vers la vue 
       if (polarity == 1) {
            String breadcrumbs = "<li><a href=\"/logon\">Log on</a></li>";
            request.setAttribute( "title", "Log on" );
            request.setAttribute( "breadcrumbs", breadcrumbs );
          this.getServletContext().getRequestDispatcher( "/WEB-INF/logIn.jsp" ).forward( request, response ); 
       } else{
            String breadcrumbs = "<li><a href=\"/logon\">Log in</a></li>";
            request.setAttribute( "title", "Log in" );
            request.setAttribute( "breadcrumbs", breadcrumbs );
           this.getServletContext().getRequestDispatcher( "/WEB-INF/logOn.jsp" ).forward( request, response );
       }    
   } 
}
