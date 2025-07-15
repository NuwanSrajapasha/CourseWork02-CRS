
package edu.ijse.model;

public class User {
     private String userId;
     private String username;
     private String email;
     private String password;
     private String role;
     private String status;
     
     public User() {}
     
     public User(String username, String password, String role) {
      
      this.username = username;
      this.role = role;
      this.password = password;
      
      
}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}