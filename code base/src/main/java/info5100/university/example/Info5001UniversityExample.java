/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info5100.university.example;

import Feedback.Certifications;
import Feedback.CourseFeedback;
import com.github.javafaker.Faker;
import info5100.university.example.CourseCatalog.Course;
import info5100.university.example.CourseSchedule.CourseLoad;
import info5100.university.example.CourseSchedule.CourseOffer;
import info5100.university.example.CourseSchedule.CourseSchedule;
import info5100.university.example.CourseSchedule.Seat;
import info5100.university.example.Department.Department;
import info5100.university.example.Persona.Alumini;
import info5100.university.example.Persona.AluminiDirectory;
import info5100.university.example.Persona.Person;
import info5100.university.example.Persona.PersonDirectory;
import info5100.university.example.Persona.StudentDirectory;
import info5100.university.example.Persona.StudentProfile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author kal bugrara
 */
public class Info5001UniversityExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Faker faker = new Faker();
        System.out.println(faker.educator().course());
        System.out.println(faker.university().name());
        System.out.println(faker.gameOfThrones().quote());
        System.out.println(faker.gameOfThrones().quote());
        System.out.println(faker.gameOfThrones().quote());
        System.out.println( faker.number().randomDigitNotZero());
        System.out.println( faker.number().numberBetween(10, 100));
        System.out.println(faker.gameOfThrones().character());
        System.out.println(faker.gameOfThrones().character());
        


        System.out.println(faker.educator().course());
        System.out.println(faker.number().digits(5));
        
        Department department = new Department("Information Systems");
        ArrayList<CourseFeedback> feedbacks = new ArrayList<>();


        Course course = department.newCourse("app eng", "info 5100", 4);
        Course webCourse = department.newCourse("Web design", "info 6210", 4);
        
