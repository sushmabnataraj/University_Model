/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info5100.university.example.Persona;

import java.util.ArrayList;

/**
 *
 * @author maneesh
 */
public class AluminiDirectory {
    ArrayList<Alumini> aluminiList;
    
    public AluminiDirectory(){
        aluminiList = new ArrayList<>();
    }

    public ArrayList<Alumini> getAluminiList() {
        return aluminiList;
    }

    public void setAluminiList(ArrayList<Alumini> aluminiList) {
        this.aluminiList = aluminiList;
    }
    
    public void addAlumini(Alumini alum){
        aluminiList.add(alum);
    }
}
