/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lirmm.servlets;
import fr.lirmm.beans.User;
import fr.lirmm.db.Names;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author niels
 */
@WebServlet(name = "LogIn", urlPatterns = {"/LogIn"})
public class LogIn extends HttpServlet {

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //deconnexion
        HttpSession session = request.getSession();
        session.invalidate();
        
        String breadcrumbs = "<li><a href=\"/logIn\">Log in</a></li>";
        request.setAttribute( "title", "Log in" );
        request.setAttribute( "topMenuName", "WorkFlow" );
        request.setAttribute( "breadcrumbs", breadcrumbs );
    
        this.getServletContext().getRequestDispatcher( "/WEB-INF/logIn.jsp" ).forward( request, response );
       
    }
public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
     
        Names utilisateur = new Names();
        String mail = request.getParameter("mail");
        String pass = request.getParameter("pass");
        User connecter = utilisateur.recupererUtilisateurs(mail,pass);
        if(connecter.Fname != null)
        {
            HttpSession session = request.getSession();
            session.setAttribute("nom", connecter.Lname);
            session.setAttribute("prenom", connecter.Fname);
            session.setAttribute("mail", connecter.Mail);
            session.setAttribute("isLog", "1");
          
            request.setAttribute("utilisateur", utilisateur.recupererUtilisateurs(mail,pass));
            String breadcrumbs = "<li><a href=\"/index\">Index</a></li>";
            request.setAttribute( "title", "Index" );
            request.setAttribute( "topMenuName", "WorkFlow" );
            request.setAttribute( "breadcrumbs", breadcrumbs );
            
            this.getServletContext().getRequestDispatcher( "/WEB-INF/index.jsp" ).forward( request, response );
        }
        else{
            //session
            HttpSession session = request.getSession();
            session.invalidate();
            //preparation des chaînes
            String additionalInformation = "The email and/or password entered do not allow you to connect.";
            String breadcrumbs = "<li><a href=\"/logIn\">Log in</a></li>";
            //polatité de l'information 0 = alert, 1 = neutre ou positive
            int polarity = 0; 
            //envoie des attribut dans la requête
            request.setAttribute( "title", "Log in" );
            request.setAttribute( "topMenuName", "WorkFlow" );
            request.setAttribute( "breadcrumbs", breadcrumbs );
            request.setAttribute("info", additionalInformation);
            request.setAttribute("polarity", polarity);
            //redirection vers la jsp
            this.getServletContext().getRequestDispatcher( "/WEB-INF/logIn.jsp" ).forward( request, response );
        }
    }
}    
