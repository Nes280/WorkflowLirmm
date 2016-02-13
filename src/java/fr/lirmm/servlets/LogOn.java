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
}