//        for(int i=0;i<10;i++){
//            //Course randomCourse = department.
//            Course randomCourse = department.newCourse("app eng", "info 5100", 4);
//            randomCourse.
//        }
        

        CourseSchedule courseschedule = department.newCourseSchedule("Fall2020");

        CourseOffer courseoffer = courseschedule.newCourseOffer("info 5100");
        CourseOffer webCourseOffer = courseschedule.newCourseOffer("info 6210");
        webCourseOffer.generatSeats(20);
        courseoffer.generatSeats(20);
        
        PersonDirectory pd = department.getPersonDirectory();
        Person person = pd.newPerson("0112303", faker.name().fullName());
        StudentDirectory sd = department.getStudentDirectory();
        StudentProfile student = sd.newStudentProfile(person);
        CourseLoad courseload = student.newCourseLoad("Fall2020"); 
        
        //Adding new student
        StudentProfile student2 = createNewStudent(department, "12123123");
        CourseLoad courseloadStudnet2 = student2.newCourseLoad("Fall2020");
        
        
        //Adding 3rd Student
        StudentProfile student3 = createNewStudent(department, "14324235");
        CourseLoad courseloadStudnet3 = student3.newCourseLoad("Fall2020");
        
        //Adding random students
        for(int i=0; i<10; i++){
            StudentProfile randomStudent = createNewStudent(department, faker.number().digits(5));
            CourseLoad randomCourseload = randomStudent.newCourseLoad("Fall2020");
            randomCourseload.newSeatAssignment(webCourseOffer);  
            randomCourseload.newSeatAssignment(courseoffer);
            feedbacks.add(randomStudent.generateCourseFeedback(webCourse, 
                    faker.number().numberBetween(10, 100), 
                    faker.number().numberBetween(10, 100),
                    faker.number().numberBetween(10, 100),
                    faker.number().numberBetween(10, 100)));
            feedbacks.add(randomStudent.generateCourseFeedback(course, 
                    faker.number().numberBetween(10, 100), 
                    faker.number().numberBetween(10, 100),
                    faker.number().numberBetween(10, 100),
                    faker.number().numberBetween(10, 100)));
        }
        
        
        //Register multiple courses
        courseloadStudnet2.newSeatAssignment(webCourseOffer);  
        courseloadStudnet2.newSeatAssignment(courseoffer);
        courseload.newSeatAssignment(webCourseOffer);
        courseload.newSeatAssignment(courseoffer); //register student in class
        courseloadStudnet3.newSeatAssignment(webCourseOffer);
        
        //Assign grades
        ArrayList<Seat> aedSeats = department.getCourseSchedule("Fall2020")
                .getCourseOfferByNumber("info 5100").getSeatlist();
        
        //Setting grades
        for(Seat s:aedSeats){
            if(s.isOccupied()){
                Random r = new Random();
                double min = 2.0;
                double max = 4.0;
                s.getSeatassignment().setGrade(min + r.nextDouble()* (max - min));
            }
        }
        
        for(Seat s:department.getCourseSchedule("Fall2020").getCourseOfferByNumber("info 6210").getSeatlist()){
            if(s.isOccupied()){
                Random r = new Random();
                double min = 2.0;
                double max = 4.0;
                s.getSeatassignment().setGrade(min + r.nextDouble()* (max - min));
            }
        }
        System.out.println("----------------------Grades list----------------------");
        System.out.println("======================AED Grades list===================");

        //Displaying student grades in AED
        for(Seat s:aedSeats){
             if(s.isOccupied()){
                 
                 System.out.println(s.getSeatassignment().getGrade());
             }
        }
        System.out.println("======================WEB Grades list===================");
        for(Seat s: department.getCourseSchedule("Fall2020").getCourseOfferByNumber("info 6210").getSeatlist()){
             if(s.isOccupied()){
                 System.out.println(s.getSeatassignment().getGrade());
             }
        }
        
        ArrayList<StudentProfile> departmentStudents = department.getStudentDirectory().getStudentlist();
        System.out.println("--------------------Student directory------------------------");

        for(StudentProfile studentProfile: departmentStudents){
            System.out.println(studentProfile.getPerson().getPersonId());
        }
        System.out.println("----------------------REVENUE----------------------");
        

        int total = department.calculateRevenuesBySemester("Fall2020");
        System.out.println("Total: " + total);

        
        System.out.println("----------------------GPA----------------------");

        System.out.println(student.getTranscript().calculateGPA());
        System.out.println("----------------------Rank CGPA----------------------");

        HashMap<String, Double> hm = new HashMap<>();
        System.out.println(departmentStudents.size());
         for(StudentProfile studentProfile: departmentStudents){
            hm.put(studentProfile.getPerson().getPersonId(), studentProfile.getTranscript().calculateGPA());
        }
         
        //Prining the cgpa
        Map<String, Double> hm1 = sortByValue(hm); 
        // print the sorted hashmap 
        for (Map.Entry<String, Double> en : hm1.entrySet()) { 
            System.out.println("Key = " + en.getKey() +  
                          ", Value = " + en.getValue()); 
        } 
        
        System.out.println("----------------------Popular courses----------------------");
        
        
         Map<String, Integer> hm2 = sortByValueInt(department.mostPopularCourse("Fall2020")); 
        // print the sorted hashmap 
        for (Map.Entry<String, Integer> en : hm2.entrySet()) { 
            System.out.println("Key = " + en.getKey() +  
                          ", Value = " + en.getValue()); 
        } 
        
        System.out.println("----------------------Course Feedback----------------------");
        
        feedbacks.add(student.generateCourseFeedback(webCourse, 10, 20, 30, 40));
        feedbacks.add(student.generateCourseFeedback(course,  40, 50, 70, 40));
        
        
        HashMap<String, Double> courseRatings = new HashMap<>();
        
        //Fetch reviews based on courses
        for(Course c:department.getCourseCatalog().getCourseList()){
            courseRatings.put(c.getName(), getAverageCourseRatings(feedbacks,c));
            
        }
        
        for (Map.Entry<String, Double> en : courseRatings.entrySet()) { 
            System.out.println("Key = " + en.getKey() +  
                          ", Value = " + en.getValue()); 
        } 
        
        System.out.println("----------------------Alumini----------------------");
        
        AluminiDirectory alumuniDirectory = new AluminiDirectory();

        for(int i=0;i<100;i++){
            Alumini newAlum = new Alumini();
            newAlum.addCertification();
            newAlum.addCertification();
            alumuniDirectory.getAluminiList().add(newAlum);
        }
        
        //Certifications done for people with salary of above 90,000
        int count = 0;
        for(Alumini alum: alumuniDirectory.getAluminiList()){
            if(alum.getSalary() > 100000){
                count++;
               for(Certifications cert:alum.getCertificationsDirectory().
                    getCertificationList()){
                    System.out.println(cert.getDomain());
                } 
            }
        }
        System.out.println(count);
    }
    
    public static StudentProfile createNewStudent(Department department, String id){
        Faker fake = new Faker();
        PersonDirectory pd = department.getPersonDirectory();
        Person person = pd.newPerson(id, fake.name().fullName());
        StudentDirectory sd = department.getStudentDirectory();
        StudentProfile student = sd.newStudentProfile(person);
        return student;
    }
    
    
     public static HashMap<String, Double> sortByValue(HashMap<String, Double> hm) 
    { 
        // Create a list from elements of HashMap 
        List<Map.Entry<String, Double> > list = 
               new LinkedList<Map.Entry<String, Double> >(hm.entrySet()); 
  
        // Sort the list 
        Collections.sort(list, new Comparator<Map.Entry<String, Double> >() { 
            public int compare(Map.Entry<String, Double> o1,  
                               Map.Entry<String, Double> o2) 
            { 
                return (o1.getValue()).compareTo(o2.getValue()); 
            } 
        }); 
          
        // put data from sorted list to hashmap  
        HashMap<String, Double> temp = new LinkedHashMap<String, Double>(); 
        for (Map.Entry<String, Double> aa : list) { 
            temp.put(aa.getKey(), aa.getValue()); 
        } 
        return temp; 
    }
     
    // function to sort hashmap by values 
    public static HashMap<String, Integer> sortByValueInt(HashMap<String, Integer> hm) 
    { 
        // Create a list from elements of HashMap 
        List<Map.Entry<String, Integer> > list = 
               new LinkedList<Map.Entry<String, Integer> >(hm.entrySet()); 
  
        // Sort the list 
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() { 
            public int compare(Map.Entry<String, Integer> o1,  
                               Map.Entry<String, Integer> o2) 
            { 
                return (o1.getValue()).compareTo(o2.getValue()); 
            } 
        }); 
          
        // put data from sorted list to hashmap  
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>(); 
        for (Map.Entry<String, Integer> aa : list) { 
            temp.put(aa.getKey(), aa.getValue()); 
        } 
        return temp; 
    } 
    
    public static double getAverageCourseRatings(ArrayList<CourseFeedback> feedbacks, Course course){
        double finalAverage = 0;
        int feedbackCount = 0;
        for(CourseFeedback feedback: feedbacks){
            if(course.getName().equalsIgnoreCase(feedback.getCourse().getName())){
                finalAverage += feedback.getAverageRating();
                feedbackCount++;
            }
        }
        finalAverage = finalAverage/feedbackCount;
        return finalAverage;
    }

}
