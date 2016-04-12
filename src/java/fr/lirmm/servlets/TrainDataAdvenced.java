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
public class TrainDataAdvenced extends HttpServlet {
    public static final String FILE_ID = "fileId";
    public static final String FILE_NAME = "fileName";
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileId = request.getParameter(FILE_ID);
        String fileName = request.getParameter(FILE_NAME);
        
        if (fileId != null && fileName != null) {
            
        request.setAttribute("fileId", fileId);
        request.setAttribute("fileName", fileName);
        request.setAttribute( "title", "Advenced parameters" );
        request.setAttribute( "topMenuName", "WorkFlow" );

        this.getServletContext().getRequestDispatcher( "/WEB-INF/trainAdvenced.jsp" ).forward( request, response );
        }
        else{ 
            // traiter le cas de cette erreur
        }
    }

}
