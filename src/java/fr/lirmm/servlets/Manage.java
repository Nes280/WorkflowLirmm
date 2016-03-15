/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lirmm.servlets;

import fr.lirmm.db.BaseDeDonnee;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Niels
 */
public class Manage extends HttpServlet {
    public static String FILE_NAME = "file_name";
    public static String INFO = "info";
    public static String LENGTH = "The length of additional information must be less than 100 characters. ";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Object mail = session.getAttribute("mail");
        
        
        BaseDeDonnee bd = new BaseDeDonnee();
        try {
            ArrayList<String> b = new ArrayList<String>();
            b = bd.getFileUser(mail + "");
            request.setAttribute( "test", b );
            System.out.println(b);
        } catch (SQLException ex) {
            Logger.getLogger(Manage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        request.setAttribute( "title", "Manage" );
        request.setAttribute( "topMenuName", "WorkFlow" );

        this.getServletContext().getRequestDispatcher( "/WEB-INF/manage.jsp" ).forward( request, response );
       
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Object mail = session.getAttribute("mail");
        String file_name = request.getParameter(FILE_NAME);
        String info = request.getParameter(INFO);
        
        if (info.length() < 100)
        {
            BaseDeDonnee bd = new BaseDeDonnee();
            try {
                int create = bd.newFileUser(file_name, mail + "", info);

                System.out.println(create);
            } catch (SQLException ex) {
                Logger.getLogger(Manage.class.getName()).log(Level.SEVERE, null, ex);
            }


            request.setAttribute( "title", "Manage" );
            request.setAttribute( "topMenuName", "WorkFlow" );

            this.getServletContext().getRequestDispatcher( "/WEB-INF/manage.jsp" ).forward( request, response );
        }
        else
        {
            request.setAttribute( "info", LENGTH );
            request.setAttribute( "title", "Manage" );
            request.setAttribute( "topMenuName", "WorkFlow" );

            this.getServletContext().getRequestDispatcher( "/WEB-INF/manage.jsp" ).forward( request, response );
        }
    }
    

}
