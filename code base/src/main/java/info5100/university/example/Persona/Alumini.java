/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info5100.university.example.Persona;

import Feedback.Certifications;
import Feedback.CertificationsDirectory;
import com.github.javafaker.Faker;

/**
 *
 * @author maneesh
 */
public class Alumini {
     Person person;
     int workExp;
     int internships;
     int intersnhipDuration;
     int salary;
     double grade;
     Faker fake;
     CertificationsDirectory directory;
     
     public Alumini(){
         fake = new Faker();
         person = new Person(fake.number().digits(5), fake.name().fullName());
         grade = fake.number().randomDouble(2, 2, 4);
         directory = new CertificationsDirectory();
         this.salary = fake.number().numberBetween(60000, 200000);
     }
     
     public void addCertification(){
        
        Certifications cert = new Certifications(fake.educator().course(),
                 fake.number().randomDigitNotZero(),
                 fake.job().field(),
                 this);
        directory.addCertification(cert);
     }
     
     public CertificationsDirectory getCertificationsDirectory(){
         return directory;
     }
     
     public int getSalary(){
         return salary;
     }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getWorkExp() {
        return workExp;
    }

    public void setWorkExp(int workExp) {
        this.workExp = workExp;
    }

    public int getInternships() {
        return internships;
    }

    public void setInternships(int internships) {
        this.internships = internships;
    }

    public int getIntersnhipDuration() {
        return intersnhipDuration;
    }

    public void setIntersnhipDuration(int intersnhipDuration) {
        this.intersnhipDuration = intersnhipDuration;
    }

    public Faker getFake() {
        return fake;
    }

    public void setFake(Faker fake) {
        this.fake = fake;
    }

    public CertificationsDirectory getDirectory() {
        return directory;
    }

    public void setDirectory(CertificationsDirectory directory) {
        this.directory = directory;
    }
    
    public double getGrade(){
        return grade;
    }
     
     
}
