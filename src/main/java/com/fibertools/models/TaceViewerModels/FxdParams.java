package com.fibertools.models.TaceViewerModels;

import javax.xml.bind.annotation.XmlElement;

public class FxdParams {
    private String dateTime;
    private String unit;
    private String wavelength;
    private int acquisitionOffset;
    private int acquisitionOffsetDistance;
    private int numberOfPulseWidthEntries;
    private String pulseWidth;
    private String sampleSpacing;
    private int numDataPoints;
    private String index;
    private String BC;
    private int numAverages;
    private String averagingTime;
    private double range;
    private int acquisitionRangeDistance;
    private int frontPanelOffset;
    private int noiseFloorLevel;
    private int noiseFloorScalingFactor;
    private int powerOffsetFirstPoint;
    private String lossThr;
    private String reflThr;
    private String EOTThr;
    private String traceType;
    private int X1;
    private int Y1;
    private int X2;
    private int Y2;
    private double resolution;

    // Getters and Setters
    @XmlElement(name = "date_time")
    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @XmlElement(name = "unit")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @XmlElement(name = "wavelength")
    public String getWavelength() {
        return wavelength;
    }

    public void setWavelength(String wavelength) {
        this.wavelength = wavelength;
    }

    @XmlElement(name = "acquisition_offset")
    public int getAcquisitionOffset() {
        return acquisitionOffset;
    }

    public void setAcquisitionOffset(int acquisitionOffset) {
        this.acquisitionOffset = acquisitionOffset;
    }

    @XmlElement(name = "acquisition_offset_distance")
    public int getAcquisitionOffsetDistance() {
        return acquisitionOffsetDistance;
    }

    public void setAcquisitionOffsetDistance(int acquisitionOffsetDistance) {
        this.acquisitionOffsetDistance = acquisitionOffsetDistance;
    }

    @XmlElement(name = "number_of_pulse_width_entries")
    public int getNumberOfPulseWidthEntries() {
        return numberOfPulseWidthEntries;
    }

    public void setNumberOfPulseWidthEntries(int numberOfPulseWidthEntries) {
        this.numberOfPulseWidthEntries = numberOfPulseWidthEntries;
    }

    @XmlElement(name = "pulse_width")
    public String getPulseWidth() {
        return pulseWidth;
    }

    public void setPulseWidth(String pulseWidth) {
        this.pulseWidth = pulseWidth;
    }

    @XmlElement(name = "sample_spacing")
    public String getSampleSpacing() {
        return sampleSpacing;
    }

    public void setSampleSpacing(String sampleSpacing) {
        this.sampleSpacing = sampleSpacing;
    }

    @XmlElement(name = "num_data_points")
    public int getNumDataPoints() {
        return numDataPoints;
    }

    public void setNumDataPoints(int numDataPoints) {
        this.numDataPoints = numDataPoints;
    }

    @XmlElement(name = "index")
    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    @XmlElement(name = "BC")
    public String getBC() {
        return BC;
    }

    public void setBC(String BC) {
        this.BC = BC;
    }

    @XmlElement(name = "num_averages")
    public int getNumAverages() {
        return numAverages;
    }

    public void setNumAverages(int numAverages) {
        this.numAverages = numAverages;
    }

    @XmlElement(name = "averaging_time")
    public String getAveragingTime() {
        return averagingTime;
    }

    public void setAveragingTime(String averagingTime) {
        this.averagingTime = averagingTime;
    }

    @XmlElement(name = "range")
    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    @XmlElement(name = "acquisition_range_distance")
    public int getAcquisitionRangeDistance() {
        return acquisitionRangeDistance;
    }

    public void setAcquisitionRangeDistance(int acquisitionRangeDistance) {
        this.acquisitionRangeDistance = acquisitionRangeDistance;
    }

    @XmlElement(name = "front_panel_offset")
    public int getFrontPanelOffset() {
        return frontPanelOffset;
    }

    public void setFrontPanelOffset(int frontPanelOffset) {
        this.frontPanelOffset = frontPanelOffset;
    }

    @XmlElement(name = "noise_floor_level")
    public int getNoiseFloorLevel() {
        return noiseFloorLevel;
    }

    public void setNoiseFloorLevel(int noiseFloorLevel) {
        this.noiseFloorLevel = noiseFloorLevel;
    }

    @XmlElement(name = "noise_floor_scaling_factor")
    public int getNoiseFloorScalingFactor() {
        return noiseFloorScalingFactor;
    }

    public void setNoiseFloorScalingFactor(int noiseFloorScalingFactor) {
        this.noiseFloorScalingFactor = noiseFloorScalingFactor;
    }

    @XmlElement(name = "power_offset_first_point")
    public int getPowerOffsetFirstPoint() {
        return powerOffsetFirstPoint;
    }

    public void setPowerOffsetFirstPoint(int powerOffsetFirstPoint) {
        this.powerOffsetFirstPoint = powerOffsetFirstPoint;
    }

    @XmlElement(name = "loss_thr")
    public String getLossThr() {
        return lossThr;
    }

    public void setLossThr(String lossThr) {
        this.lossThr = lossThr;
    }

    @XmlElement(name = "refl_thr")
    public String getReflThr() {
        return reflThr;
    }

    public void setReflThr(String reflThr) {
        this.reflThr = reflThr;
    }

    @XmlElement(name = "EOT_thr")
    public String getEOTThr() {
        return EOTThr;
    }

    public void setEOTThr(String EOTThr) {
        this.EOTThr = EOTThr;
    }

    @XmlElement(name = "trace_type")
    public String getTraceType() {
        return traceType;
    }

    public void setTraceType(String traceType) {
        this.traceType = traceType;
    }

    @XmlElement(name = "X1")
    public int getX1() {
        return X1;
    }

    public void setX1(int X1) {
        this.X1 = X1;
    }

    @XmlElement(name = "Y1")
    public int getY1() {
        return Y1;
    }

    public void setY1(int Y1) {
        this.Y1 = Y1;
    }

    @XmlElement(name = "X2")
    public int getX2() {
        return X2;
    }

    public void setX2(int X2) {
        this.X2 = X2;
    }

    @XmlElement(name = "Y2")
    public int getY2() {
        return Y2;
    }

    public void setY2(int Y2) {
        this.Y2 = Y2;
    }

    @XmlElement(name = "resolution")
    public double getResolution() {
        return resolution;
    }

    public void setResolution(double resolution) {
        this.resolution = resolution;
    }
}
