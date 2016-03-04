/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lirmm.servlets;

import fr.lirmm.beans.Polarite;
import fr.lirmm.beans.Root;
import static fr.lirmm.servlets.AffichageAPITweet.ATT_DESCRIPTION;
import static fr.lirmm.servlets.AffichageAPITweet.ATT_NEGATIVE;
import static fr.lirmm.servlets.AffichageAPITweet.ATT_NEUTRE;
import static fr.lirmm.servlets.AffichageAPITweet.ATT_POSITIVE;
import static fr.lirmm.servlets.AffichageAPITweet.ATT_ROOT;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
@WebServlet(name = "AffichageModeleExistant", urlPatterns = {"/affichageModeleExistant"})
public class AffichageModeleExistant extends HttpServlet {

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
    public static final String ATT_DESCRIPTION= "description";
    public static final String ATT_DOWNLOAD = "alternative";
    
    public static final String VUE = "/WEB-INF/affichageModeleExistant.jsp";
     
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        
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
        
        //Description 
        String description = valeurXml("/tweet/description");
        
        
        String breadcrumbs = "<li><a href=\"/index\">Home</a></li>";
        request.setAttribute( "title", "Tweet" );
        request.setAttribute( "topMenuName", "WorkFlow" );
        request.setAttribute( "breadcrumbs", breadcrumbs );
        request.setAttribute(ATT_ROOT, root);
        request.setAttribute(ATT_POSITIVE, positive);
        request.setAttribute(ATT_NEUTRE, neutre);
        request.setAttribute(ATT_NEGATIVE, negative);
        request.setAttribute(ATT_DESCRIPTION, description);
        
        this.getServletContext().getRequestDispatcher(VUE).forward( request, response );
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
