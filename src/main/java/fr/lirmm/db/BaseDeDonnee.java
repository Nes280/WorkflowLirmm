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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import fr.lirmm.beans.Model;
import java.text.SimpleDateFormat;
import java.sql.Date;
/**
 *
 * @author niels
 */
public class BaseDeDonnee {
    //private static String JDBC = "jdbc:postgresql://localhost/workflow_db";
    private static String JDBC = "jdbc:postgresql://localhost/sentiment_analysis_webpage_users_db";
    private static String USER = "sentiment_analysis_webpage_user";
    private static String PASSWORD = "admin";
    private static String DRIVER = "org.postgresql";
    
    private Connection connexion;
    boolean alreadyConnect = false;
    //private String JDBC = "jdbc:postgresql://192.168.0.2:5432/workflow_db"; 
    
    private void connecting(){
        if (!alreadyConnect) {
            try {
                Class.forName(DRIVER);
            } catch (ClassNotFoundException e){
                //non traité pour le moment
            }

            try {
                connexion = DriverManager.getConnection(JDBC, USER, PASSWORD);  
            } catch (SQLException e) {
                //non traité pour le moment
            }
            alreadyConnect = true;
        }
    }
    
    public String getUserId(String user_mail) throws SQLException
    {
        Statement statement = null;
        ResultSet resultat = null;
        String user_id = "";
        connecting();
        statement = connexion.createStatement();
        
        resultat = statement.executeQuery("SELECT \"Id\" FROM lirmm.\"User\" WHERE \"Mail\" = '"+ user_mail +"'");
        while (resultat.next()) 
        {
            user_id = resultat.getString("Id");
        }
        return user_id;  
    }
    
    /**
     * PARTIE POUR LES UTILISATEURS
     *  
     */
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
        
        connecting();
        Md5 cryptPw = new Md5(utilisateur.getPassword());   
        try {
            PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO lirmm.\"User\"(\"Fname\", \"Lname\", \"Mail\", \"Password\", \"Mod\", \"IsUpload\", \"IsTraining\") VALUES(?, ?, ?, ?, ?, ?, ?);");
           
