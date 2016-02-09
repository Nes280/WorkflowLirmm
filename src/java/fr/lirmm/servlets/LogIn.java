/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lirmm.servlets;

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

/**
 *
 * @author niels
 */
@WebServlet(name = "LogIn", urlPatterns = {"/LogIn"})
public class LogIn extends HttpServlet {

public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    
    String breadcrumbs = "<li><a href=\"/logIn\">Log in</a></li>";
        request.setAttribute( "title", "Log in" );
        request.setAttribute( "topMenuName", "WorkFlow" );
        request.setAttribute( "breadcrumbs", breadcrumbs );
    
        this.getServletContext().getRequestDispatcher( "/WEB-INF/logIn.jsp" ).forward( request, response );
    }
}    
