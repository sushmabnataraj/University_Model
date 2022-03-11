/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Feedback;

import java.util.ArrayList;

/**
 *
 * @author maneesh
 */
public class CertificationsDirectory {
    ArrayList<Certifications> certificationsList;
    
    public CertificationsDirectory(){
        certificationsList = new ArrayList<>();
    }
    
    public void addCertification(Certifications cert){
        this.certificationsList.add(cert);
    }
    
    public ArrayList<Certifications> getCertificationList(){
        return certificationsList;
    }
}