            preparedStatement.setString(1, utilisateur.getFname());
            preparedStatement.setString(2, utilisateur.getLname());
            preparedStatement.setString(3, utilisateur.getMail());
            preparedStatement.setString(4, cryptPw.getCode());
            preparedStatement.setBoolean(5, utilisateur.isMod());
            preparedStatement.setBoolean(6, false);
            preparedStatement.setBoolean(7, false);
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void alterUtilisateur(String lMail, String nMail, String Lname, String Fname){
        Statement statement = null;
        ResultSet resultat = null;
        
        connecting();
         
        try {//update article set MonChamp = 'NouvelleValeur' where MonChamp = 'AncienneValeur'
            PreparedStatement preparedStatement = connexion.prepareStatement("UPDATE lirmm.\"User\" set \"Fname\" = '"+Fname+"',\"Lname\" = '"+Lname+"',\"Mail\" = '"+nMail+"' WHERE \"Mail\" = '"+lMail+"';");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void alterPassword(String Mail, String password){
        connecting();
        Md5 cryptPw = new Md5(password);
        try {//update article set MonChamp = 'NouvelleValeur' where MonChamp = 'AncienneValeur'
            PreparedStatement preparedStatement = connexion.prepareStatement("UPDATE lirmm.\"User\" set \"Password\" = '"+cryptPw.getCode()+"'WHERE \"Mail\" = '"+Mail+"';");
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
            // Exécution de la requête
            resultat = statement.executeQuery("SELECT \"Mail\" FROM lirmm.\"User\" WHERE \"Mail\" = '"+ mail +"'");
 
            // Récupération des données
            while (resultat.next()) {
                Mail = resultat.getString("Mail");
            }
        } catch (NullPointerException n) {
            System.out.println(n);
        } 
        return Mail.equals(mail);   
   }
  /**
   * PARTIE POUR LES FICHIER
   * 
   * les fichiers sont utilisé pour les models utilisateur
   */  
    
   /**
    * Methode pour tester l'existance d'un fichier
    * renvoi true si oui false si non
    */ 
    public boolean isInDatabase(String file_name, String user_mail) throws SQLException
    {
        Statement statement = null;
        ResultSet resultat = null;
        String file = "";
        String user_id = "";
        
        connecting();
        statement = connexion.createStatement();
        
        try {
            user_id = getUserId(user_mail);
            
            // Exécution de la requête
            resultat = statement.executeQuery("SELECT \"Id_file\" FROM lirmm.\"File\" WHERE \"Name\" = '"+ file_name +"' AND \"Id_user\" = '"+ user_id+"'");
 
            // Récupération des données
            while (resultat.next()) {
                file = resultat.getString("Id_file");
            }
        } catch (NullPointerException n) {
            System.out.println(n);
        } 
        return !file.isEmpty();
    }
    
    /**
     * Methode pour la recupération de fichier
     * renvoie une List<String> des fichiers de l'utilisateur
     *  
     */
    public ArrayList<String> getFileUser(String user_mail) throws SQLException
    {
        Statement statement = null;
        ResultSet resultat = null;
        ArrayList<String> file = new ArrayList<String>();
        String user_id = "";
        connecting();
        statement = connexion.createStatement();
        
        try {
            user_id = getUserId(user_mail);
            
            // Exécution de la requête
            resultat = statement.executeQuery("SELECT \"Id_file\", \"Name\", \"Info\", \"Date_update\" FROM lirmm.\"File\" WHERE \"Id_user\" = '"+ user_id+"'");
 
            // Récupération des données
            while (resultat.next()) {
                file.add(resultat.getString("Id_file"));
                file.add(resultat.getString("Name"));
                file.add(resultat.getString("Info"));
                file.add(resultat.getString("Date_update"));
            }
        } catch (NullPointerException n) {
            System.out.println(n);
        } 
        return file;
    }
    
    /**
     * Methode pour la creation d'un fichier (model)
     * cette methode ce deroule en 2 temps:
     * - creation du fichier dans le serveur
     * - indexation du fichier sur la BD
     * 
     * renvoi 0 si OK, -1 si le fichier existe déjà
     */
    public int newFileUser( String file_name, String user_mail, String info )throws SQLException, FileNotFoundException, IOException
    {
        
        if (!isInDatabase(file_name + ".model", user_mail))
        {
            String user_id = getUserId(user_mail);
            
            Model file = new Model(file_name, user_mail, info, user_id); 
            file.create();
            
            Statement statement = null;
            connecting();
            statement = connexion.createStatement();

            try {
                PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO lirmm.\"File\"(\"Id_user\", \"Name\", \"Info\", \"Date_create\") VALUES(?, ?, ?, ?);");

                preparedStatement.setInt(1, Integer.parseInt(file.getId()));
                preparedStatement.setString(2, file.getNom());
                preparedStatement.setString(3, file.getInfo());
                preparedStatement.setDate(4, new java.sql.Date(new java.util.Date().getTime()));

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
        }
        else return -1;
        
    }
    
    
    /*
     * Partie Upload de fichier
     */
    public String[] getUserIsUpload(String user_mail) throws SQLException
    {
        String[] resultatFinal = new String[2];
        Statement statement = null;
        ResultSet resultat = null;
        String user_id = "";
        String user_isupload = "";
        connecting();
        statement = connexion.createStatement();
        
        resultat = statement.executeQuery("SELECT \"Id\" FROM lirmm.\"User\" WHERE \"Mail\" = '"+ user_mail +"'");
        while (resultat.next()) 
        {
            user_id = resultat.getString("Id");
        }
        resultatFinal[0] = user_id;
        
        resultat = statement.executeQuery("SELECT \"IsUpload\" FROM lirmm.\"User\" WHERE \"Id\" = '"+ user_id +"'");
        while (resultat.next()) 
        {
            user_isupload = resultat.getString("IsUpload");
        }
        resultatFinal[1] = user_isupload;
        return resultatFinal;  
    }
    
    public void setIsUpload(String Id, String IsUpload){
        connecting();
        try {
            PreparedStatement preparedStatement = connexion.prepareStatement("UPDATE lirmm.\"User\" set \"IsUpload\" = '"+IsUpload+"'WHERE \"Id\" = '"+Id+"';");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
}
