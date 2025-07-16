
package edu.ijse.DAO;

import edu.ijse.db.DbConnection;
import edu.ijse.model.FacultyDetails;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class FacultyDetailsDAO {
    
    //Student detials registration portal
    
    public boolean registerFaculty(FacultyDetails fd){
        
        String sql = "INSERT INTO facultydetails (user_id,name,contact,email) VALUES (?,?,?,?)";
        String sql1 = "INSERT INTO users (user_id,role,password) VALUES (?,?,?)";
        
        try (
                Connection con = DbConnection.getConnection(); 
                PreparedStatement ps = con.prepareStatement(sql);
                PreparedStatement ps1 = con.prepareStatement(sql1)
            ) 
        {
            //FacultyDetails table
            ps.setString(1, fd.getUserId());
            ps.setString(2, fd.getName());
            ps.setString(3, fd.getContact());
            ps.setString(4, fd.getEmail());
            
            
            //passing Student data to user table
            ps1.setString(1, fd.getUserId());
            ps1.setString(2, fd.getRole());
            ps1.setString(3, fd.getPassword());
            
         
            
            int rows1 = ps.executeUpdate();
            int rows2  = ps1.executeUpdate();
            
            return (rows1 > 0 && rows2 > 0);
            
            

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
       
        
    }
    
   public boolean getFacultytDetailsById(FacultyDetails faculty) {
    String sql = "SELECT * FROM facultydetails WHERE user_id = ?";
   

    try (Connection con = DbConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, faculty.getUserId().trim());
       

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
               
                
                faculty.setName(rs.getString("name"));
                faculty.setContact(rs.getString("contact"));
                faculty.setEmail(rs.getString("email"));

               
                return true;
            } else {
                System.out.println("DEBUG: No row returned from Facultydetails!");
            }
        }

    } catch (SQLException e) {
        System.out.println("DEBUG: SQL Exception -> " + e.getMessage());
        e.printStackTrace();
    }

    return false;
}
    
}
