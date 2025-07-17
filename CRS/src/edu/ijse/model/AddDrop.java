
package edu.ijse.model;


public class AddDrop {
    private String FacultyId;
    private String Program;
    private String Status;
    
    public AddDrop(){}

    public AddDrop(String FacultyId, String Program, String Status) {
        this.FacultyId = FacultyId;
        this.Program = Program;
        this.Status = Status;
    }

    public String getFacultyId() {
        return FacultyId;
    }

    public void setFacultyId(String FacultyId) {
        this.FacultyId = FacultyId;
    }

    public String getProgram() {
        return Program;
    }

    public void setProgram(String Program) {
        this.Program = Program;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    
    
    
}
