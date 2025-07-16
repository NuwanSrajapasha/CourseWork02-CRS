/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.ijse.model;

/**
 *
 * @author ASUS
 */
public class FacultyDetails {
    
     private String userId;
     private String name;
     private String Contact;
     private String Email;
     private String role;
     private String Password;
     
     public FacultyDetails() {}

    public FacultyDetails(String userId, String name, String Contact,String Email,String Password) {
        this.userId = userId;
        this.name = name;
        this.Email = Email;
        this.Contact = Contact;
        this.role = "Faculty";
        this.Password = Password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String Contact) {
        this.Contact = Contact;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
     
     
    
}
