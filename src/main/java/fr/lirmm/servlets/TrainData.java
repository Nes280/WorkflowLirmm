/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lirmm.servlets;



import fr.lirmm.db.BaseDeDonnee;
import java.io.FileOutputStream;
import java.io.IOException;
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
            
            
            request.setAttribute("fileId", fileId);
            request.setAttribute("fileName", fileName);
            request.setAttribute( "title", "Train" );
            request.setAttribute( "topMenuName", "WorkFlow" );

            this.getServletContext().getRequestDispatcher( "/WEB-INF/trainAdvenced.jsp" ).forward( request, response );
            //response.sendRedirect("/train-data-advenced");
        }
        else if (form.equals("tweet")){
            System.out.println("FORM tweet");
            String cross = request.getParameter(CLASSIFICATION);
            String folds = "0";
            if (cross != null)
            {
                folds = request.getParameter(FOLDS);
            }
            HttpSession session = request.getSession();
            Object mail = session.getAttribute("mail");
            
            CreatProp(mail.toString(),fileName,folds,form);
            
            response.sendRedirect("manage");
        }
        else if (form.equals("text")){
            String cross = request.getParameter(CLASSIFICATION);
            String folds = "0";
            if (cross != null)
            {
                folds = request.getParameter(FOLDS);
            }
            HttpSession session = request.getSession();
            Object mail = session.getAttribute("mail");
            
            CreatProp(mail.toString(),fileName,folds,form);
            
            response.sendRedirect("manage");
        }
        
    }
    
    protected void CreatProp(String mail, String fileName, String folds, String form)
    {
        Properties prop = new Properties();
            OutputStream output = null;
            
            BaseDeDonnee bd = new BaseDeDonnee();
            String id = "";
            try {
                id = bd.getUserId(mail);
            } catch (SQLException ex) {
                Logger.getLogger(TrainData.class.getName()).log(Level.SEVERE, null, ex);
            }
        try {
                //System.out.println(normalizeSlang);
		output = new FileOutputStream("./user_models/" + id + "/" + fileName +"/"+ fileName+".properties");

		// set the properties value
                prop.setProperty("type", form);
		prop.setProperty("Data.nbFolds", folds);
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
