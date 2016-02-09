/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lirmm.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Niels
 */
@WebServlet(name = "Index", urlPatterns = {"/Index"})
public class Index extends HttpServlet {
<<<<<<< Updated upstream
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        String breadcrumbs = "<li><a href=\"/index\">Home</a></li>";
=======

public static ResultSet sauverEnBase() throws ClassNotFoundException, SQLException{
String url = "jdbc:postgresql://localhost/";
String login = "workflow_user";
String pwd = "admin";
Connection cn = null;
Statement st = null;

    Class.forName("com.postgresql.jdbc.Driver");
    cn = DriverManager.getConnection(url, login, pwd);
    st = cn.createStatement();
    String sql = "SELECT * FROM user";
    return st.executeQuery(sql);
    

}    
public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    
    String breadcrumbs = "<li><a href=\"/index\">Home</a></li>";
>>>>>>> Stashed changes
        request.setAttribute( "title", "Main page" );
        request.setAttribute( "topMenuName", "WorkFlow" );
        request.setAttribute( "breadcrumbs", breadcrumbs );
    try {
        request.setAttribute("sql",sauverEnBase());
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
    }
        this.getServletContext().getRequestDispatcher( "/WEB-INF/index.jsp" ).forward( request, response );
    }
}
