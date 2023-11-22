package com.fibertools.models;

public class Jobs {
    private int jobId;
    private String jobName;

    public Jobs(int jobId, String jobName) {
        this.jobId = jobId;
        this.jobName = jobName;
    }

    //Getters
    public int getJobId() {
        return jobId;
    }

    //Setters
    public void setJobId(int id) {
        this.jobId = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @Override
    public String toString() {
        return jobName;
    }

}
