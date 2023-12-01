package com.fibertools.models.TaceViewerModels;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "FxdParams")
public class FxdParams {

    private String dateTime;
    private String unit;
    private String wavelength;
    private int acquisitionOffset;
    private double acquisitionOffsetDistance;
    private int numberOfPulseWidthEntries;
    private String pulseWidth;
    private String sampleSpacing;
    private int numDataPoints;
    private String index;
    private String bc;
    private int numAverages;
    private String averagingTime;
    private double range;
    private double acquisitionRangeDistance;
    private int frontPanelOffset;
    private int noiseFloorLevel;
    private int noiseFloorScalingFactor;
    private int powerOffsetFirstPoint;
    private String lossThr;
    private String reflThr;
    private String eotThr;
    private String traceType;
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private double resolution;

    @XmlElement(name = "date_time")
    public String getDateTime() {
        return dateTime;
    }

    @XmlElement(name = "unit")
    public String getUnit() {
        return unit;
    }

    @XmlElement(name = "wavelength")
    public String getWavelength() {
        return wavelength;
    }

    @XmlElement(name = "acquisition_offset")
    public int getAcquisitionOffset() {
        return acquisitionOffset;
    }

    @XmlElement(name = "acquisition_offset_distance")
    public double getAcquisitionOffsetDistance() {
        return acquisitionOffsetDistance;
    }

    @XmlElement(name = "number_of_pulse_width_entries")
    public int getNumberOfPulseWidthEntries() {
        return numberOfPulseWidthEntries;
    }

    @XmlElement(name = "pulse_width")
    public String getPulseWidth() {
        return pulseWidth;
    }

    @XmlElement(name = "sample_spacing")
    public String getSampleSpacing() {
        return sampleSpacing;
    }

    @XmlElement(name = "num_data_points")
    public int getNumDataPoints() {
        return numDataPoints;
    }

    @XmlElement(name = "index")
    public String getIndex() {
        return index;
    }

    @XmlElement(name = "BC")
    public String getBc() {
        return bc;
    }

    @XmlElement(name = "num_averages")
    public int getNumAverages() {
        return numAverages;
    }

    @XmlElement(name = "averaging_time")
    public String getAveragingTime() {
        return averagingTime;
    }

    @XmlElement(name = "range")
    public double getRange() {
        return range;
    }

    @XmlElement(name = "acquisition_range_distance")
    public double getAcquisitionRangeDistance() {
        return acquisitionRangeDistance;
    }

    @XmlElement(name = "front_panel_offset")
    public int getFrontPanelOffset() {
        return frontPanelOffset;
    }

    @XmlElement(name = "noise_floor_level")
    public int getNoiseFloorLevel() {
        return noiseFloorLevel;
    }

    @XmlElement(name = "noise_floor_scaling_factor")
    public int getNoiseFloorScalingFactor() {
        return noiseFloorScalingFactor;
    }

    @XmlElement(name = "power_offset_first_point")
    public int getPowerOffsetFirstPoint() {
        return powerOffsetFirstPoint;
    }

    @XmlElement(name = "loss_thr")
    public String getLossThr() {
        return lossThr;
    }

    @XmlElement(name = "refl_thr")
    public String getReflThr() {
        return reflThr;
    }

    @XmlElement(name = "EOT_thr")
    public String getEotThr() {
        return eotThr;
    }

    @XmlElement(name = "trace_type")
    public String getTraceType() {
        return traceType;
    }

    @XmlElement(name = "X1")
    public int getX1() {
        return x1;
    }

    @XmlElement(name = "Y1")
    public int getY1() {
        return y1;
    }

    @XmlElement(name = "X2")
    public int getX2() {
        return x2;
    }

    @XmlElement(name = "Y2")
    public int getY2() {
        return y2;
    }

    @XmlElement(name = "resolution")
    public double getResolution() {
        return resolution;
    }

    // Setters
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setWavelength(String wavelength) {
        this.wavelength = wavelength;
    }

    public void setAcquisitionOffset(int acquisitionOffset) {
        this.acquisitionOffset = acquisitionOffset;
    }

    public void setAcquisitionOffsetDistance(double acquisitionOffsetDistance) {
        this.acquisitionOffsetDistance = acquisitionOffsetDistance;
    }

    public void setNumberOfPulseWidthEntries(int numberOfPulseWidthEntries) {
        this.numberOfPulseWidthEntries = numberOfPulseWidthEntries;
    }

    public void setPulseWidth(String pulseWidth) {
        this.pulseWidth = pulseWidth;
    }

    public void setSampleSpacing(String sampleSpacing) {
        this.sampleSpacing = sampleSpacing;
    }

    public void setNumDataPoints(int numDataPoints) {
        this.numDataPoints = numDataPoints;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public void setBc(String bc) {
        this.bc = bc;
    }

    public void setNumAverages(int numAverages) {
        this.numAverages = numAverages;
    }

    public void setAveragingTime(String averagingTime) {
        this.averagingTime = averagingTime;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public void setAcquisitionRangeDistance(double acquisitionRangeDistance) {
        this.acquisitionRangeDistance = acquisitionRangeDistance;
    }

    public void setFrontPanelOffset(int frontPanelOffset) {
        this.frontPanelOffset = frontPanelOffset;
    }

    public void setNoiseFloorLevel(int noiseFloorLevel) {
        this.noiseFloorLevel = noiseFloorLevel;
    }

    public void setNoiseFloorScalingFactor(int noiseFloorScalingFactor) {
        this.noiseFloorScalingFactor = noiseFloorScalingFactor;
    }

    public void setPowerOffsetFirstPoint(int powerOffsetFirstPoint) {
        this.powerOffsetFirstPoint = powerOffsetFirstPoint;
    }

    public void setLossThr(String lossThr) {
        this.lossThr = lossThr;
    }

    public void setReflThr(String reflThr) {
        this.reflThr = reflThr;
    }

    public void setEotThr(String eotThr) {
        this.eotThr = eotThr;
    }

    public void setTraceType(String traceType) {
        this.traceType = traceType;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public void setResolution(double resolution) {
        this.resolution = resolution;
    }

}
