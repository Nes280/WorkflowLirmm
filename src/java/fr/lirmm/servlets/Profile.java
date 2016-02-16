/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lirmm.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import fr.lirmm.db.Names;
/**
 *
 * @author Niels
 */
@WebServlet(name = "profile", urlPatterns = {"/profile"})
public class Profile extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String edit = request.getParameter("edit");
        if (edit.equals("settings")) {
            request.setAttribute("edit",1); //permet de dire à la vue que l'on veux changer des parametres.
        }
        else if (edit.equals("password")) {
            request.setAttribute("edit",2); //permet de dire à la vue que l'on veux changer des parametres.
        }
        String breadcrumbs = "<li><a href=\"/logIn\">Profile</a></li>";
        request.setAttribute( "title", "Profile" );
        request.setAttribute( "topMenuName", "WorkFlow" );
        request.setAttribute( "breadcrumbs", breadcrumbs );
    
        this.getServletContext().getRequestDispatcher( "/WEB-INF/profile.jsp" ).forward( request, response );
       
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String lMail = (String) session.getAttribute("mail");
                
        String change = request.getParameter("change");
        if (change.equals("settings")) { 
        //ici on va changer le nom le prenom et le mail, o
        //on utilise lMail qui est l'ancien mail
        //et nMail qui est le nouveau mail
        // on ne s'occupe pas de savoir ques qui a changé 
            String nMail = request.getParameter("Mail");
            String Fname = request.getParameter("Fname");
            String Lname = request.getParameter("Lname");
            Names name = new Names();
            try{
                name.alterUtilisateur(lMail, nMail, Lname, Fname);
                session.setAttribute("nom", Lname);
                session.setAttribute("prenom", Fname);
                session.setAttribute("mail", nMail);
                
            }catch(Exception e){}
        }
        else if (change.equals("password")) {
            String pass = request.getParameter("pass");
            String rePass = request.getParameter("rePass");
     
            if (pass.equals(rePass) && !pass.equals("")) {
                Names name = new Names();
                try{
                    name.alterPassword(lMail, pass);
                }catch(Exception e){}
            }
            else{
                String breadcrumbs = "<li><a href=\"/logIn\">Profile</a></li>";
                request.setAttribute( "title", "Profile" );
                request.setAttribute( "topMenuName", "WorkFlow" );
                request.setAttribute( "breadcrumbs", breadcrumbs );
                request.setAttribute( "edit", 2 );
                this.getServletContext().getRequestDispatcher( "/WEB-INF/profile.jsp" ).forward( request, response );
            }
        }
        
        String breadcrumbs = "<li><a href=\"/logIn\">Profile</a></li>";
        request.setAttribute( "title", "Profile" );
        request.setAttribute( "topMenuName", "WorkFlow" );
        request.setAttribute( "breadcrumbs", breadcrumbs );
    
        this.getServletContext().getRequestDispatcher( "/WEB-INF/profile.jsp" ).forward( request, response );
       
    }
}
