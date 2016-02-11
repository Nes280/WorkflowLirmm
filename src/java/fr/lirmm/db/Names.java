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
/**
 *
 * @author niels
 */
public class Names {
    private Connection connexion;
    
    public User recupererUtilisateurs(String mail, String password) {
        Statement statement = null;
        ResultSet resultat = null;

        loadDatabase();
        User utilisateur = new User();
        
        try {
            statement = connexion.createStatement();
            
            // Creation de la requete
            String base = "SELECT \"Fname\", \"Lname\" FROM lirmm.\"User\"";              //bas de la requete 
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
    
    private void loadDatabase() {
        // Chargement du driver
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
    }
   /* 
    public void ajouterUtilisateur(User utilisateur) {
        loadDatabase();
        
        try {
            PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO noms(nom, prenom) VALUES(?, ?);");
            preparedStatement.setString(1, utilisateur.getNom());
            preparedStatement.setString(2, utilisateur.getPrenom());
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}
