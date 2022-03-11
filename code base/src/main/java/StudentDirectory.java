
import com.github.javafaker.Faker;
import info5100.university.example.Department.Department;
import info5100.university.example.Persona.StudentProfile;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author maneesh
 */
public class StudentDirectory extends javax.swing.JPanel {

    /**
     * Creates new form StudentDirectory
     */
     ArrayList<StudentProfile> departmentStudents;
    public StudentDirectory(ArrayList<StudentProfile> departmentStudents) {
        initComponents();
        populateTable(departmentStudents);
    }
    
    private void populateTable(ArrayList<StudentProfile> departmentStudents){
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        
        for(StudentProfile studentProfile: departmentStudents){
            Object[] row = new Object[2];
            row[0] = studentProfile;
            row[1] = studentProfile.getPerson().getName();
            model.addRow(row);
            System.out.println(studentProfile.getPerson().getPersonId());
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtInternships = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        txtCgpa = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Student Directory");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 6, 380, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Person ID", "Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 28, 375, 219));

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 51));
        jLabel2.setText("Student Details");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, -1, -1));

        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Name");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, -1, -1));

        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("NEU ID");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, -1, -1));

        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("CGPA");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, -1, -1));

        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Internships");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, -1, -1));

        txtInternships.setEditable(false);
        txtInternships.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 102, 102)));
        add(txtInternships, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 410, 140, -1));

        txtName.setEditable(false);
        txtName.setForeground(new java.awt.Color(102, 102, 102));
        txtName.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 102, 102)));
        add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 320, 140, -1));

        txtId.setEditable(false);
        txtId.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 102, 102)));
        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });
        add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 350, 140, -1));

        txtCgpa.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 102, 102)));
        add(txtCgpa, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 380, 140, -1));

        jButton1.setText("Get Student Details");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 250, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    public static double round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();

    long factor = (long) Math.pow(10, places);
    value = value * factor;
    long tmp = Math.round(value);
    return (double) tmp / factor;
}
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable1.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        StudentProfile selected = (StudentProfile) model.getValueAt(selectedRow, 0);
        txtId.setText(selected.getPerson().getPersonId());
        txtName.setText(selected.getPerson().getName());
        txtCgpa.setText(String.valueOf(round(selected.getTranscript().calculateGPA(), 2)));
        Faker fake = new Faker();
        txtInternships.setText(String.valueOf(fake.bool().bool()));
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtCgpa;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtInternships;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables

    
}
