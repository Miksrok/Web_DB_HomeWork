
package model;


public class Operation {
    
    private Integer id;
    private int patientNumber;
    private String operation;
    private boolean status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPatientNumber() {
        return patientNumber;
    }

    public void setPatientNumber(int patientNumber) {
        this.patientNumber = patientNumber;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
