package com.fibertools.models.TaceViewerModels;


import java.util.Map;

public class DataPts {

    private Map<String, Object> dataptsParams;
    private double maxBeforeOffset;
    private double minBeforeOffset;
    private int numDataPoints;
    private int numDataPoints2;
    private int numTraces;
    private double scalingFactor;

    // Getters
    public Map<String, Object> getDataptsParams() {
        return dataptsParams;
    }

    public double getMaxBeforeOffset() {
        return maxBeforeOffset;
    }

    public double getMinBeforeOffset() {
        return minBeforeOffset;
    }

    public int getNumDataPoints() {
        return numDataPoints;
    }

    public int getNumDataPoints2() {
        return numDataPoints2;
    }

    public int getNumTraces() {
        return numTraces;
    }

    public double getScalingFactor() {
        return scalingFactor;
    }

    // Setters
    public void setDataptsParams(Map<String, Object> dataptsParams) {
        this.dataptsParams = dataptsParams;
    }

    public void setMaxBeforeOffset(double maxBeforeOffset) {
        this.maxBeforeOffset = maxBeforeOffset;
    }

    public void setMinBeforeOffset(double minBeforeOffset) {
        this.minBeforeOffset = minBeforeOffset;
    }

    public void setNumDataPoints(int numDataPoints) {
        this.numDataPoints = numDataPoints;
    }

    public void setNumDataPoints2(int numDataPoints2) {
        this.numDataPoints2 = numDataPoints2;
    }

    public void setNumTraces(int numTraces) {
        this.numTraces = numTraces;
    }

    public void setScalingFactor(double scalingFactor) {
        this.scalingFactor = scalingFactor;
    }
}

