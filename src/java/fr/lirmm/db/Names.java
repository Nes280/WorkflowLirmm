/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lirmm.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import fr.lirmm.beans.User;
import static fr.lirmm.db.DataBase.resultat;
/**
 *
 * @author niels
 */
public class Names {
    static private Connection connexion;
    
    public User recupererUtilisateurs(String mail, String password) {
        Statement statement = null;
        ResultSet resultat = null;

        try {
            Class.forName("org.postgresql");
        } catch (ClassNotFoundException e) {
            //non traité pour le moment
        }

        try {
            connexion = DriverManager.getConnection("jdbc:postgresql://localhost/workflow_db", "workflow_user", "admin");
        } catch (SQLException e) {
            //non traité pour le moment
        }
        
        User utilisateur = new User();
        
        try {
            statement = connexion.createStatement();
            
            // Creation de la requete
            String base = "SELECT \"Fname\", \"Lname\" FROM lirmm.\"User\"";              //base de la requete 
            String spec = "WHERE \"Mail\" = '"+mail+"' AND \"Password\" = '"+password+"'";//specification de la requete

            // Exécution de la requête
            resultat = statement.executeQuery(base + spec);
 
            // Récupération des données
            while (resultat.next()) {
                String nom = resultat.getString("Fname");
                String prenom = resultat.getString("Lname");
            
                utilisateur.setFname(nom);
                utilisateur.setLname(prenom);
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
        
        return utilisateur;
    }
    static public void ajouterUtilisateur(User utilisateur) {
        
        Statement statement = null;
        ResultSet resultat = null;

        try {
            Class.forName("org.postgresql");
        } catch (ClassNotFoundException e) {
            //non traité pour le moment
        }

        try {
            connexion = DriverManager.getConnection("jdbc:postgresql://localhost/workflow_db", "workflow_user", "admin");
        } catch (SQLException e) {
            //non traité pour le moment
        }
        
        try {
            PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO lirmm.\"User\"(\"Fname\", \"Lname\", \"Mail\", \"Password\", \"Mod\") VALUES(?, ?, ?, ?, ?);");
           
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
    static public boolean isInDataBase(String mail) throws SQLException //true si existe déjà false si non
   {
        Statement statement = null;
        ResultSet resultat = null;
        String id = "";
        try {
            Class.forName("org.postgresql");
        } catch (ClassNotFoundException e) {
            //non traité pour le moment
        }

        try {
            connexion = DriverManager.getConnection("jdbc:postgresql://localhost/workflow_db", "workflow_user", "admin");
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
}
