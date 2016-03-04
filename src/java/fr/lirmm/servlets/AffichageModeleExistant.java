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

/**
 *
 * @author azaz1
 */
@WebServlet(name = "AffichageModeleExistant", urlPatterns = {"/affichageModeleExistant"})
public class AffichageModeleExistant extends HttpServlet {

    public static final String VUE = "/WEB-INF/affichageModeleExistant.jsp";
     
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        String breadcrumbs = "<li><a href=\"/index\">Home</a></li>";
        request.setAttribute( "title", "Tweet" );
        request.setAttribute( "topMenuName", "WorkFlow" );
        request.setAttribute( "breadcrumbs", breadcrumbs );
        this.getServletContext().getRequestDispatcher(VUE).forward( request, response );
    }
}
