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
    boolean alreadyConnect = false;
    
    private void connecting(){
        if (!alreadyConnect) {
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
            alreadyConnect = true;
        }
    }
    
    public User recupererUtilisateurs(String mail, String password) {
        Statement statement = null;
        ResultSet resultat = null;
        
        User utilisateur = new User();
        connecting();
        try {
            statement = connexion.createStatement();
            Md5 cryptPw = new Md5(password);
            // Creation de la requete
            String base = "SELECT \"Fname\", \"Lname\", \"Mail\" FROM lirmm.\"User\"";              //base de la requete 
            String spec = "WHERE \"Mail\" = '"+mail+"' AND \"Password\" = '"+cryptPw.getCode()+"'";//specification de la requete

            // Exécution de la requête
            resultat = statement.executeQuery(base + spec);
 
            // Récupération des données
            while (resultat.next()) {
                String nom = resultat.getString("Fname");
                String prenom = resultat.getString("Lname");
                String email = resultat.getString("Mail");
            
                utilisateur.setFname(nom);
                utilisateur.setLname(prenom);
                utilisateur.setMail(email);
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
    public void ajouterUtilisateur(User utilisateur) {
        

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
        Md5 cryptPw = new Md5(utilisateur.getPassword());   
        try {
            PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO lirmm.\"User\"(\"Fname\", \"Lname\", \"Mail\", \"Password\", \"Mod\") VALUES(?, ?, ?, ?, ?);");
           
            preparedStatement.setString(1, utilisateur.getFname());
            preparedStatement.setString(2, utilisateur.getLname());
            preparedStatement.setString(3, utilisateur.getMail());
            preparedStatement.setString(4, cryptPw.getCode());
            preparedStatement.setBoolean(5, utilisateur.isMod());
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean isInDataBase(String mail) throws SQLException //true si existe déjà false si non
    {   
        Statement statement = null;
        ResultSet resultat = null;
        String Mail = "";
        
        connecting();
        statement = connexion.createStatement();
        
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
            resultat = statement.executeQuery("SELECT \"Mail\" FROM lirmm.\"User\" WHERE \"Mail\" = '"+ mail +"'");
 
            // Récupération des données
            while (resultat.next()) {
                Mail = resultat.getString("Mail");
            }
        } catch (NullPointerException n) {
            System.out.println(n);
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
        return Mail.equals(mail);   
   }
}
