
package edu.ijse.crs;

import edu.ijse.DAO.CourseEnrolledDAO;
import edu.ijse.DAO.CoursesDAO;
import edu.ijse.DAO.EligibilityDAO;
import edu.ijse.DAO.FacultyDetailsDAO;
import edu.ijse.DAO.StudentDetailsDAO;
import edu.ijse.model.Courses;
import edu.ijse.model.Eligibility;
import edu.ijse.model.EnrollDetails;
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
    private String facultyName;
    private String facultyId;
    private String studentId;
    private String stid;
   
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
        loadEligible();
        loadEnroll();
        
        
        //passing facultyId
         String studentId = loggedInUser.getUserId();
         StudentDetailsDAO studentDAO = new StudentDetailsDAO();
         facultyName = studentDAO.getFacultyNameByStudentId(studentId);
         
         FacultyDetailsDAO facultyDetailsDAO = new FacultyDetailsDAO();
         String facultyId =facultyDetailsDAO.getFacultyIdByName(facultyName);
         
         sfid.setText(facultyId);
         sfid.setEnabled(false);
         
         
         
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
                   loadEligible();
                  
               }else if (selectedPanel == crtab){
                   System.out.println("Course Registration Panel selected");
                   loadCourses();
                  
               }else if (selectedPanel == ectab){
                   System.out.println("Enroll Courses Panel selected");
                   loadEnroll();
                  
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
         ppv.setText(student.getProgramme());
         ppv.setEnabled(false);
         sid.setText(student.getUserId());
         sid.setEnabled(false);
         
    } else {
        JOptionPane.showMessageDialog(this, "No student details found!");
    }
      }
      
      private void loadEligible(){
          
          try{
            
          String stId = loggedInUser.getUserId();
          
          EligibilityDAO edo = new EligibilityDAO();
          List<Eligibility> eligibilityList =edo.getEligibilityById(stId);
          
            DefaultTableModel dtm = (DefaultTableModel) eligit.getModel();
            dtm.setRowCount(0);
            
            for(Eligibility eligible:eligibilityList){
                Vector <Object> v = new Vector<>();
                v.add(eligible.getFacultyId());
                v.add(eligible.getProgram());
                v.add(eligible.getCourseCode());
                v.add(eligible.getCourseTitle());
                v.add(eligible.getStatus());
                
                dtm.addRow(v);
            }
            
             
          
          } catch(Exception ex){
             ex.printStackTrace();
         }
     }
      //cpourse enroll details
      private void loadEnroll(){
          
          try{
            
          String stId = loggedInUser.getUserId();
          
          CourseEnrolledDAO edo = new  CourseEnrolledDAO();
          List<EnrollDetails> enList =edo.getEnrollById(stId);
          
            DefaultTableModel dtm = (DefaultTableModel) etable.getModel();
            dtm.setRowCount(0);
            
            for(EnrollDetails eligible:enList){
                Vector <Object> v = new Vector<>();
                v.add(eligible.getFacultyId());
                v.add(eligible.getProgram());
                v.add(eligible.getCourseCode());
                v.add(eligible.getCourseTitle());
                
                
                dtm.addRow(v);
            }
            
             
          
          } catch(Exception ex){
             ex.printStackTrace();
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
                 // v.add(course.getMaxCapacity());
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
        jScrollPane1 = new javax.swing.JScrollPane();
        eligit = new javax.swing.JTable();
        crtab = new javax.swing.JPanel();
        ctable = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        test = new javax.swing.JLabel();
        ppv = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        sid = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        ccd = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        cct = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        sfid = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        ectab = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        etable = new javax.swing.JTable();

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

        eligit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Faculty Id", "Program", "Course Code", "Course Title", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(eligit);

        javax.swing.GroupLayout estatusLayout = new javax.swing.GroupLayout(estatus);
        estatus.setLayout(estatusLayout);
        estatusLayout.setHorizontalGroup(
            estatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(estatusLayout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1020, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(144, Short.MAX_VALUE))
        );
        estatusLayout.setVerticalGroup(
            estatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(estatusLayout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(279, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Eligibility Status", estatus);

        crtab.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Course Code", "Course Title", "Pre-Requisites"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        ctable.setViewportView(jTable1);

        test.setFont(new java.awt.Font("Rockwell Condensed", 1, 24)); // NOI18N

        ppv.setFont(new java.awt.Font("Rockwell Condensed", 1, 24)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N
        jLabel11.setText("Student Id");

        sid.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N
        jLabel12.setText("Course Code");

        ccd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N
        jLabel13.setText("Course Title");

        cct.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N
        jLabel14.setText("Faculty Id");

        sfid.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jButton2.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N
        jButton2.setText("Register For a Course");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout crtabLayout = new javax.swing.GroupLayout(crtab);
        crtab.setLayout(crtabLayout);
        crtabLayout.setHorizontalGroup(
            crtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crtabLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(crtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crtabLayout.createSequentialGroup()
                        .addComponent(test, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(322, 322, 322))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crtabLayout.createSequentialGroup()
                        .addComponent(ppv, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(272, 272, 272))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crtabLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(crtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(crtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(crtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(sid)
                        .addComponent(ccd)
                        .addComponent(cct)
                        .addComponent(sfid, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(ctable, javax.swing.GroupLayout.PREFERRED_SIZE, 884, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        crtabLayout.setVerticalGroup(
            crtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crtabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(test, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ppv, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(crtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(crtabLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(ctable, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(crtabLayout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(crtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sid, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(crtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ccd, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(crtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(cct, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(crtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sfid, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addComponent(jButton2)))
                .addContainerGap(287, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Course Registration", crtab);

        ectab.setBackground(new java.awt.Color(255, 255, 255));

        etable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Program", "Course Code", "Course Title", "Faculty Id"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(etable);

        javax.swing.GroupLayout ectabLayout = new javax.swing.GroupLayout(ectab);
        ectab.setLayout(ectabLayout);
        ectabLayout.setHorizontalGroup(
            ectabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ectabLayout.createSequentialGroup()
                .addContainerGap(181, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 990, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(129, 129, 129))
        );
        ectabLayout.setVerticalGroup(
            ectabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ectabLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(174, Short.MAX_VALUE))
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
  
    //course registration
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        String studentId=loggedInUser.getUserId();
        String selectedCourseCode = ccd.getText();
        
        if (selectedCourseCode.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please select a course first!");
        return;
    }
       
    // ✅ Check eligibility
    EligibilityDAO eligibilityDAO = new EligibilityDAO();
    boolean canEnroll = eligibilityDAO.canStudentEnroll(studentId, selectedCourseCode);

    if (canEnroll) {
        // ✅ If eligible → enroll
        CourseEnrolledDAO enrolledDAO = new CourseEnrolledDAO();
        enrolledDAO.enrollStudentInCourse(studentId, selectedCourseCode);

        JOptionPane.showMessageDialog(this,
            "✅ You have been enrolled in " + selectedCourseCode);
    } else {
        // ❌ Not eligible
        JOptionPane.showMessageDialog(this,
            "❌ You cannot register for " + selectedCourseCode +
            ". Please complete the prerequisite first.",
            "Eligibility Error", JOptionPane.ERROR_MESSAGE);
    }
        
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    //mouse clicked for course details
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        
        String selectedCourseCode = model.getValueAt(jTable1.getSelectedRow(), 0).toString();
        String title = model.getValueAt(jTable1.getSelectedRow(), 1).toString();
        ccd.setText(selectedCourseCode);
        cct.setText(title);
    }//GEN-LAST:event_jTable1MouseClicked

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
    private javax.swing.JTextField ccd;
    private javax.swing.JTextField cct;
    private javax.swing.JLabel con;
    private javax.swing.JPanel crtab;
    private javax.swing.JScrollPane ctable;
    private javax.swing.JLabel dob;
    private javax.swing.JPanel ectab;
    private javax.swing.JTable eligit;
    private javax.swing.JPanel estatus;
    private javax.swing.JTable etable;
    private javax.swing.JLabel fname;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField ppv;
    private javax.swing.JLabel prg;
    private javax.swing.JTextField sfid;
    private javax.swing.JTextField sid;
    private javax.swing.JLabel snme;
    private javax.swing.JPanel stdetails;
    private javax.swing.JLabel test;
    private javax.swing.JLabel uid;
    // End of variables declaration//GEN-END:variables
}
