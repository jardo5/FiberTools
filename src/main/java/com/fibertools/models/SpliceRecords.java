package com.fibertools.models;

public class SpliceRecords {

    int spliceId;
    String spliceName;
    String customerName;
    String spliceLocation;
    int spliceCount;
    String spliceNotes;
    String spliceAssignedJob;
    String spliceDate;

    public SpliceRecords(int spliceId, String spliceName, String customerName, String spliceLocation, int spliceCount, String spliceNotes, String spliceAssignedJob, String spliceDate) {
        this.spliceId = spliceId;
        this.spliceName = spliceName;
        this.customerName = customerName;
        this.spliceLocation = spliceLocation;
        this.spliceCount = spliceCount;
        this.spliceNotes = spliceNotes;
        this.spliceAssignedJob = spliceAssignedJob;
        this.spliceDate = spliceDate;
    }

    //Getters
    public int getSpliceId() {
        return spliceId;
    }

    public void setSpliceId(int spliceId) {
        this.spliceId = spliceId;
    }

    public String getSpliceName() {
        return spliceName;
    }

    public void setSpliceName(String spliceName) {
        this.spliceName = spliceName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSpliceLocation() {
        return spliceLocation;
    }

    public void setSpliceLocation(String spliceLocation) {
        this.spliceLocation = spliceLocation;
    }

    //Setters

    public int getSpliceCount() {
        return spliceCount;
    }

    public void setSpliceCount(int spliceCount) {
        this.spliceCount = spliceCount;
    }

    public String getSpliceNotes() {
        return spliceNotes;
    }

    public void setSpliceNotes(String spliceNotes) {
        this.spliceNotes = spliceNotes;
    }

    public String getSpliceAssignedJob() {
        return spliceAssignedJob;
    }

    public void setSpliceAssignedJob(String spliceAssignedJob) {
        this.spliceAssignedJob = spliceAssignedJob;
    }

    public String getSpliceDate() {
        return spliceDate;
    }

    public void setSpliceDate(String spliceDate) {
        this.spliceDate = spliceDate;
    }

    @Override
    public String toString() {
        return spliceName;
    }


}
