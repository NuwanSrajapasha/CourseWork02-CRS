
package edu.ijse.crs;

import edu.ijse.DAO.CoursesDAO;
import edu.ijse.DAO.StudentDetailsDAO;
import edu.ijse.model.Courses;
import edu.ijse.model.StudentDetails;
import edu.ijse.model.User;
import java.awt.Component;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;


public class StudentDashboard extends javax.swing.JFrame {

    private User loggedInUser;
    public StudentDashboard() {
        initComponents();
        setupTabChangeListener();
    }
    
     public StudentDashboard(User user) {
        this.loggedInUser = user;
        initComponents();
        System.out.println("DEBUG: Logged in user ID = '" + loggedInUser.getUserId() + "'");
        loadUserData();
        loadCourses();
       
        //snme.setText(loggedInUser.getUsername()
     }
     
     //setup tab listner
      public void setupTabChangeListener(){
        jTabbedPane1.addChangeListener(new ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
               Component selectedPanel = jTabbedPane1.getSelectedComponent();
               
               if(selectedPanel == stdetails){
                   System.out.println("Student Panel selected");
                   loadUserData();
               }else if (selectedPanel == estatus){
                   System.out.println("Eligibility Status Panel selected");
                   //loadFacultyData();
                  
               }else if (selectedPanel == crtab){
                   System.out.println("Course Registration Panel selected");
                   loadCourses();
                  
               }else if (selectedPanel == ectab){
                   System.out.println("Faculty Panel selected");
                   //loadFacultyData();
                  
               }
               
               
            }
        });

      loadUserData();
  }
      //load student data
      private void loadUserData(){
            // Prepare a StudentDetails object with just userId
         StudentDetails student = new StudentDetails();
         student.setUserId(loggedInUser.getUserId());
        
         StudentDetailsDAO std = new StudentDetailsDAO();
         boolean success= std.getStudentDetailsById(student);
         
         if (success) {
        // student object now has all details
         snme.setText(student.getName());
         fname.setText(student.getFacultyName());
         uid.setText(student.getUserId());
         prg.setText(student.getProgramme());
         dob.setText(student.getDateOfBirth());
         ay.setText(student.getAcademicYear());
         con.setText(student.getContact());
         test.setText(student.getFacultyName());
    } else {
        JOptionPane.showMessageDialog(this, "No student details found!");
    }
      }
     
     //load courses to a jTable
      private void loadCourses(){
         try{
             String facName = test.getText() ;
             test.setEnabled(false);
             CoursesDAO courseDAO = new CoursesDAO();
             List <Courses> courseList = courseDAO.getCoursesByName(facName);
             
              DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
              dtm.setRowCount(0);
              
              for (Courses course:courseList){
                  Vector <Object> v = new Vector<>();
                  v.add(course.getCourseCode());
                  v.add(course.getCourseTitle());
                  v.add(course.getPrerequisite());
                  v.add(course.getMaxCapacity());
                  dtm.addRow(v);
                  
                  
              }
         } catch(Exception ex){
             ex.printStackTrace();
         }
     }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        stdetails = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        uid = new javax.swing.JLabel();
        snme = new javax.swing.JLabel();
        prg = new javax.swing.JLabel();
        dob = new javax.swing.JLabel();
        ay = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        con = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        fname = new javax.swing.JLabel();
        estatus = new javax.swing.JPanel();
        crtab = new javax.swing.JPanel();
        ctable = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        test = new javax.swing.JLabel();
        ectab = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setBackground(new java.awt.Color(102, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N

        stdetails.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("User Id : ");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("Name :");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setText("Faculty :");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/pr.jpg"))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Rockwell Condensed", 1, 30)); // NOI18N
        jLabel5.setText("Welcome To The Student Dashboard");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setText("Date Of Birth :");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setText("Acedemic Year :");

        uid.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N

        snme.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N

        prg.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N

        dob.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N

        ay.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N
        jLabel9.setText("Contact :");

        con.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/side sd new.jpg"))); // NOI18N

        jButton1.setBackground(new java.awt.Color(0, 102, 102));
        jButton1.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("SIGN OUT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel10.setText("Programme :");

        fname.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N

        javax.swing.GroupLayout stdetailsLayout = new javax.swing.GroupLayout(stdetails);
        stdetails.setLayout(stdetailsLayout);
        stdetailsLayout.setHorizontalGroup(
            stdetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stdetailsLayout.createSequentialGroup()
                .addGroup(stdetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(stdetailsLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(stdetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, stdetailsLayout.createSequentialGroup()
                                .addGroup(stdetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel10))
                                .addGap(37, 37, 37))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, stdetailsLayout.createSequentialGroup()
                                .addGroup(stdetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(stdetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(stdetailsLayout.createSequentialGroup()
                                            .addGap(6, 6, 6)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel1)))
                                .addGap(29, 29, 29)))
                        .addGroup(stdetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(stdetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(uid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(snme, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(prg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dob, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(con, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                                .addComponent(fname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(stdetailsLayout.createSequentialGroup()
                        .addGap(169, 169, 169)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(stdetailsLayout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
                .addContainerGap())
        );
        stdetailsLayout.setVerticalGroup(
            stdetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stdetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(stdetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(stdetailsLayout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jLabel5)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                        .addGroup(stdetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(stdetailsLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2))
                            .addGroup(stdetailsLayout.createSequentialGroup()
                                .addComponent(uid, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(snme, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(stdetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(stdetailsLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(jLabel6))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, stdetailsLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fname, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(prg, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dob, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(stdetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ay, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(stdetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(stdetailsLayout.createSequentialGroup()
                                .addComponent(con, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50))
                    .addGroup(stdetailsLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        jTabbedPane1.addTab("Student Details", stdetails);

        estatus.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout estatusLayout = new javax.swing.GroupLayout(estatus);
        estatus.setLayout(estatusLayout);
        estatusLayout.setHorizontalGroup(
            estatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1300, Short.MAX_VALUE)
        );
        estatusLayout.setVerticalGroup(
            estatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 819, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Eligibility Status", estatus);

        crtab.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Course Code", "Course Title", "Pre-Requisites", "Max Capacity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        ctable.setViewportView(jTable1);

        test.setFont(new java.awt.Font("Rockwell Condensed", 1, 24)); // NOI18N

        javax.swing.GroupLayout crtabLayout = new javax.swing.GroupLayout(crtab);
        crtab.setLayout(crtabLayout);
        crtabLayout.setHorizontalGroup(
            crtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crtabLayout.createSequentialGroup()
                .addContainerGap(386, Short.MAX_VALUE)
                .addGroup(crtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crtabLayout.createSequentialGroup()
                        .addComponent(ctable, javax.swing.GroupLayout.PREFERRED_SIZE, 884, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crtabLayout.createSequentialGroup()
                        .addComponent(test, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(322, 322, 322))))
        );
        crtabLayout.setVerticalGroup(
            crtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crtabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(test, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ctable, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(351, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Course Registration", crtab);

        ectab.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout ectabLayout = new javax.swing.GroupLayout(ectab);
        ectab.setLayout(ectabLayout);
        ectabLayout.setHorizontalGroup(
            ectabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1300, Short.MAX_VALUE)
        );
        ectabLayout.setVerticalGroup(
            ectabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 819, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Enrolled Courses", ectab);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 850));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        UserLogin login = new UserLogin();
        login.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(StudentDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ay;
    private javax.swing.JLabel con;
    private javax.swing.JPanel crtab;
    private javax.swing.JScrollPane ctable;
    private javax.swing.JLabel dob;
    private javax.swing.JPanel ectab;
    private javax.swing.JPanel estatus;
    private javax.swing.JLabel fname;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel prg;
    private javax.swing.JLabel snme;
    private javax.swing.JPanel stdetails;
    private javax.swing.JLabel test;
    private javax.swing.JLabel uid;
    // End of variables declaration//GEN-END:variables
}
