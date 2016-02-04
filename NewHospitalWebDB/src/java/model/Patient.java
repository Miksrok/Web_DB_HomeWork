package model;

public class Patient {

    private Integer clinicalRecord;
    private String name;
    private String diagnosis;
    private int department;
    private int primaryDoctor;

    public Integer getClinicalRecord() {
        return clinicalRecord;
    }

    public void setClinicalRecord(Integer clinicalRecord) {
        this.clinicalRecord = clinicalRecord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public int getPrimaryDoctor() {
        return primaryDoctor;
    }

    public void setPrimaryDoctor(int primaryDoctor) {
        this.primaryDoctor = primaryDoctor;
    }

}
