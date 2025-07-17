
package edu.ijse.model;

public class Courses {
    
    
     private String courseCode;
     private String courseTitle;
     private String faculty;
     private String prerequisite;
     private String maxCapacity;
     private String facultyId;
     private String programme;
     
     public Courses() {}

    public Courses( String programme,String courseCode, String courseTitle, String faculty, String prerequisite, String maxCapacity, String facultyId) {
        
        this.programme = programme;
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.faculty = faculty;
        this.prerequisite = prerequisite;
        this.maxCapacity = maxCapacity;
        this.facultyId = facultyId;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    
    

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(String prerequisite) {
        this.prerequisite = prerequisite;
    }

    public String getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(String maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }
     
     
    
}
