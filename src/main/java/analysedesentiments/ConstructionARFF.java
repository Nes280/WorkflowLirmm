package analysedesentiments;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import org.annolab.tt4j.TreeTaggerException;
import weka.core.Attribute;
import weka.core.Instances;


/**
 *
 * @author amin.abdaoui
 */
public class ConstructionARFF {
    
    private CalculAttributs ca;
    
    public ConstructionARFF() throws IOException{
        ca = new CalculAttributs();
    }
    
    public Instances ConstructionInstances(Instances data) throws Exception{
        String tweet;
        Instances newData = new Instances(data);
        // Entete
        newData.insertAttributeAt(new Attribute("_allCaps"), newData.numAttributes());
        // Attributs des Lexiques
        newData.insertAttributeAt(new Attribute("_countPosFEEL"), newData.numAttributes());
        newData.insertAttributeAt(new Attribute("_countNegFEEL"), newData.numAttributes());
        newData.insertAttributeAt(new Attribute("_countPosAffects"), newData.numAttributes());
        newData.insertAttributeAt(new Attribute("_countNegAffects"), newData.numAttributes());
        newData.insertAttributeAt(new Attribute("_countNeuAffects"), newData.numAttributes());
        newData.insertAttributeAt(new Attribute("_countPosDiko"), newData.numAttributes());
        newData.insertAttributeAt(new Attribute("_countNegDiko"), newData.numAttributes());
        newData.insertAttributeAt(new Attribute("_countNeuDiko"), newData.numAttributes());
        // Data
        for (int i=0; i<newData.numInstances(); i++){
            tweet = newData.instance(i).stringValue(data.attribute("_tweet"));
            // Appliquer les prétraitements
            tweet = Pretraitements.ReplaceLink(tweet);
            tweet = Pretraitements.ReplaceUserTag(tweet);
            // Attributs
            newData.instance(i).setValue(newData.attribute("_allCaps"), ca.AllCaps(tweet));
            // Lémmatisation
            tweet = tweet.toLowerCase();
            tweet = ca.Lemmatiser(tweet);
            newData.instance(i).setValue(newData.attribute("_tweet"), tweet);
            // Lexiques
            newData.instance(i).setValue(newData.attribute("_countPosFEEL"), ca.ComputePosFEEL(tweet));
            newData.instance(i).setValue(newData.attribute("_countNegFEEL"), ca.ComputeNegFEEL(tweet));
            newData.instance(i).setValue(newData.attribute("_countPosAffects"), ca.ComputePosAffects(tweet));
            newData.instance(i).setValue(newData.attribute("_countNegAffects"), ca.ComputeNegAffects(tweet));
            newData.instance(i).setValue(newData.attribute("_countNeuAffects"), ca.ComputeNeuAffects(tweet));
            newData.instance(i).setValue(newData.attribute("_countPosDiko"), ca.ComputePosDiko(tweet));
            newData.instance(i).setValue(newData.attribute("_countNegDiko"), ca.ComputeNegDiko(tweet));
            newData.instance(i).setValue(newData.attribute("_countNeuDiko"), ca.ComputeNeuDiko(tweet));
        }
        return newData;
    }
    
}