/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lirmm.servlets;

import analysedesentiments.LearnModel;
import fr.lirmm.db.BaseDeDonnee;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
public class TrainRun extends HttpServlet {
     public static final String NAME = "fileName";
    //A CODER
     
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Learn ::");
        
        HttpSession session = request.getSession();
        String mail = session.getAttribute("mail").toString();
        
        String fileName = request.getParameter(NAME);
            
        String propPath = generatesPath(mail,fileName);
        
         try {
             System.out.println("Learn commancé");
             LearnModel.learn(propPath);
             System.out.println("Learn terminé");
         } catch (Exception ex) {
             Logger.getLogger(TrainRun.class.getName()).log(Level.SEVERE, null, ex);
         }
        //request.setAttribute("fileName", fileName);
        request.setAttribute( "title", "Result" );
        request.setAttribute( "topMenuName", "WorkFlow" );

        this.getServletContext().getRequestDispatcher( "/WEB-INF/affichageModeleUtilisateur.jsp" ).forward( request, response );
    }
    protected String generatesPath(String mail, String fileName)
    {
       
        BaseDeDonnee bd = new BaseDeDonnee();
        String id = "";
        try {
            id = bd.getUserId(mail);
        } catch (SQLException ex) {
            Logger.getLogger(TrainData.class.getName()).log(Level.SEVERE, null, ex);
        }
         String path = "./user_models/" + id + "/" + fileName +"/"+fileName+".properties";
         return path;
    }

}
