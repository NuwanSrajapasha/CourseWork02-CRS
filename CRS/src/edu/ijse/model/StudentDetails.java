
package edu.ijse.model;


public class StudentDetails {
     private String userId;
     private String name;
     private String DateOfBirth;
     private String Programme;
     private String AcademicYear;
     private String Contact;
     private String role;
     private String Password;
     
     public StudentDetails() {}

    public StudentDetails(String userId, String name, String DateOfBirth, String Programme, String AcademicYear, String Contact,String Password) {
        this.userId = userId;
        this.name = name;
        this.DateOfBirth = DateOfBirth;
        this.Programme = Programme;
        this.AcademicYear = AcademicYear;
        this.Contact = Contact;
        this.role = "Student"; //default role
        this.Password = Password;
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

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String DateOfBirth) {
        this.DateOfBirth = DateOfBirth;
    }

    public String getProgramme() {
        return Programme;
    }

    public void setProgramme(String Programme) {
        this.Programme = Programme;
    }

    public String getAcademicYear() {
        return AcademicYear;
    }

    public void setAcademicYear(String AcademicYear) {
        this.AcademicYear = AcademicYear;
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
