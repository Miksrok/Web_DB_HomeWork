package model;

public class ListOfProcedures {

    private Integer id;
    private int patientNumber;
    private String procedure;
    private boolean procedureStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPatientNumber() {
        return patientNumber;
    }

    public void setPatientNumber(int clinicalRecord) {
        this.patientNumber = clinicalRecord;
    }

   
    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public boolean isProcedureStatus() {
        return procedureStatus;
    }

    public void setProcedureStatus(boolean procedureStatus) {
        this.procedureStatus = procedureStatus;
    }

}
