package edu.ijse.model;


public class FacultyProgram {
    
    private String facultyId;
    private String programId;
    private String Program;
    
    public  FacultyProgram (){}

    public FacultyProgram(String facultyId, String programId, String Program) {
        this.facultyId = facultyId;
        this.programId = programId;
        this.Program = Program;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getProgram() {
        return Program;
    }

    public void setProgram(String Program) {
        this.Program = Program;
    }
    
    
    
    
}
