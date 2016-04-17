/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lirmm.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Niels
 */
public class TrainRun extends HttpServlet {

    //A CODER
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.setAttribute("fileName", fileName);
        request.setAttribute( "title", "Result" );
        request.setAttribute( "topMenuName", "WorkFlow" );

        this.getServletContext().getRequestDispatcher( "/WEB-INF/affichageModeleUtilisateur.jsp" ).forward( request, response );
    }

}
