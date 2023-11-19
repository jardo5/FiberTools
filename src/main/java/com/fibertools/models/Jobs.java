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
    public String getJobName() {
        return jobName;
    }

    //Setters
    public void setJobId(int id) {
        this.jobId = id;
    }
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @Override
    public String toString() {
        return jobName;
    }

}
