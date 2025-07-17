
package edu.ijse.DAO;

import edu.ijse.db.DbConnection;
import edu.ijse.model.Eligibility;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EligibilityDAO {
    
    //for faculty to register eligibility of a student
     public boolean registereligibility(Eligibility eligibility){
        
        String sql = "INSERT INTO eligibility (student_id,faculty_id,program,course_code,course_title,status) VALUES (?,?,?,?,?,?)";
        
        
        try (
                Connection con = DbConnection.getConnection(); 
                PreparedStatement ps = con.prepareStatement(sql);
              
            ) 
        {
            //FacultyDetails table
            ps.setString(1, eligibility.getStudentId());
            ps.setString(2, eligibility.getFacultyId());
            ps.setString(3, eligibility.getProgram());
            ps.setString(4, eligibility.getCourseCode());
            ps.setString(5, eligibility.getCourseTitle());
            ps.setString(6, eligibility.getStatus());
            
  
            
            int rows1 = ps.executeUpdate();
            return rows1>0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
       
        
    }
     
      public boolean canStudentEnroll(String studentId, String courseCode) {
        boolean eligible = false;

        String prerequisite = null;

        String sqlGetPrereq = "SELECT prerequisites FROM courses WHERE course_code = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pst1 = conn.prepareStatement(sqlGetPrereq)) {

            pst1.setString(1, courseCode);
            ResultSet rs1 = pst1.executeQuery();

            if (rs1.next()) {
                prerequisite = rs1.getString("prerequisites");
            }

            // Case 1: No prerequisite → eligible
            if (prerequisite == null || prerequisite.isEmpty()) {
                return true;
            }

            //Case 2: Has prerequisite → check if student has passed it
            String sqlCheckEnrolled = 
                "SELECT status FROM eligibility WHERE student_id = ? AND course_code = ?";

            try (PreparedStatement pst2 = conn.prepareStatement(sqlCheckEnrolled)) {
                pst2.setString(1, studentId);
                pst2.setString(2, prerequisite);
                ResultSet rs2 = pst2.executeQuery();

                if (rs2.next()) {
                    String status = rs2.getString("status");
                    if ("yes".equalsIgnoreCase(status)) {
                        eligible = true;
                    }
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return eligible;
    }
    
      
     public List<Eligibility> getEligibilityById(String studentId) {
          List<Eligibility> eligibilityList = new ArrayList<>();
          String sql = "SELECT * FROM eligibility WHERE student_id = ?";

    try (Connection con = DbConnection.getConnection()) {
        
         PreparedStatement ps = con.prepareStatement(sql);
         ps.setString(1, studentId.trim());
       
         try(
         ResultSet rs = ps.executeQuery()){
           while(rs.next()){
               
             Eligibility e = new Eligibility();
             e.setFacultyId(rs.getString("faculty_id"));
             e.setProgram(rs.getString("program"));
             e.setCourseCode(rs.getString("course_code"));
             e.setCourseTitle(rs.getString("course_title"));
             e.setStatus(rs.getString("status"));
             
             eligibilityList.add(e);
         }
         }

    } catch (SQLException e) {
        System.out.println("DEBUG: SQL Exception -> " + e.getMessage());
        System.out.println("DEBUG: Found rows = " + eligibilityList.size());

        e.printStackTrace();
    }
     return eligibilityList;
}
}
