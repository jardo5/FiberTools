package com.fibertools.models;

public class Employees {

    private int employeeId;
    private String employeeName;
    private String employeePhone;
    private String employeeEmail;
    private String employeeAddress;
    private String employeePosition;
    private double employeeRate;
    private String employeeAssignedJob;

    public Employees(int employeeId, String employeeName, String employeePhone, String employeeEmail, String employeeAddress, String employeePosition, double employeeRate, String employeeAssignedJob) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeePhone = employeePhone;
        this.employeeEmail = employeeEmail;
        this.employeeAddress = employeeAddress;
        this.employeePosition = employeePosition;
        this.employeeRate = employeeRate;
        this.employeeAssignedJob = employeeAssignedJob;
    }

    //Getters
    public int getEmployeeId() {
        return employeeId;
    }
    public String getEmployeeName() {
        return employeeName;
    }
    public String getEmployeePhone() {
        return employeePhone;
    }
    public String getEmployeeEmail() {
        return employeeEmail;
    }
    public String getEmployeeAddress() {
        return employeeAddress;
    }
    public String getEmployeePosition() {
        return employeePosition;
    }
    public double getEmployeeRate() {
        return employeeRate;
    }
    public String getEmployeeAssignedJob() {
        return employeeAssignedJob;
    }

    //Setters
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }
    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }
    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }
    public void setEmployeePosition(String employeePosition) {
        this.employeePosition = employeePosition;
    }
    public void setEmployeeRate(double employeeRate) {
        this.employeeRate = employeeRate;
    }
    public void setEmployeeAssignedJob(String employeeAssignedJob) {
        this.employeeAssignedJob = employeeAssignedJob;
    }

    @Override
    public String toString() {
        return employeeName;
    }

}
