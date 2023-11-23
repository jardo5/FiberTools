package com.fibertools.models;

public class Fibers {

    private int fiberId;
    private int fiberNumber;
    private double fiberDistance;
    private double fiberSpanLoss;
    private double fiberAvgLoss;
    private double fiberMaxLoss;
    private String fiberNotes;
    private int spliceId;

    public Fibers(int fiberId, int fiberNumber, double fiberDistance, double fiberSpanLoss, double fiberAvgLoss, double fiberMaxLoss, String fiberNotes, int spliceId) {
        this.fiberId = fiberId;
        this.fiberNumber = fiberNumber;
        this.fiberDistance = fiberDistance;
        this.fiberSpanLoss = fiberSpanLoss;
        this.fiberAvgLoss = fiberAvgLoss;
        this.fiberMaxLoss = fiberMaxLoss;
        this.fiberNotes = fiberNotes;
        this.spliceId = spliceId;
    }

    //Getters
    public int getFiberId() {
        return fiberId;
    }

    public int getFiberNumber() {
        return fiberNumber;
    }

    public double getFiberDistance() {
        return fiberDistance;
    }

    public double getFiberSpanLoss() {
        return fiberSpanLoss;
    }

    public double getFiberAvgLoss() {
        return fiberAvgLoss;
    }

    public double getFiberMaxLoss() {
        return fiberMaxLoss;
    }

    public String getFiberNotes() {
        return fiberNotes;
    }

    public int getSpliceId() {
        return spliceId;
    }

    //Setters

    public void setFiberId(int fiberId) {
        this.fiberId = fiberId;
    }

    public void setFiberNumber(int fiberNumber) {
        this.fiberNumber = fiberNumber;
    }

    public void setFiberDistance(double fiberDistance) {
        this.fiberDistance = fiberDistance;
    }

    public void setFiberSpanLoss(double fiberSpanLoss) {
        this.fiberSpanLoss = fiberSpanLoss;
    }

    public void setFiberAvgLoss(double fiberAvgLoss) {
        this.fiberAvgLoss = fiberAvgLoss;
    }

    public void setFiberMaxLoss(double fiberMaxLoss) {
        this.fiberMaxLoss = fiberMaxLoss;
    }

    public void setFiberNotes(String fiberNotes) {
        this.fiberNotes = fiberNotes;
    }

    public void setSpliceId(int spliceId) {
        this.spliceId = spliceId;
    }

    @Override
    public String toString() {
        return fiberId + " " + fiberDistance + " " + fiberSpanLoss + " " + fiberAvgLoss + " " + fiberMaxLoss + " " + fiberNotes;
    }


}
