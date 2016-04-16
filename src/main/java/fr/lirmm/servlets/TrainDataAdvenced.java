/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lirmm.servlets;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Niels
 */
public class TrainDataAdvenced extends HttpServlet {
    public static final String FILE_ID = "fileId";
    public static final String FILE_NAME = "fileName";
    public static final String NB_FOLDS = "Data.nbFolds";
    public static final String N_MIN = "Ngrams.min";
    public static final String N_MAX = "Ngrams.max";
    public static final String P_LOWERCASE = "lowercase";
    public static final String P_LEMMATIZE = "lemmatize";
    public static final String P_REMOVESTOPWORDS = "removeStopWords";
    public static final String P_NORMALIZESLANG = "normalizeSlang";
    public static final String P_NORMALIZEHYPERLINKS = "normalizeHyperlinks";
    public static final String P_NORMALIZEEMAILS = "normalizeEmails";
    public static final String P_REPLACEPSEUDONYMS = "replacePseudonyms";
    public static final String L_FEELPOL = "feelPol";
    public static final String L_POLARIMOTSPOL = "polarimotsPol";
    public static final String L_AFFECTSPOL = "affectsPol";
    public static final String L_DIKOKOL = "dikoPol";
    public static final String L_FEELEMO = "feelEmo";
    public static final String L_AFFECTSEMO = "affectsEmo";
    public static final String L_DIKEMO = "dikoEmo";
    public static final String SF_COUNTCAPITALIZATIONS = "countCapitalizations";
    public static final String SF_COUNTELONGATEWORDS = "countElongatedWords";
    public static final String SF_COUNTHASTAGS = "countHashtags";
    public static final String SF_COUNTNAGATORS = "countNegators";
    public static final String SF_PRESENCESMILEYS = "presenceSmileys";
    public static final String SF_PRESENCEPUNCTUATION = "presencePunctuation";
    public static final String SF_PRESENCEPARTOFSPEECHTAGS = "presencePartOfSpeechTags";
    public static final String FS_PERCENTTAGEATTRIBUTES = "percentageAttributes";
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileId = request.getParameter(FILE_ID);
        String fileName = request.getParameter(FILE_NAME);
        String config = request.getParameter("config");
        
        System.out.println(fileId);
        System.out.println(fileName);
        System.out.println(config);
        
