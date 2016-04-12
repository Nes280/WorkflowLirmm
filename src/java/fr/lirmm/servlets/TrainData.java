/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lirmm.servlets;

import fr.lirmm.db.BaseDeDonnee;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Niels
 */
public class TrainData extends HttpServlet {
    
    

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String fileId = request.getParameter("fileId");
        String form = request.getParameter("hidden");
        System.out.println(form);
        if (form == null) {
            System.out.println("test@@@@@@@@@@@@@@@@");
        request.setAttribute("fileId", fileId);
        request.setAttribute( "title", "Train" );
        request.setAttribute( "topMenuName", "WorkFlow" );

        this.getServletContext().getRequestDispatcher( "/WEB-INF/trainForm.jsp" ).forward( request, response );
        }
        else if (form.equals("true")){
            System.out.println("test################");
            request.setAttribute("fileId", fileId);
        request.setAttribute( "title", "Train" );
        request.setAttribute( "topMenuName", "WorkFlow" );

        this.getServletContext().getRequestDispatcher( "/WEB-INF/trainAdvenced.jsp" ).forward( request, response );
        //response.sendRedirect("/train-data-advenced");
        }
    }

}
