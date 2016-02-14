/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lirmm.db;

import fr.lirmm.beans.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Niels
 */
public class DataBase {
    static private Connection connexion;
    static private String CLASS = "org.postgresql";
    static private String DRIVER = "\"jdbc:postgresql://localhost/workflow_db\", \"workflow_user\", \"admin\"";
    static Statement statement = null;
    static ResultSet resultat = null;
    
    static protected void loadDatabase(Connection connexion) {
        // Chargement du driver
        try {
            Class.forName(CLASS);
        } catch (ClassNotFoundException e) {
            //non traité pour le moment
        }

        try {
            connexion = DriverManager.getConnection(DRIVER);
        } catch (SQLException e) {
            //non traité pour le moment
        }
    }
    
    static public boolean isInDataBase(String mail) throws SQLException //true si existe déjà false si non
   {
        String id = "";
        try {
            Class.forName(CLASS);
        } catch (ClassNotFoundException e) {
            //non traité pour le moment
        }

        try {
            connexion = DriverManager.getConnection(DRIVER);
        } catch (SQLException e) {
            //non traité pour le moment
        }
        try {
            // Exécution de la requête
            resultat = statement.executeQuery("SELECT \"Id\" FROM lirmm.\"User\" WHERE \"Mail\" = '"+ mail +"'");
 
            // Récupération des données
            while (resultat.next()) {
                id = resultat.getString("Id");
            }
        } catch (SQLException e) {
            //non traité pour le moment
        } finally {
            // Fermeture de la connexion
            try {
                if (resultat != null)
                    resultat.close();
                if (statement != null)
                    statement.close();
                if (connexion != null)
                    connexion.close();
            } catch (SQLException ignore) {
                //non traité pour le moment
            }
        }
        if (id == "") {
           return false;
        }else return true;   
   }
    
    static public void ajouterUtilisateur(User utilisateur) {
        
        DataBase.loadDatabase(connexion);
        try {
            PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO noms(Fname, Lname, Mail, Password, Mod) VALUES(?, ?, ?, ?, ?);");
           
            preparedStatement.setString(1, utilisateur.getFname());
            preparedStatement.setString(2, utilisateur.getLname());
            preparedStatement.setString(3, utilisateur.getMail());
            preparedStatement.setString(4, utilisateur.getPassword());
            preparedStatement.setBoolean(5, utilisateur.isMod());
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
