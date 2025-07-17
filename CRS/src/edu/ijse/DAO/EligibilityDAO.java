
package edu.ijse.DAO;

import edu.ijse.db.DbConnection;
import edu.ijse.model.Eligibility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


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
    
}
