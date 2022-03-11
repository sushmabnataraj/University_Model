/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Feedback;

import info5100.university.example.Persona.Alumini;

/**
 *
 * @author maneesh
 */
public class Certifications {
    String name;
    int validaity;
    String domain;
    Alumini alum;

    public Certifications(String name, int validaity, String domain, Alumini alum) {
        this.name = name;
        this.validaity = validaity;
        this.domain = domain;
        this.alum = alum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValidaity() {
        return validaity;
    }

    public void setValidaity(int validaity) {
        this.validaity = validaity;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Alumini getAlum() {
        return alum;
    }

    public void setAlum(Alumini alum) {
        this.alum = alum;
    }
    
    

   
    
    
}
