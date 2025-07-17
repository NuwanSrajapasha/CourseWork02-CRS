
package edu.ijse.crs;

import edu.ijse.DAO.AddDropDAO;
import edu.ijse.DAO.CourseEnrolledDAO;
import edu.ijse.DAO.CoursesDAO;
import edu.ijse.DAO.EligibilityDAO;
import edu.ijse.DAO.FacultyDetailsDAO;
import edu.ijse.DAO.FacultyProgramDAO;
import edu.ijse.db.DbConnection;
import edu.ijse.model.AddDrop;
import edu.ijse.model.Courses;
import edu.ijse.model.Eligibility;
import edu.ijse.model.EnrollDetails;
import edu.ijse.model.FacultyDetails;
import edu.ijse.model.FacultyProgram;
import edu.ijse.model.User;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;


public class FacultyDashboard extends javax.swing.JFrame {
     private User loggedInUser;
    
    public FacultyDashboard() {
        initComponents();
    }
    
     public FacultyDashboard(User user) {
        this.loggedInUser = user;
        initComponents();
        loadCourses();
        loadProgram();
        loadComboBoxData();
        loadEligible();
        loadEnroll();
        stat();
        
       // Prepare a faculty object with just userId
         FacultyDetails faculty = new FacultyDetails();
         faculty.setUserId(loggedInUser.getUserId());
        
         FacultyDetailsDAO ftd = new FacultyDetailsDAO();
         boolean success= ftd.getFacultytDetailsById(faculty);
         
         if (success) {
        // student object now has all details
        
        fid.setText(faculty.getUserId());
        fid.setEnabled(false);
        
        ffid.setText(faculty.getUserId());
        ffid.setEnabled(false);
        
        fcid.setText(faculty.getUserId());
        fcid.setEnabled(false);
        
        fname.setText(faculty.getName());
        fname.setEnabled(false);
        
        fcsid.setText(faculty.getUserId());
        fcsid.setEnabled(false);
    } else {
        JOptionPane.showMessageDialog(this, "No Faculty details found!");
    }
    }
     
      //course enroll details
      private void loadEnroll(){
          
          try{
            
          String stId = loggedInUser.getUserId();
          
          CourseEnrolledDAO edo = new  CourseEnrolledDAO();
          List<EnrollDetails> enList =edo.getEnrollByFId(stId);
          
            DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
            dtm.setRowCount(0);
            
            for(EnrollDetails eligible:enList){
                Vector <Object> v = new Vector<>();
                v.add(eligible.getStudentId());
                v.add(eligible.getProgram());
                v.add(eligible.getCourseCode());
                v.add(eligible.getCourseTitle());
                
                
                
                dtm.addRow(v);
            }
            
             
          
          } catch(Exception ex){
             ex.printStackTrace();
         }
     }
     
