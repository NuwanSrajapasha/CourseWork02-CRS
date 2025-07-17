
package edu.ijse.DAO;

import edu.ijse.db.DbConnection;
import edu.ijse.model.AddDrop;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AddDropDAO {
    
     public boolean registerCourses(AddDrop add){
        
        String sql = "INSERT INTO add_drop_period (faculty_id,program,status) VALUES (?,?,?)";
        
        
        try (
                Connection con = DbConnection.getConnection(); 
                PreparedStatement ps = con.prepareStatement(sql);
              
            ) 
        {
            //AddDrop table
            ps.setString(1, add.getFacultyId());
            ps.setString(2, add.getProgram());
            ps.setString(3, add.getStatus());
          
  
            
            int rows1 = ps.executeUpdate();
            return rows1>0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
       
        
    }
     
      //Get courses by faculty id
     public List<AddDrop> getStatById(String facultyId) {
         List<AddDrop> AddDropList = new ArrayList<>();
         String sql = "SELECT * FROM add_drop_period WHERE faculty_id = ?";
   

    try (Connection con = DbConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, facultyId.trim());
       

        try (ResultSet rs = ps.executeQuery()) {
           while (rs.next()) {
               
               AddDrop adddrop = new AddDrop();
               adddrop.setFacultyId(rs.getString("faculties_id"));
               adddrop.setProgram(rs.getString("program"));
               adddrop.setStatus(rs.getString("status"));
              

               AddDropList.add(adddrop);
                
            } 
        }

    } catch (SQLException e) {
        System.out.println("DEBUG: SQL Exception -> " + e.getMessage());
        e.printStackTrace();
    }

    return AddDropList;
}
    
     
     //add drop checking and availability of buttons
     
  public boolean isAddDropActive(String facultyId) {
    String sql = "SELECT status FROM add_drop_period WHERE faculty_id = ?";
    try (Connection con = DbConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, facultyId.trim());
        System.out.println("DEBUG: Running query for faculty_id=" + facultyId);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                String status = rs.getString("status");
                System.out.println("DEBUG: Found status = " + status);

                return "ACTIVE".equalsIgnoreCase(status); 
            } else {
                System.out.println("DEBUG: No row found for this faculty!");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false; // default
}
     
}
