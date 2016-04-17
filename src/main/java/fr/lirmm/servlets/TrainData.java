/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lirmm.servlets;

import fr.lirmm.db.BaseDeDonnee;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Niels
 */
public class TrainData extends HttpServlet {
    public static final String MODE = "mode";
    public static final String ID = "fileId";
    public static final String NAME = "fileName";
    public static final String CLASSIFICATION = "classification";
    public static final String FOLDS = "folds";
    public static final String TRAIN_DATA = "fileUpload"; 
    public static final String TEST_DATA = "fileUpload2";

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String fileId = request.getParameter(ID);
        String fileName = request.getParameter(NAME);
        String form = request.getParameter(MODE);
        
        if (form == null) {
            System.out.println("FORM null");
            request.setAttribute("fileId", fileId);
            request.setAttribute("fileName", fileName);
            request.setAttribute( "title", "Train" );
            request.setAttribute( "topMenuName", "WorkFlow" );

        this.getServletContext().getRequestDispatcher( "/WEB-INF/trainForm.jsp" ).forward( request, response );
        
        }
        else if (form.equals("advenced")){
            String cross = request.getParameter(CLASSIFICATION);
            String folds = "0";
            if (cross.equals("Cross"))
            {
                folds = request.getParameter(FOLDS);
            }
            HttpSession session = request.getSession();
            String mail = session.getAttribute("mail").toString();
            String path = generatesPath(mail,fileName);
            
            //creatProp(mail, fileName, folds, form, path);
            
            //récupère le fichier 1
            Part p = request.getPart(TRAIN_DATA);
            InputStream is = request.getPart(p.getName()).getInputStream();
            getDataFiles(is, "TRAIN_DATA", path);
            
            if(folds.equals("0"))
            {
                //récupère le fichier 2
                Part p2 = request.getPart(TEST_DATA);
                InputStream is2 = request.getPart(p2.getName()).getInputStream();
                getDataFiles(is2, "TEST_DATA", path);
            }
            request.setAttribute("folds", folds);
            request.setAttribute("fileId", fileId);
            request.setAttribute("classification", cross);
            request.setAttribute("fileName", fileName);
            request.setAttribute( "title", "Train" );
            request.setAttribute( "topMenuName", "WorkFlow" );

            this.getServletContext().getRequestDispatcher( "/WEB-INF/trainAdvenced.jsp" ).forward( request, response );
            //response.sendRedirect("/train-data-advenced");
        }
        
        //si le mode est sur tweet ou sur texte on fait le même traitement, seule la valeur de form change 
        else if (form.equals("tweet") || form.equals("text")){
            
            System.out.println("FORM tweet");
            String cross = request.getParameter(CLASSIFICATION);
            String folds = "0";
            if (cross.equals("Cross"))
            {
                folds = request.getParameter(FOLDS);
            }
            HttpSession session = request.getSession();
            String mail = session.getAttribute("mail").toString();
            String path = generatesPath(mail,fileName);
            
            creatProp(mail, fileName, folds, form, path);
            
            //récupère le fichier 1
            Part p = request.getPart(TRAIN_DATA);
            InputStream is = request.getPart(p.getName()).getInputStream();
            getDataFiles(is, "TRAIN_DATA", path);
            
            if(folds.equals("0"))
            {
                //récupère le fichier 2
                Part p2 = request.getPart(TEST_DATA);
                InputStream is2 = request.getPart(p2.getName()).getInputStream();
                getDataFiles(is2, "TEST_DATA", path);
            }
            
            response.sendRedirect("manage"); // on revien sur la page manage, 
        }
    }
    
    //genère le chemin du répertoire du model 
    protected String generatesPath(String mail, String fileName)
    {
       
        BaseDeDonnee bd = new BaseDeDonnee();
        String id = "";
        try {
            id = bd.getUserId(mail);
        } catch (SQLException ex) {
            Logger.getLogger(TrainData.class.getName()).log(Level.SEVERE, null, ex);
        }
         String path = "./user_models/" + id + "/" + fileName +"/";
         return path;
    }
   
    //recupère les fichier fournis par l'utilisateur pour les stocker dans le repertoire du mopdèle
    protected void getDataFiles(InputStream is, String nomFichier, String path) throws IOException
    {
        
            
            int i = is.available();
            byte[] b = new byte[i];
            is.read(b);
            FileOutputStream os = new FileOutputStream(path + nomFichier + ".txt");
            os.write(b); 
            os.close();
            is.close();
    }
    
    //crée le fichier de propriété dans le répertoire du modèle
    protected void creatProp(String mail, String fileName, String folds, String form, String path)
    {
        Properties prop = new Properties();
            OutputStream output = null;
            
            
        try {
            //System.out.println(normalizeSlang);
            output = new FileOutputStream(path + fileName + ".properties");

            // set the properties value
            prop.setProperty("type", form);
            prop.setProperty("Data.nbFolds", folds); //si = 0 on n'est pas en cross
            prop.setProperty("Ngrams.min", "1");
            prop.setProperty("Ngrams.max", "1");
            prop.setProperty("Preprocessings.lowercase", "on");
            prop.setProperty("Preprocessings.lemmatize", "on");
            prop.setProperty("Preprocessings.removeStopWords", "on");
            prop.setProperty("Preprocessings.normalizeSlang", "off");
            prop.setProperty("Preprocessings.normalizeHyperlinks", "on");
            prop.setProperty("Preprocessings.normalizeEmails", "off");
            prop.setProperty("Preprocessings.replacePseudonyms", "yes");
            prop.setProperty("Lexicons.feelPol", "off");
            prop.setProperty("Lexicons.polarimotsPol", "off");
            prop.setProperty("Lexicons.affectsPol", "off");
            prop.setProperty("Lexicons.dikoPol", "off");
            prop.setProperty("Lexicons.feelEmo", "off");
            prop.setProperty("Lexicons.affectsEmo", "off");
            prop.setProperty("Lexicons.dikoEmo", "off");
            prop.setProperty("SyntacticFeatures.countCapitalizations", "off");
            prop.setProperty("SyntacticFeatures.countElongatedWords", "off");
            prop.setProperty("SyntacticFeatures.countHashtags", "off");
            prop.setProperty("SyntacticFeatures.countNegators", "off");
            prop.setProperty("SyntacticFeatures.presenceSmileys", "off");
            prop.setProperty("SyntacticFeatures.presencePunctuation", "off");
            prop.setProperty("SyntacticFeatures.presencePartOfSpeechTags", "off");
            prop.setProperty("FeatureSelection.percentageAttributes", "100");

            // save properties to model folder
            prop.store(output, null );

        } catch (IOException io) {
            System.out.println(io);
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    System.out.println(e);
		}
            }
        }   
    }
}
