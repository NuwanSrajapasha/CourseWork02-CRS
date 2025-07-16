
package edu.ijse.DAO;

import edu.ijse.db.DbConnection;
import edu.ijse.model.StudentDetails;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class StudentDetailsDAO {
    
   //Student detials registration portal
    
    public boolean registerStudent(StudentDetails st){
        
        String sql = "INSERT INTO studentdetails (user_id,name,DOB,Program,Acedemic_Year,Contact) VALUES (?,?,?,?,?,?)";
        String sql1 = "INSERT INTO users (user_id,role,password) VALUES (?,?,?)";
        
        try (
                Connection con = DbConnection.getConnection(); 
                PreparedStatement ps = con.prepareStatement(sql);
                PreparedStatement ps1 = con.prepareStatement(sql1)
            ) 
        {
            //StudentDetails table
            ps.setString(1, st.getUserId());
            ps.setString(2, st.getName());
            ps.setString(3, st.getDateOfBirth());
            ps.setString(4, st.getProgramme());
            ps.setString(5, st.getAcademicYear());
            ps.setString(6, st.getContact());
            
            
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
    
    
}
