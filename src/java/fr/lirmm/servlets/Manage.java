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
    public static String FIC_INFO = "ficInfo";
    public static String FIC_NOM = "ficNom";
    public static String F5 = "please, use the submit buton;";
    

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Object mail = session.getAttribute("mail");
        
        BaseDeDonnee bd = new BaseDeDonnee();
        
        request.setAttribute( "tableau", faireListeFichier(bd, (String)mail) );
        request.setAttribute( "title", "Manage" );
        request.setAttribute( "topMenuName", "WorkFlow" );

        this.getServletContext().getRequestDispatcher( "/WEB-INF/manage.jsp" ).forward( request, response );
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Object mail = session.getAttribute("mail");
        Object ficInfo = session.getAttribute(FIC_INFO);
        Object ficNom = session.getAttribute(FIC_NOM);
        String file_name = request.getParameter(FILE_NAME);
        String info = request.getParameter(INFO);
        BaseDeDonnee bd = new BaseDeDonnee();
        
        
        if (!info.equals(ficInfo) && !file_name.equals(ficNom)) {     
        
            if (info.length() < 100 && !file_name.isEmpty() && !info.isEmpty())
            {

                try {
                    
                    int create = bd.newFileUser(file_name, mail + "", info);

                } catch (SQLException ex) {
                    Logger.getLogger(Manage.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                request.setAttribute( "title", "Manage" );
                request.setAttribute( "tableau", faireListeFichier(bd, (String)mail) );
                session.setAttribute(FIC_INFO, info);
                session.setAttribute(FIC_NOM, file_name);
            }
            else
            {
                request.setAttribute( "tableau", faireListeFichier(bd, (String)mail) );
                request.setAttribute( "info", LENGTH );
                request.setAttribute( "title", "Manage" );

            }
        }
        else //l'utilisateur a fait un refresh on ne fait rien
        {
            request.setAttribute( "tableau", faireListeFichier(bd, (String)mail) );
            request.setAttribute( "title", "Manage" );
        }
        
       this.getServletContext().getRequestDispatcher( "/WEB-INF/manage.jsp" ).forward( request, response );
    }
    
    public String faireListeFichier(BaseDeDonnee bd, String mail)
    {
        String tableau = "";
        try {
            ArrayList<String> liste = new ArrayList<String>();
            liste = bd.getFileUser(mail + "");
            
            for (int i = 0; i < liste.size(); i = i + 3) {
                
                String nom = liste.get(i); 
                String information = liste.get(i+1);
                String date_modif = liste.get(i+2);
                
                
                tableau += "<tr class=\"blue-hover\">\n" +"<td>"+nom+"</td>\n" +"<td>"+information+"</td>\n"+"<td>"+date_modif+"</td>\n"+"<td></td>\n"+"<td></td>\n" +"</tr>\n"; 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Manage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tableau;
    }
}
