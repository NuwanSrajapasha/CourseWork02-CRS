
package edu.ijse.model;

public class EnrollDetails {
    
  //  student_id	c_code	c_title	faculty_id	
  
    private String StudentId;
    private String program;
    private String CourseCode;
    private String CourseTitle;
    private String FacultyId;
    
    public EnrollDetails(){}

    public EnrollDetails(String StudentId,String program, String CourseCode, String CourseTitle, String FacultyId) {
        this.StudentId = StudentId;
        this.program = program;
        this.CourseCode = CourseCode;
        this.CourseTitle = CourseTitle;
        this.FacultyId = FacultyId;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    
    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String StudentId) {
        this.StudentId = StudentId;
    }

    public String getCourseCode() {
        return CourseCode;
    }

    public void setCourseCode(String CourseCode) {
        this.CourseCode = CourseCode;
    }

    public String getCourseTitle() {
        return CourseTitle;
    }

    public void setCourseTitle(String CourseTitle) {
        this.CourseTitle = CourseTitle;
    }

    public String getFacultyId() {
        return FacultyId;
    }

    public void setFacultyId(String FacultyId) {
        this.FacultyId = FacultyId;
    }
    
    
    
    
}
