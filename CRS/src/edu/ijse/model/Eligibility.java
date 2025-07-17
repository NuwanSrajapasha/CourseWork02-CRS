
package edu.ijse.model;

public class Eligibility {
    
    private String studentId;
    private String facultyId;
    private String program;
    private String courseCode;
    private String courseTitle;
    private String status;
    
    
    public Eligibility(){}

    public Eligibility(String studentId, String facultyId, String program, String courseCode, String courseTitle, String status) {
        this.studentId = studentId;
        this.facultyId = facultyId;
        this.program = program;
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.status = status;
    }

   

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
}
