/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lirmm.beans;

/**
 *
 * @author azaz1
 */
public class Polarite {
    public String fmeasure;
    public String precision;
    public String recall;
    
    public Polarite(){}
    
    public Polarite(String f,  String p,  String r){
        fmeasure = f;
        precision = p;
        recall = r;
    }

    public String getFmeasure() {
        return fmeasure;
    }

    public void setFmeasure(String fmeasure) {
        this.fmeasure = fmeasure;
    }

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }

    public String getRecall() {
        return recall;
    }

    public void setRecall(String recall) {
        this.recall = recall;
    }
    
}
