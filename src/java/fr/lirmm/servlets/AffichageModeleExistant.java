/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lirmm.servlets;

import analysedesentiments.AnalyseDeSentiments;
import fr.lirmm.beans.Polarite;
import fr.lirmm.beans.Root;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
 
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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
    public static final String ATT_CLASSE= "classe";
    public static final String ATT_DESCRIPTION= "description";
    
    public static final String VUE = "/WEB-INF/affichageModeleExistant.jsp";
     
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        
        //variable pour le formulaire de saisie de tweet
        String tweet = request.getParameter(CHAMP_TWEET);
        String choix = request.getParameter(CHAMP_CHOIX);
        System.out.println("choix "+ choix);

        //Preparation des résultats
        Map<String, String> listeTweet = new HashMap<String, String>();
        String message = ""; 
        //boolean erreur = true; 
        int erreur = 0; 
        String resultat = "";
        
        //Initialiser les modèles
        String modele1 = "DEFT15T1STW.model";
        String modele2 = "DEFT15T1IG.model";
        String modele3 = "DEFT15T1SMO.model";
        
        //on utilise le formulaire de saisie de texte
        if(choix.equals("saisieTexte"))
        {
            System.out.println("tweet " + tweet);
            //il n'y a pas de tweet à traiter
            if(tweet == null || tweet.isEmpty() ){
                message = "No tweet";
                //erreur = true;
                erreur = 0;
            }
            //on a un tweet à traiter
            else if (tweet != null){
                message = "Analysis tweet";
                //erreur = false;
                erreur = 1;
                
                AnalyseDeSentiments a = new AnalyseDeSentiments();

                try {
                    resultat = a.start(tweet, modele1, modele2, modele3);
                    listeTweet.put(tweet, resultat);
                } catch (Exception ex) {
                    Logger.getLogger(AffichageAPITweet.class.getName()).log(Level.SEVERE, null, ex);
                }
 
            }
        }
        //on utilise le formulaire d'upload de fichier
        else if(choix.equals("uploadFile")){
            Part p = request.getPart(CHAMP_FILE);
            InputStream is = request.getPart(p.getName()).getInputStream();
            int i = is.available();
            byte[] b = new byte[i];
            is.read(b);
            String fileName = getFileName(p);
            
            //On a pas mis de fichiers
            if(fileName.equals("")){
                message = "No tweet";
                //erreur = true;
                erreur = 0;
            }
            //on a uploadé un fichier
            else{
                message = "Analysis tweet";
                //erreur = false;
                erreur = 2;
                FileOutputStream os = new FileOutputStream("./fichiers/" + fileName);
                os.write(b); 
                
                //Lecture de fichier uploader
                String chaine = "";
                try{
                    InputStream ips = new FileInputStream("./fichiers/" + fileName); 
                    InputStreamReader ipsr = new InputStreamReader(ips);
                    BufferedReader br= new BufferedReader(ipsr);
                    String ligne;
                    while ((ligne=br.readLine())!=null){
                            System.out.println(ligne);
                            chaine+=ligne+"\n";
                    }
                    br.close(); 
                    ipsr.close();
                    
                    String delim = "\n\n";
                    String[] tokens = chaine.split(delim);
                    AnalyseDeSentiments a = new AnalyseDeSentiments();

                    for(int j = 0; j < tokens.length; j++){
                        try {
                            resultat = a.start(tokens[j], modele1, modele2, modele3);
                            listeTweet.put(tokens[j], resultat);
                        } catch (Exception ex) {
                            Logger.getLogger(AffichageAPITweet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }  
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
                os.close();
            }
            is.close();
            
            File f = new File("./fichiers/" + fileName);
            f.delete();
            
            //Création du json
            FileOutputStream fos = new FileOutputStream(new File("./fichiers/fichierjson.json"));
            
            JsonGeneratorFactory factory = Json.createGeneratorFactory(null);
            JsonGenerator generator = factory.createGenerator(fos);
            generator.writeStartArray();
            
            for(Entry<String, String> entry : listeTweet.entrySet())
            {
                generator.writeStartObject().write("phrase", entry.getKey()).
                    write("classe", entry.getValue()).writeEnd();            
            }            
            generator.writeEnd().close(); 
        }
          
        //Valeur pour Root
        Root root = new Root();
        root.setSample(valeurXml("/modele/root/sample"));
        root.setMicrofmeasure(valeurXml("/modele/root/microfmeasure"));
        root.setMacrofmeasure(valeurXml("/modele/root/macrofmeasure"));
        root.setMicroprecision(valeurXml("/modele/root/microprecision"));
        root.setMacroprecision(valeurXml("/modele/root/macroprecision"));
        root.setMicrorecall(valeurXml("/modele/root/microrecall"));
        root.setMacrorecall(valeurXml("/modele/root/macrorecall"));
        
        //Recuperer les id des éléments de type classe
        String[] classe = valeurClasseXml("/modele/classe/@id");
        ArrayList<Polarite> listeClasse = new ArrayList<Polarite>(); 
        
        //Recuperer les valeurs samples, f-measure, precision, recall
        for(int i = 0; i < classe.length; i++)
        {
            System.out.println("nom classe :" + classe[i]);
            Polarite p = new Polarite();
            p.setClasse(classe[i]);
            p.setSample(valeurXml("/modele/classe[@id='"+ classe[i] +"']/sample"));
            p.setFmeasure(valeurXml("/modele/classe[@id='"+ classe[i] +"']/fmeasure"));
            p.setPrecision(valeurXml("/modele/classe[@id='"+ classe[i] +"']/precision"));
            p.setRecall(valeurXml("/modele/classe[@id='"+ classe[i] +"']/recall"));
            listeClasse.add(p);
        }
        
        
        //Description 
        String description = valeurXml("/modele/description");
        
        request.setAttribute( "title", "Tweet" );
        request.setAttribute(ATT_TWEET, listeTweet);
        request.setAttribute(ATT_MESSAGE, message);
        request.setAttribute(ATT_ERREUR, erreur);
        request.setAttribute(ATT_ROOT, root);
        request.setAttribute(ATT_CLASSE, listeClasse);
        request.setAttribute(ATT_DESCRIPTION, description);
        
        this.getServletContext().getRequestDispatcher(VUE).forward( request, response );
    }
    
    
    //Fonction qui renvoie le nom du fichier uploader
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
            File file = new File("XML/tweetPolarity.xml");
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

    
    //Fonction qui recupere les classes des modèles
    public String[] valeurClasseXml(String expression){
        String[] valeur = null;

        try{
            File file = new File("XML/tweetPolarity.xml");
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder =  builderFactory.newDocumentBuilder();
            Document xmlDocument = builder.parse(file);
            /*Element root = xmlDocument.getDocumentElement();
            XPath xPath =  XPathFactory.newInstance().newXPath();*/
            //NodeList listeNode = (NodeList) xPath.evaluate(expression,XPathConstants.NODESET);
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
            XPathExpression expr = xPath.compile(expression);
            NodeList listNode = (NodeList) expr.evaluate(xmlDocument, XPathConstants.NODESET);
            valeur = new String[listNode.getLength()];
            
            for (int i = 0; i< listNode.getLength(); i++){
                //System.out.println("taille de id " + listNode.getLength() );
                Node classe = listNode.item(i).getChildNodes().item(0);
                valeur[i] = classe.getNodeValue();
                //System.out.println(valeur[i]);
            }
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
