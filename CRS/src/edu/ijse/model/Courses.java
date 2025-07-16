
package edu.ijse.model;

public class Courses {
    
     private String courseId;
     private String courseCode;
     private String courseTitle;
     private String faculty;
     private String prerequisite;
     private int maxCapacity;
     private String facultyId;
     
     public Courses() {}

    public Courses(String courseId, String courseCode, String courseTitle, String faculty, String prerequisite, int maxCapacity, String facultyId) {
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.faculty = faculty;
        this.prerequisite = prerequisite;
        this.maxCapacity = maxCapacity;
        this.facultyId = facultyId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
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

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }
     
     
    
}