        if (fileId != null && fileName != null && config == null) {
            
        request.setAttribute("fileId", fileId);
        request.setAttribute("fileName", fileName);
        request.setAttribute( "title", "Advenced parameters" );
        request.setAttribute( "topMenuName", "WorkFlow" );

        this.getServletContext().getRequestDispatcher( "/WEB-INF/trainAdvenced.jsp" ).forward( request, response );
        }
        else if(config != null && fileId != null && fileName != null){
            
            Properties prop = new Properties();
            OutputStream output = null;
            
            String nbFolds = request.getParameter(NB_FOLDS);
            String min = request.getParameter(N_MIN);
            String max = request.getParameter(N_MAX);
            String lowercase = request.getParameter(P_LOWERCASE);
            String lemmatize = request.getParameter(P_LEMMATIZE);
            String removeStopWords = request.getParameter(P_REMOVESTOPWORDS);
            String normalizeSlang = request.getParameter(P_NORMALIZESLANG);
            String normalizeHyperlinks = request.getParameter(P_NORMALIZEHYPERLINKS);
            String normalizeEmails = request.getParameter(P_NORMALIZEEMAILS);
            String replacePseudonyms = request.getParameter(P_REPLACEPSEUDONYMS);
            String feelPol = request.getParameter(L_FEELPOL);
            String polarimotsPol = request.getParameter(L_POLARIMOTSPOL);
            String affectsPol = request.getParameter(L_AFFECTSPOL);
            String dikoPol = request.getParameter(L_DIKOKOL);
            String feelEmo = request.getParameter(L_FEELEMO);
            String affectsEmo = request.getParameter(L_AFFECTSEMO);
            String dikoEmo = request.getParameter(L_DIKEMO);
            String countCapitalizations = request.getParameter(SF_COUNTCAPITALIZATIONS);
            String countElongatedWords = request.getParameter(SF_COUNTELONGATEWORDS);
            String countHashtags = request.getParameter(SF_COUNTHASTAGS);
            String countNegators = request.getParameter(SF_COUNTNAGATORS);
            String presenceSmileys = request.getParameter(SF_PRESENCESMILEYS);
            String presencePunctuation = request.getParameter(SF_PRESENCEPUNCTUATION);
            String presencePartOfSpeechTags = request.getParameter(SF_PRESENCEPARTOFSPEECHTAGS);
            String percentageAttributes = request.getParameter(FS_PERCENTTAGEATTRIBUTES);
            
            if(lowercase == null) lowercase = "off";
            if(lemmatize == null) lemmatize = "off";
            if(removeStopWords == null) removeStopWords = "off";
            if(normalizeSlang == null) normalizeSlang = "off";
            if(normalizeHyperlinks == null) normalizeHyperlinks = "off";
            if(normalizeEmails == null) normalizeEmails = "off";
            if(replacePseudonyms == null) replacePseudonyms = "off";
            if(feelPol == null) feelPol = "off";
            if(polarimotsPol == null) polarimotsPol = "off";
            if(affectsPol == null) affectsPol = "off";
            if(dikoPol== null) dikoPol = "off";
            if(feelEmo== null) feelEmo = "off";
            if(affectsEmo== null) affectsEmo = "off";
            if(dikoEmo == null) dikoEmo = "off";
            if(countCapitalizations == null) countCapitalizations = "off";
            if(countElongatedWords == null) countElongatedWords = "off";
            if(countHashtags == null) countHashtags = "off";
            if(countNegators == null) countNegators = "off";
            if(presenceSmileys == null) presenceSmileys = "off";
            if(presencePunctuation == null) presencePunctuation = "off";
            if(presencePartOfSpeechTags == null) presencePartOfSpeechTags = "off";
            if(percentageAttributes == null) percentageAttributes= "off";
            

            try {
                //System.out.println(normalizeSlang);
		output = new FileOutputStream("config.properties");

		// set the properties value
		prop.setProperty("Data.nbFolds", nbFolds);
                prop.setProperty("Ngrams.min", min);
                prop.setProperty("Ngrams.max", max);
                prop.setProperty("Preprocessings.lowercase", lowercase);
                prop.setProperty("Preprocessings.lemmatize", lemmatize);
                prop.setProperty("Preprocessings.removeStopWords", removeStopWords);
                prop.setProperty("Preprocessings.normalizeSlang", normalizeSlang);
                prop.setProperty("Preprocessings.normalizeHyperlinks", normalizeHyperlinks);
                prop.setProperty("Preprocessings.normalizeEmails", normalizeEmails);
                prop.setProperty("Preprocessings.replacePseudonyms", replacePseudonyms);
                prop.setProperty("Lexicons.feelPol", feelPol);
                prop.setProperty("Lexicons.polarimotsPol", polarimotsPol);
                prop.setProperty("Lexicons.affectsPol", affectsPol);
                prop.setProperty("Lexicons.dikoPol", dikoPol);
                prop.setProperty("Lexicons.feelEmo", feelEmo);
                prop.setProperty("Lexicons.affectsEmo", affectsEmo);
                prop.setProperty("Lexicons.dikoEmo", dikoEmo);
                prop.setProperty("SyntacticFeatures.countCapitalizations", countCapitalizations);
                prop.setProperty("SyntacticFeatures.countElongatedWords", countElongatedWords);
                prop.setProperty("SyntacticFeatures.countHashtags", countHashtags);
                prop.setProperty("SyntacticFeatures.countNegators", countNegators);
                prop.setProperty("SyntacticFeatures.presenceSmileys", presenceSmileys);
                prop.setProperty("SyntacticFeatures.presencePunctuation", presencePunctuation);
                prop.setProperty("SyntacticFeatures.presencePartOfSpeechTags", presencePartOfSpeechTags);
                prop.setProperty("FeatureSelection.percentageAttributes", percentageAttributes);

		// save properties to project root folder
		prop.store(output, null);

            } catch (IOException io) {
		io.printStackTrace();
            } finally {
		if (output != null) {
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
            //this.getServletContext().getRequestDispatcher( "/WEB-INF/manage.jsp" ).forward( request, response );
            response.sendRedirect("manage");
        }
        else{ 
            // traiter le cas de cette erreur
        }
    }

}
