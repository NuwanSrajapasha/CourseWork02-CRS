
package edu.ijse.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import edu.ijse.db.DbConnection;
import edu.ijse.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class UserDAO {
    
    //customer registartion portal
    public boolean registerUser(User user) {
        
        String sql = "INSERT INTO users (user_id,role,password) VALUES(?, ?,?)";

        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user.getUserId());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getRole());
         

            int rowInserted = ps.executeUpdate();
            return rowInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
        
    //Admin Registration process via Admin Pannel
    public boolean registerUser1(User user1) {
        String sql = "INSERT INTO users (user_id,role,password) VALUES(?,?,?)";

        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1,user1.getUserId());
            ps.setString(4, user1.getPassword());
            ps.setString(5, user1.getRole());
          

            int rowInserted = ps.executeUpdate();
            return rowInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
         
    //User Login
    public User login(String userId, String password) {
        String sql = "SELECT * FROM users WHERE user_id=? AND password=?";

        try (Connection con = DbConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, userId);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
                

        if (rs.next()) {
            // Create a User object from DB data
            User user = new User();
            user.setUserId(rs.getString("user_id"));
            user.setRole(rs.getString("role"));
            user.setPassword(rs.getString("password")); // optional
            return user;
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return null; // no match found
}
    
    
}
