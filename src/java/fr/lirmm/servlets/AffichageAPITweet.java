/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lirmm.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import analysedesentiments.AnalyseDeSentiments;
import fr.lirmm.beans.Polarite;
import fr.lirmm.beans.Root;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.Part;
 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
 
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author Elsa Martel
 */
public class AffichageAPITweet extends HttpServlet{
    
    public static final String CHAMP_TWEET = "tweetAAnalyser";
    public static final String CHAMP_FILE = "fileUpload";
    public static final String CHAMP_CHOIX = "choix";
    
    public static final String CHEMIN = "chemin";
    public static final int TAILLE_TAMPON = 10240;
    
    public static final String ATT_TWEET = "tweet";
    public static final String ATT_MESSAGE = "message";
    public static final String ATT_ERREUR = "erreur";
    public static final String ATT_ROOT = "root";
    public static final String ATT_POSITIVE = "positive";
    public static final String ATT_NEUTRE = "neutre";
    public static final String ATT_NEGATIVE = "negative";

    
    public static final String VUE = "/WEB-INF/affichageAPITweet.jsp";
    
    public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //variable pour le formulaire de saisie de tweet
        String tweet = request.getParameter(CHAMP_TWEET);
        String nom = request.getParameter(CHAMP_FILE);
        String choix = request.getParameter(CHAMP_CHOIX);
        //System.out.println("nom " + nom);

        //Preparation des résultats
        Map<String, String> listeTweet = new HashMap<String, String>();
        String message = ""; 
        boolean erreur = true; 
        String resultat = "";
        
        //on utilise le formulaire de saisie de texte
        if(choix.equals("saisieTexte"))
        {
            //il n'y a pas de tweet à traiter
            if(tweet == null ){
                message = "No tweet";
                erreur = true;
            }
            //on a un tweet à traiter
            else if (tweet != null){
                message = "Analyse du tweet";
                erreur = false;
                String delim = "\n";
                String[] tokens = tweet.split(delim);
                AnalyseDeSentiments a = new AnalyseDeSentiments();

                for(int i = 0; i < tokens.length; i++){
                    try {
                        resultat = a.start(tokens[i]);
                        listeTweet.put(tokens[i], resultat);
                    } catch (Exception ex) {
                        Logger.getLogger(AffichageAPITweet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }  
            }
        }
        //on utilise le formulaire d'upload de fichier
        else if(choix.equals("uploadFile")){

            Part p = request.getPart(CHAMP_FILE);
            System.out.println("nom "+ p.getName());
            InputStream is = request.getPart(p.getName()).getInputStream();
            int i = is.available();
            byte[] b = new byte[i];
            is.read(b);
            System.out.println("longueur "+ b.length);
            String fileName = getFileName(p);
            System.out.println("File name "+ fileName);
            FileOutputStream os = new FileOutputStream("./fichiers/" + fileName);
            os.write(b);
            is.close();
        }
        
        //Valeur pour Root
        Root root = new Root();
        root.setMicrofmeasure(valeurXml("/tweet/root/microfmeasure"));
        root.setMacrofmeasure(valeurXml("/tweet/root/macrofmeasure"));
        root.setMicroprecision(valeurXml("/tweet/root/microprecision"));
        root.setMacroprecision(valeurXml("/tweet/root/macroprecision"));
        root.setMicrorecall(valeurXml("/tweet/root/microrecall"));
        root.setMacrorecall(valeurXml("/tweet/root/macrorecall"));
        
        //Valeur pour Positive
        Polarite positive = new Polarite();
        positive.setFmeasure(valeurXml("/tweet/positive/fmeasure"));
        positive.setPrecision(valeurXml("/tweet/positive/precision"));
        positive.setRecall(valeurXml("/tweet/positive/recall"));
        
        //Valeur pour Neutre
        Polarite neutre = new Polarite();
        neutre.setFmeasure(valeurXml("/tweet/neutre/fmeasure"));
        neutre.setPrecision(valeurXml("/tweet/neutre/precision"));
        neutre.setRecall(valeurXml("/tweet/neutre/recall"));
        
        //Valeur pour Negative
        Polarite negative = new Polarite();
        negative.setFmeasure(valeurXml("/tweet/negative/fmeasure"));
        negative.setPrecision(valeurXml("/tweet/negative/precision"));
        negative.setRecall(valeurXml("/tweet/negative/recall"));
        
        String breadcrumbs = "<li><a href=\"/index\">Home</a></li>";
        request.setAttribute( "title", "Tweet" );
        request.setAttribute( "topMenuName", "WorkFlow" );
        request.setAttribute( "breadcrumbs", breadcrumbs );
        request.setAttribute(ATT_TWEET, listeTweet);
        request.setAttribute(ATT_MESSAGE, message);
        request.setAttribute(ATT_ERREUR, erreur);
        request.setAttribute(ATT_ROOT, root);
        request.setAttribute(ATT_POSITIVE, positive);
        request.setAttribute(ATT_NEUTRE, neutre);
        request.setAttribute(ATT_NEGATIVE, negative);

        this.getServletContext().getRequestDispatcher(VUE).forward( request, response );
    }
    
    
    private String getFileName(Part part) { 
        String partHeader = part.getHeader("content-disposition"); 
        System.out.println("Part Header = " + partHeader); 
        for (String cd : part.getHeader("content-disposition").split(";")) { 
          if (cd.trim().startsWith("filename")) { 
            return cd.substring(cd.indexOf('=') + 1).trim() 
                .replace("\"", ""); 
          } 
        } 
        return null;
    }

    
    //Fonction qui va chercher les valeurs dans le fichier xml
    public String valeurXml(String expression){
        String valeur = "";
        try{
            File file = new File("XML/tweet.xml");
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder =  builderFactory.newDocumentBuilder();
            Document xmlDocument = builder.parse(file);
            Element root = xmlDocument.getDocumentElement();
            XPath xPath =  XPathFactory.newInstance().newXPath();
            valeur = xPath.evaluate(expression,root);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }catch (XPathExpressionException e) {
            e.printStackTrace();
        } 
        return valeur;

    }

} 

//<%@page import="analysedesentiments.AnalyseDeSentiments"%>
