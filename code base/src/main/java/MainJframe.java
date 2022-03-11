
import Feedback.Certifications;
import Feedback.CourseFeedback;
import com.github.javafaker.Faker;
import info5100.university.example.CourseCatalog.Course;
import info5100.university.example.CourseSchedule.CourseLoad;
import info5100.university.example.CourseSchedule.CourseOffer;
import info5100.university.example.CourseSchedule.CourseSchedule;
import info5100.university.example.CourseSchedule.Seat;
import info5100.university.example.Department.Department;
import static info5100.university.example.Info5001UniversityExample.createNewStudent;
import static info5100.university.example.Info5001UniversityExample.getAverageCourseRatings;
import static info5100.university.example.Info5001UniversityExample.sortByValue;
import static info5100.university.example.Info5001UniversityExample.sortByValueInt;
import info5100.university.example.Persona.Alumini;
import info5100.university.example.Persona.AluminiDirectory;
import info5100.university.example.Persona.Person;
import info5100.university.example.Persona.PersonDirectory;
import info5100.university.example.Persona.StudentProfile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author maneesh
 */
public class MainJframe extends javax.swing.JFrame {

    /**
     * Creates new form MainJframe
     */
    ArrayList<StudentProfile> departmentStudents;
    Map<String, Integer> hm2;
    HashMap<String, Integer> courseRankings;
    HashMap<String, Double> courseRatings;
    AluminiDirectory alumuniDirectory;
    public MainJframe() {
        initComponents();
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
        webCourseOffer.generatSeats(200);
        courseoffer.generatSeats(200);
        
        PersonDirectory pd = department.getPersonDirectory();
        Person person = pd.newPerson("0112303", faker.name().fullName());
        info5100.university.example.Persona.StudentDirectory sd = department.getStudentDirectory();
        StudentProfile student = sd.newStudentProfile(person);
        CourseLoad courseload = student.newCourseLoad("Fall2020"); 
        
        //Adding new student
        StudentProfile student2 = createNewStudent(department, "12123123");
        CourseLoad courseloadStudnet2 = student2.newCourseLoad("Fall2020");
        
        
        //Adding 3rd Student
        StudentProfile student3 = createNewStudent(department, "14324235");
        CourseLoad courseloadStudnet3 = student3.newCourseLoad("Fall2020");
        
        //Adding random students
        for(int i=0; i<100; i++){
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
        
        departmentStudents = department.getStudentDirectory().getStudentlist();
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
            hm.put(studentProfile.getPerson().getPersonId(),
                    studentProfile.getTranscript().calculateGPA());
        }
         
        //Prining the cgpa
        Map<String, Double> hm1 = sortByValue(hm); 
        // print the sorted hashmap 
        for (Map.Entry<String, Double> en : hm1.entrySet()) { 
            System.out.println("Key = " + en.getKey() +  
                          ", Value = " + en.getValue()); 
        } 
        
        System.out.println("----------------------Popular courses----------------------");
        
        
        courseRankings = department.mostPopularCourse("Fall2020"); 
        // print the sorted hashmap 
        for (Map.Entry<String, Integer> en : courseRankings.entrySet()) { 
            System.out.println("Key = " + en.getKey() +  
                          ", Value = " + en.getValue()); 
        } 
        
        System.out.println("----------------------Course Feedback----------------------");
        
        //feedbacks.add(student.generateCourseFeedback(webCourse, 10, 20, 30, 40));
        //feedbacks.add(student.generateCourseFeedback(course,  40, 50, 70, 40));
        
        
        courseRatings = new HashMap<>();
        
        //Fetch reviews based on courses
        for(Course c:department.getCourseCatalog().getCourseList()){
            courseRatings.put(c.getName(), getAverageCourseRatings(feedbacks,c));
            
        }
        
        for (Map.Entry<String, Double> en : courseRatings.entrySet()) { 
            System.out.println("Key = " + en.getKey() +  
                          ", Value = " + en.getValue()); 
        } 
        
        System.out.println("----------------------Alumini----------------------");
        
        alumuniDirectory = new AluminiDirectory();

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
        
        System.out.println("-----------grades and salry--------------");
         for(Alumini alum: alumuniDirectory.getAluminiList()){
            if(alum.getGrade() > 3){
                count++;
               for(Certifications cert:alum.getCertificationsDirectory().
                    getCertificationList()){
                    System.out.println(cert.getDomain());
                } 
            }
        }
        System.out.println(count);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 767, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 505, Short.MAX_VALUE)
        );

        jSplitPane1.setRightComponent(jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setBackground(new java.awt.Color(255, 102, 102));
        jButton1.setFont(new java.awt.Font("Lucida Grande", 0, 8)); // NOI18N
        jButton1.setText("Student Directory");
        jButton1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 102, 102));
        jButton2.setFont(new java.awt.Font("Lucida Grande", 0, 8)); // NOI18N
        jButton2.setText("Course Rankings by popularity");
        jButton2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 102, 102));
        jButton3.setFont(new java.awt.Font("Lucida Grande", 0, 8)); // NOI18N
        jButton3.setText("Certifications based on salary");
        jButton3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 102, 102));
        jButton4.setFont(new java.awt.Font("Lucida Grande", 0, 8)); // NOI18N
        jButton4.setText("Course Ranking by feedback");
        jButton4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 102, 102));
        jButton5.setFont(new java.awt.Font("Lucida Grande", 0, 8)); // NOI18N
        jButton5.setText("Domains based on Salaey");
        jButton5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(255, 102, 102));
        jButton6.setFont(new java.awt.Font("Lucida Grande", 0, 8)); // NOI18N
        jButton6.setText("Do grades matter");
        jButton6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2, jButton3, jButton4});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addGap(7, 7, 7)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(246, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton2, jButton3, jButton4, jButton5, jButton6});

        jSplitPane1.setLeftComponent(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1023, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jSplitPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        //Map<String, Integer> mapInversed = hm2.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
        CourseRankingPopularPanel courseRankingPopular = new CourseRankingPopularPanel(courseRankings);
        jSplitPane1.setRightComponent(courseRankingPopular);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        StudentDirectory dir = new StudentDirectory(departmentStudents);
        jSplitPane1.setRightComponent(dir);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        CourseRankingFeedback feedbackPanel = new CourseRankingFeedback(courseRatings);
        jSplitPane1.setRightComponent(feedbackPanel);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        AluminiCertification certs = new AluminiCertification(alumuniDirectory);
        jSplitPane1.setRightComponent(certs);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        DomainListingPanel certs = new DomainListingPanel(alumuniDirectory);
        jSplitPane1.setRightComponent(certs);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        DoGradesMatterPannel certs = new DoGradesMatterPannel(alumuniDirectory);
        jSplitPane1.setRightComponent(certs);
    }//GEN-LAST:event_jButton6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainJframe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSplitPane jSplitPane1;
    // End of variables declaration//GEN-END:variables
}