     //setup tab listner
      public void setupTabChangeListener(){
        jTabbedPane1.addChangeListener(new ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
               Component selectedPanel = jTabbedPane1.getSelectedComponent();
               
               if(selectedPanel == pmtab){
                   System.out.println("Programe Panel selected");
                   loadProgram();
               }else if (selectedPanel == cmtab){
                   System.out.println("Course Management Panel selected");
                   loadCourses();
                   loadComboBoxData();
                  
               }else if (selectedPanel == emtab){
                   System.out.println("Eligibility Panel selected");
                   loadEligible();
                  
               }else if (selectedPanel == add){
                   System.out.println("Add/Drop Panel selected");
                   stat();
                  
               }
               
               
            }
        });
        loadCourses();
      }
       
      //load eligibility table
      private void stat(){
            try {
            Connection con = DbConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("Select program,status FROM add_drop_period");
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int n = rsmd.getColumnCount();
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            while (rs.next()) {
                Vector v = new Vector();
                for (int i = 1; i < n; i++) {
                    v.add(rs.getString("program"));
                    v.add(rs.getString("status"));
                    
                    

                }
                dtm.addRow(v);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
      }
     //load programme data to combobox
      private void loadComboBoxData() {
        try {
            String facultyId = loggedInUser.getUserId();
            Connection con = DbConnection.getConnection();
            pcb.removeAllItems();
           

            PreparedStatement pst = con.prepareStatement("SELECT programm FROM facultyprograms WHERE faculty_id=?");
            pst.setString(1, facultyId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String programmes = rs.getString("programm");
                
                pcb.addItem(programmes);
                
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
          
    }
     
     //Load eligibility
      private void loadEligible(){
          
          try{
            
          String stId = loggedInUser.getUserId();
          
          EligibilityDAO edo = new EligibilityDAO();
          List<Eligibility> eligibilityList =edo.getEligibilityById(stId);
          
            DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
            dtm.setRowCount(0);
            
            for(Eligibility eligible:eligibilityList){
                Vector <Object> v = new Vector<>();
                v.add(eligible.getStudentId());
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
     
    //Load Courses data
      private void loadCourses(){
         try{
             String facultyId = loggedInUser.getUserId();
             
             CoursesDAO courseDAO = new CoursesDAO();
             List <Courses> courseList = courseDAO.getCoursesById(facultyId);
             
              DefaultTableModel dtm = (DefaultTableModel) coursetable.getModel();
              dtm.setRowCount(0);
              
              for (Courses course:courseList){
                  Vector <Object> v = new Vector<>();
                  v.add(course.getProgramme());
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
      //load faculty programm
       private void loadProgram(){
         try{
             String facultyId = loggedInUser.getUserId();
             
             FacultyProgramDAO programDAO = new  FacultyProgramDAO();
             List <FacultyProgram> program = programDAO.getProgramsById(facultyId);
             
              DefaultTableModel dtm = (DefaultTableModel) ptable.getModel();
              dtm.setRowCount(0);
              
              for (FacultyProgram programm:program){
                  Vector <Object> v = new Vector<>();
                 
                  v.add(programm.getProgramId());
                  v.add(programm.getProgram());
                
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
        pmtab = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        ffid = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ptable = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        pn = new javax.swing.JTextField();
        pid = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        cmtab = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        fid = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        fname = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        ccode = new javax.swing.JTextField();
        ctitle = new javax.swing.JTextField();
        cpr = new javax.swing.JTextField();
        mxc = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        ctable = new javax.swing.JScrollPane();
        coursetable = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        pcb = new javax.swing.JComboBox<>();
        emtab = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        stt = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        ct = new javax.swing.JTextField();
        cc = new javax.swing.JTextField();
        pg = new javax.swing.JTextField();
        stid = new javax.swing.JTextField();
        fcid = new javax.swing.JTextField();
        add = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        status = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        pg1 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        fcsid = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        pcgm = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        sst = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setBackground(new java.awt.Color(51, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Rockwell Condensed", 1, 15)); // NOI18N

        pmtab.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N
        jLabel8.setText("Faculty Id :");

        ffid.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Rockwell Condensed", 1, 36)); // NOI18N
        jLabel9.setText("Programme Management Dashboard");

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));

        ptable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Programm Id", "Programme Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(ptable);

        jLabel10.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N
        jLabel10.setText("Programme Name:");

        pn.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N

        pid.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N
        jLabel11.setText("Programme Id");

        jButton3.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jButton3.setText("SUBMIT");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Rockwell Condensed", 1, 24)); // NOI18N
        jButton4.setText("SIGN OUT");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pmtabLayout = new javax.swing.GroupLayout(pmtab);
        pmtab.setLayout(pmtabLayout);
        pmtabLayout.setHorizontalGroup(
            pmtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pmtabLayout.createSequentialGroup()
                .addGroup(pmtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pmtabLayout.createSequentialGroup()
                        .addGroup(pmtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pmtabLayout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jLabel8)
                                .addGap(25, 25, 25))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pmtabLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(pmtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(pid, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                            .addComponent(pn)
                            .addComponent(ffid))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pmtabLayout.createSequentialGroup()
                        .addGap(0, 47, Short.MAX_VALUE)
                        .addGroup(pmtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pmtabLayout.createSequentialGroup()
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(143, 143, 143))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pmtabLayout.createSequentialGroup()
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(256, 256, 256)))))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 794, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
            .addGroup(pmtabLayout.createSequentialGroup()
                .addGap(308, 308, 308)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pmtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pmtabLayout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addComponent(jLabel11)
                    .addContainerGap(1163, Short.MAX_VALUE)))
        );
        pmtabLayout.setVerticalGroup(
            pmtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pmtabLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGroup(pmtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pmtabLayout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addGroup(pmtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(ffid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addComponent(pid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(pmtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pmtabLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 217, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
            .addGroup(pmtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pmtabLayout.createSequentialGroup()
                    .addGap(203, 203, 203)
                    .addComponent(jLabel11)
                    .addContainerGap(586, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Programme Manage", pmtab);

        cmtab.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N
        jLabel1.setText("Faculty Id :");

        fid.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N
        jLabel2.setText("Name:");

        fname.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N
        jLabel3.setText("Course Code:");

        jLabel4.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N
        jLabel4.setText("Course Title:");

        jLabel5.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N
        jLabel5.setText("Pre-requisite:");

        jLabel6.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N
        jLabel6.setText("Max-Capacity");

        ccode.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N

        ctitle.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N

        cpr.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N

        mxc.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N

        jButton2.setFont(new java.awt.Font("Rockwell Condensed", 1, 14)); // NOI18N
        jButton2.setText("SUBMIT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        coursetable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Programme", "Course Code", "Course Title", "Pre-Requisites", "Max Capacity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        ctable.setViewportView(coursetable);

        jLabel7.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N
        jLabel7.setText("Programme:");

        pcb.setFont(new java.awt.Font("Rockwell Condensed", 1, 14)); // NOI18N
        pcb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select a Programme" }));

        javax.swing.GroupLayout cmtabLayout = new javax.swing.GroupLayout(cmtab);
        cmtab.setLayout(cmtabLayout);
        cmtabLayout.setHorizontalGroup(
            cmtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cmtabLayout.createSequentialGroup()
                .addGroup(cmtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cmtabLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(cmtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(25, 25, 25)
                        .addGroup(cmtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(fid, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(fname, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(ccode, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(ctitle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(cpr, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(mxc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(pcb, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cmtabLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)))
                .addComponent(ctable, javax.swing.GroupLayout.PREFERRED_SIZE, 884, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );
        cmtabLayout.setVerticalGroup(
            cmtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cmtabLayout.createSequentialGroup()
                .addGroup(cmtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cmtabLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(cmtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(fid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(cmtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(fname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(cmtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(pcb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(cmtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ccode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(cmtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ctitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(cmtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cpr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(cmtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mxc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addComponent(jButton2))
                    .addGroup(cmtabLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(ctable, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(384, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Course Management", cmtab);

        emtab.setBackground(new java.awt.Color(255, 255, 255));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Student Id", "Program", "Course Code", "Course Title"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable2);

        jLabel14.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jLabel14.setText("Change Status to Update Eligibility Of Students");

        jLabel15.setFont(new java.awt.Font("Rockwell Condensed", 1, 16)); // NOI18N
        jLabel15.setText("Status");

        jButton5.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jButton5.setText("Update");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jLabel16.setText("Update Status to Yes or No!!");

        jLabel17.setText("Faculty Id :");

        jLabel18.setText("Student Id:");

        jLabel19.setText("Program:");

        jLabel20.setText("Course Code:");

        jLabel21.setText("Course Title:");

        javax.swing.GroupLayout emtabLayout = new javax.swing.GroupLayout(emtab);
        emtab.setLayout(emtabLayout);
        emtabLayout.setHorizontalGroup(
            emtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, emtabLayout.createSequentialGroup()
                .addGroup(emtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(emtabLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(emtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(emtabLayout.createSequentialGroup()
                                .addGroup(emtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(emtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ct)
                                    .addComponent(cc)
                                    .addComponent(pg)
                                    .addComponent(stid)
                                    .addComponent(fcid)))
                            .addGroup(emtabLayout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(emtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(stt, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(emtabLayout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 864, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, emtabLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(291, 291, 291))
        );
        emtabLayout.setVerticalGroup(
            emtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(emtabLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel14)
                .addGap(46, 46, 46)
                .addGroup(emtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(emtabLayout.createSequentialGroup()
                        .addGroup(emtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(fcid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(emtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(stid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(emtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(pg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(emtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(cc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(emtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(ct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(emtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(stt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addComponent(jButton5)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel16)))
                .addContainerGap(343, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Eligibility Management", emtab);

        add.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Rockwell Condensed", 1, 25)); // NOI18N
        jLabel12.setText("Update Status to Active or Inactive to Enbale Add /Drop period or Disable it");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Program", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
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
        jScrollPane3.setViewportView(jTable1);

        jLabel13.setFont(new java.awt.Font("Rockwell Condensed", 0, 18)); // NOI18N
        jLabel13.setText("Status");

        jButton1.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jButton1.setText("Update");

        jLabel22.setFont(new java.awt.Font("Rockwell Condensed", 0, 16)); // NOI18N
        jLabel22.setText("Program");

        jLabel23.setText("Faculty ID :");

        jLabel24.setText("Program :");

        jLabel25.setText("Status :");

        jButton6.setText("Submit");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addLayout = new javax.swing.GroupLayout(add);
        add.setLayout(addLayout);
        addLayout.setHorizontalGroup(
            addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addLayout.createSequentialGroup()
                .addGap(240, 240, 240)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 794, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(256, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addGap(34, 34, 34)
                .addGroup(addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(status, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                    .addComponent(pg1))
                .addGap(510, 510, 510))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(569, 569, 569))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addLayout.createSequentialGroup()
                .addGroup(addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(addLayout.createSequentialGroup()
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fcsid, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(addLayout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addGap(18, 18, 18)
                                .addComponent(pcgm))
                            .addGroup(addLayout.createSequentialGroup()
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(sst)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton6)
                        .addGap(147, 147, 147)))
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 819, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88))
        );
        addLayout.setVerticalGroup(
            addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addLayout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jLabel12)
                .addGroup(addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addLayout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addGroup(addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(fcsid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(pcgm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(sst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addComponent(jButton6)))
                .addGap(98, 98, 98)
                .addGroup(addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(pg1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(117, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Add/Drop", add);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 840));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 836, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //Status update mouse click
    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        
        String StudentId = model.getValueAt(jTable2.getSelectedRow(), 0).toString();
        String Program = model.getValueAt(jTable2.getSelectedRow(), 1).toString();
        String CourseCode = model.getValueAt(jTable2.getSelectedRow(), 2).toString();
        String CourseTitle = model.getValueAt(jTable2.getSelectedRow(), 3).toString();
        
        stid.setText(StudentId);
        pg.setText(Program);
        cc.setText(CourseCode);
        ct.setText(CourseTitle);
    }//GEN-LAST:event_jTable2MouseClicked

    //Course adding to the system
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String FacultyId = fid.getText();
        String FacultyName = fname.getText();
        String Program = pcb.getSelectedItem().toString();
        String CourseCode = ccode.getText();
        String CourseTitle = ctitle.getText();
        String PreRequisites = cpr.getText();
        String MaxCapacity = mxc.getText();

        Courses crs = new Courses(Program,CourseCode,CourseTitle,FacultyName,PreRequisites,MaxCapacity,FacultyId);
        CoursesDAO cdao = new CoursesDAO();

        boolean success = cdao.registerCourses(crs);
        if(success){
            JOptionPane.showMessageDialog(null, "Registration Success");
            fid.setText("");
            fname.setText("");
            ccode.setText("");
            ctitle.setText("");
            cpr.setText("");
            mxc.setText("");

        }
        loadCourses();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        UserLogin login = new UserLogin();
        login.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

  //Programme adding to the system
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

        String FacultyId = ffid.getText();
        String ProgrammId = pid.getText();
        String ProgrammName = pn.getText();

        FacultyProgram  crs = new FacultyProgram(FacultyId,ProgrammId,ProgrammName);
        FacultyProgramDAO fdao = new FacultyProgramDAO();

        boolean success = fdao.registerProgram(crs);
        if(success){
            JOptionPane.showMessageDialog(null, "Registration Success");

            pid.setText("");
            pn.setText("");

        }
        loadProgram();
        loadComboBoxData();

    }//GEN-LAST:event_jButton3ActionPerformed

    //Inserting Data to Eligibilty Table
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        String faculty =fcid.getText();
        String stuid =stid.getText();
        String program =pg.getText();
        String cCode =cc.getText();
        String cTitle =ct.getText();
        String Status =stt.getText();
        
    Eligibility eb = new Eligibility(stuid,faculty,program,cCode,cTitle,Status);
    EligibilityDAO eDAO = new  EligibilityDAO();
    boolean success = eDAO.registereligibility(eb);
    
    if(success){
            JOptionPane.showMessageDialog(null, "Registration Success");
            stid.setText("");
            pg.setText("");
            cc.setText("");
            ct.setText("");
            stt.setText("");
            

        }
        loadEnroll();
        
    }//GEN-LAST:event_jButton5ActionPerformed
   //add drop submit
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        String FacId =fcsid.getText();
        String Pro = pcgm.getText();
        String Stat =sst.getText();
        
        AddDrop ad = new AddDrop(FacId,Pro,Stat);
        AddDropDAO adDAO = new AddDropDAO();
        boolean success = adDAO.registerCourses(ad);
        if(success){
             JOptionPane.showMessageDialog(null, "Registration Success");
            
             pcgm.setText("");
             sst.setText("");
        }
        
        stat();
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
         DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        
        String Pro = model.getValueAt(jTable1.getSelectedRow(), 0).toString();
        String Stat = model.getValueAt(jTable1.getSelectedRow(), 1).toString();
        
      
        pg1.setText(Pro);
        status.setText(Stat);
       
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
            java.util.logging.Logger.getLogger(FacultyDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FacultyDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FacultyDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FacultyDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FacultyDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel add;
    private javax.swing.JTextField cc;
    private javax.swing.JTextField ccode;
    private javax.swing.JPanel cmtab;
    private javax.swing.JTable coursetable;
    private javax.swing.JTextField cpr;
    private javax.swing.JTextField ct;
    private javax.swing.JScrollPane ctable;
    private javax.swing.JTextField ctitle;
    private javax.swing.JPanel emtab;
    private javax.swing.JTextField fcid;
    private javax.swing.JTextField fcsid;
    private javax.swing.JTextField ffid;
    private javax.swing.JTextField fid;
    private javax.swing.JTextField fname;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField mxc;
    private javax.swing.JComboBox<String> pcb;
    private javax.swing.JTextField pcgm;
    private javax.swing.JTextField pg;
    private javax.swing.JTextField pg1;
    private javax.swing.JTextField pid;
    private javax.swing.JPanel pmtab;
    private javax.swing.JTextField pn;
    private javax.swing.JTable ptable;
    private javax.swing.JTextField sst;
    private javax.swing.JTextField status;
    private javax.swing.JTextField stid;
    private javax.swing.JTextField stt;
    // End of variables declaration//GEN-END:variables
}
