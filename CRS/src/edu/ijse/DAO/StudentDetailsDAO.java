
package edu.ijse.DAO;

import edu.ijse.db.DbConnection;
import edu.ijse.model.StudentDetails;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;


public class StudentDetailsDAO {
    
    
   //Student detials registration portal
    
    public boolean registerStudent(StudentDetails st){
        
        String sql = "INSERT INTO studentdetails (user_id,faculty_name,name,DOB,Program,Acedemic_Year,Contact) VALUES (?,?,?,?,?,?,?)";
        String sql1 = "INSERT INTO users (user_id,role,password) VALUES (?,?,?)";
        
        try (
                Connection con = DbConnection.getConnection(); 
                PreparedStatement ps = con.prepareStatement(sql);
                PreparedStatement ps1 = con.prepareStatement(sql1)
            ) 
        {
            //StudentDetails table
            ps.setString(1, st.getUserId());
            ps.setString(2, st.getFacultyName());
            ps.setString(3, st.getName());
            ps.setString(4, st.getDateOfBirth());
            ps.setString(5, st.getProgramme());
            ps.setString(6, st.getAcademicYear());
            ps.setString(7, st.getContact());
            
            
            //passing Student data to user table
            ps1.setString(1, st.getUserId());
            ps1.setString(2, st.getRole());
            ps1.setString(3, st.getPassword());
            
         
            
            int rows1 = ps.executeUpdate();
            int rows2  = ps1.executeUpdate();
            
            return (rows1 > 0 && rows2 > 0);
            
            

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
       
        
    }
    
    public boolean getStudentDetailsById(StudentDetails student) {
    String sql = "SELECT * FROM studentdetails WHERE user_id = ?";
   

    try (Connection con = DbConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, student.getUserId().trim());
       

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                System.out.println("DEBUG: Row FOUND in studentdetails!");
                
                student.setName(rs.getString("name"));
                student.setFacultyName(rs.getString("faculty_name"));
                student.setDateOfBirth(rs.getString("DOB"));
                student.setProgramme(rs.getString("Program"));
                student.setAcademicYear(rs.getString("Acedemic_Year"));
                student.setContact(rs.getString("Contact"));

               
                return true;
            } else {
                System.out.println("DEBUG: No row returned from studentdetails!");
            }
        }

    } catch (SQLException e) {
        System.out.println("DEBUG: SQL Exception -> " + e.getMessage());
        e.printStackTrace();
    }

    return false;
}
    
     public boolean DeleteStudent(String userId){
         String sql = "DELETE FROM studentdetails WHERE user_id=?";
         String sql1 = "DELETE FROM users WHERE user_id=?";
          try (
                  Connection con = DbConnection.getConnection();
                  PreparedStatement ps = con.prepareStatement(sql);
                  PreparedStatement ps1 = con.prepareStatement(sql1)
                  ) 
          {
              ps.setString(1,userId);
              ps1.setString(1, userId);
              
              int rows1 = ps.executeUpdate();
              int rows2  = ps1.executeUpdate();
              
             return (rows1 > 0 && rows2 > 0);
             
          }catch (SQLException e) {
           e.printStackTrace();
           return false;
          
        }
        
          
                      
          }

    
}
