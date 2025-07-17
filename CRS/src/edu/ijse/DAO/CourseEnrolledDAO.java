
package edu.ijse.DAO;


import edu.ijse.db.DbConnection;

import edu.ijse.model.EnrollDetails;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseEnrolledDAO {
    
    public boolean enrollStudentInCourse(String studentId, String courseCode) {
    String sql = "INSERT INTO courseenroll (student_id,program, c_code, c_title, faculty_id) " +
                 "SELECT ?,Programme, course_code, c_title, faculty_id  FROM courses WHERE course_code = ?";

    try (Connection conn = DbConnection.getConnection();
         PreparedStatement pst = conn.prepareStatement(sql)) {

        pst.setString(1, studentId);
        pst.setString(2, courseCode);

        int rowsInserted = pst.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Student " + studentId + " enrolled in course " + courseCode);
        } else {
            System.out.println("No course found for code: " + courseCode);
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
    }
        return false;

}
    
    
    public List<EnrollDetails> getEnrollById(String studentId) {
          List<EnrollDetails> enList = new ArrayList<>();
          String sql = "SELECT * FROM courseenroll WHERE student_id = ?";

    try (Connection con = DbConnection.getConnection()) {
        
         PreparedStatement ps = con.prepareStatement(sql);
         ps.setString(1, studentId.trim());
       
         try(
         ResultSet rs = ps.executeQuery()){
           while(rs.next()){
               
             EnrollDetails e = new EnrollDetails();
           
             e.setProgram(rs.getString("program"));
             e.setCourseCode(rs.getString("c_code"));
             e.setCourseTitle(rs.getString("c_title"));
             e.setFacultyId(rs.getString("faculty_id"));
            
             
             enList.add(e);
         }
         }

    } catch (SQLException e) {
        System.out.println("DEBUG: SQL Exception -> " + e.getMessage());
        System.out.println("DEBUG: Found rows = " + enList.size());

        e.printStackTrace();
    }
     return enList;
}
}
