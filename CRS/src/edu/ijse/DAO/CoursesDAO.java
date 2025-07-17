
package edu.ijse.DAO;

import edu.ijse.db.DbConnection;
import edu.ijse.model.Courses;
import edu.ijse.model.FacultyDetails;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class CoursesDAO {
    
     public boolean registerCourses(Courses course){
        
        String sql = "INSERT INTO courses (Programme,course_code,c_title,faculty,prerequisites,max_capacity,faculty_id) VALUES (?,?,?,?,?,?,?)";
        
        
        try (
                Connection con = DbConnection.getConnection(); 
                PreparedStatement ps = con.prepareStatement(sql);
              
            ) 
        {
            //FacultyDetails table
            ps.setString(1, course.getProgramme());
            ps.setString(2, course.getCourseCode());
            ps.setString(3, course.getCourseTitle());
            ps.setString(4, course.getFaculty());
            ps.setString(5, course.getPrerequisite());
            ps.setString(6, course.getMaxCapacity());
            ps.setString(7, course.getFacultyId());
  
            
            int rows1 = ps.executeUpdate();
            return rows1>0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
       
        
    }
     
     //Get courses by faculty id
     public List<Courses> getCoursesById(String facultyId) {
         List<Courses> courseList = new ArrayList<>();
         String sql = "SELECT * FROM courses WHERE faculty_id = ?";
   

    try (Connection con = DbConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, facultyId.trim());
       

        try (ResultSet rs = ps.executeQuery()) {
           while (rs.next()) {
               
               Courses course1 = new Courses();
               course1.setProgramme(rs.getString("Programme"));
               course1.setCourseCode(rs.getString("course_code"));
               course1.setCourseTitle(rs.getString("c_title"));
               //course1.setFaculty(rs.getString("faculty"));
               course1.setPrerequisite(rs.getString("prerequisites"));
               course1.setMaxCapacity(rs.getString("max_capacity"));

               courseList.add(course1);
                
            } 
        }

    } catch (SQLException e) {
        System.out.println("DEBUG: SQL Exception -> " + e.getMessage());
        e.printStackTrace();
    }

    return courseList;
}
     
      //Get courses by faculty name
     public List<Courses> getCoursesByName(String facultyname) {
         List<Courses> courseList = new ArrayList<>();
         String sql = "SELECT * FROM courses WHERE faculty= ?";
   

    try (Connection con = DbConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, facultyname);
       

        try (ResultSet rs = ps.executeQuery()) {
           while (rs.next()) {
               
               Courses course1 = new Courses();
               course1.setCourseCode(rs.getString("Programme"));
               course1.setCourseCode(rs.getString("course_code"));
               course1.setCourseTitle(rs.getString("c_title"));
               //course1.setFaculty(rs.getString("faculty"));
               course1.setPrerequisite(rs.getString("prerequisites"));
               course1.setMaxCapacity(rs.getString("max_capacity"));

               courseList.add(course1);
                
            } 
        }

    } catch (SQLException e) {
        System.out.println("DEBUG: SQL Exception -> " + e.getMessage());
        e.printStackTrace();
    }

    return courseList;
}
     
     
//     public boolean getCoursesById(Courses course1) {
//    String sql = "SELECT * FROM courses WHERE faculty_id = ?";
//   
//
//    try (Connection con = DbConnection.getConnection();
//         PreparedStatement ps = con.prepareStatement(sql)) {
//
//        ps.setString(1, course1.getFacultyId().trim());
//       
//
//        try (ResultSet rs = ps.executeQuery()) {
//            if (rs.next()) {
//               
//                
//               course1.setCourseCode(rs.getString("course_code"));
//               course1.setCourseTitle(rs.getString("c_title"));
//               //course1.setFaculty(rs.getString("faculty"));
//               course1.setPrerequisite(rs.getString("prerequisites"));
//               course1.setMaxCapacity(rs.getString("max_capacity"));
//
//               
//                return true;
//            } else {
//                System.out.println("DEBUG: No row returned from Courses!");
//            }
//        }
//
//    } catch (SQLException e) {
//        System.out.println("DEBUG: SQL Exception -> " + e.getMessage());
//        e.printStackTrace();
//    }
//
//    return false;
//}
    
}
