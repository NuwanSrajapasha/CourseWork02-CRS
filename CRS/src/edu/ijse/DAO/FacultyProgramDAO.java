
package edu.ijse.DAO;

import edu.ijse.db.DbConnection;
import edu.ijse.model.FacultyProgram;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacultyProgramDAO {
    
      public boolean registerProgram(FacultyProgram program){
        
        String sql = "INSERT INTO facultyprograms (faculty_id,programm_id,programm) VALUES (?,?,?)";
        
        
        try (
                Connection con = DbConnection.getConnection(); 
                PreparedStatement ps = con.prepareStatement(sql);
              
            ) 
        {
            //FacultyDetails table
            ps.setString(1, program.getFacultyId());
            ps.setString(2, program.getProgramId());
            ps.setString(3, program.getProgram());
          
            int rows1 = ps.executeUpdate();
            return rows1>0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
       
        
    }
      
       //Get Program by faculty id
     public List<FacultyProgram> getProgramsById(String facultyId) {
         List<FacultyProgram> program = new ArrayList<>();
         String sql = "SELECT * FROM facultyprograms WHERE faculty_id = ?";
   

    try (Connection con = DbConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, facultyId.trim());
       

        try (ResultSet rs = ps.executeQuery()) {
           while (rs.next()) {
               
               FacultyProgram program1 = new FacultyProgram();
               program1.setProgramId(rs.getString("programm_id"));
               program1.setProgram(rs.getString("programm"));
             
              

              program.add(program1);
                
            } 
        }

    } catch (SQLException e) {
        System.out.println("DEBUG: SQL Exception -> " + e.getMessage());
        e.printStackTrace();
    }

    return program;
}
    
}
