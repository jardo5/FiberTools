package com.fibertools.models.TaceViewerModels;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

@XmlRootElement(name = "DataPts")
public class DataPts {

    private DataptsParams dataptsParams;
    private double maxBeforeOffset;
    private double minBeforeOffset;
    private int numDataPoints;
    private int numDataPoints2;
    private int numTraces;
    private double scalingFactor;

    // Getters
    @XmlElement(name = "_datapts_params")
    public DataptsParams getDataptsParams() {
        return dataptsParams;
    }

    @XmlElement(name = "max_before_offset")
    public double getMaxBeforeOffset() {
        return maxBeforeOffset;
    }

    @XmlElement(name = "min_before_offset")
    public double getMinBeforeOffset() {
        return minBeforeOffset;
    }

    @XmlElement(name = "num_data_points")
    public int getNumDataPoints() {
        return numDataPoints;
    }

    @XmlElement(name = "num_data_points_2")
    public int getNumDataPoints2() {
        return numDataPoints2;
    }

    @XmlElement(name = "num_traces")
    public int getNumTraces() {
        return numTraces;
    }

    @XmlElement(name = "scaling_factor")
    public double getScalingFactor() {
        return scalingFactor;
    }

    // Setters
    public void setDataptsParams(DataptsParams dataptsParams) {
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

    public static class DataptsParams {
        private int xscaling;
        private String offset;

        @XmlElement(name = "xscaling")
        public int getXscaling() {
            return xscaling;
        }

        @XmlElement(name = "offset")
        public String getOffset() {
            return offset;
        }

        public void setXscaling(int xscaling) {
            this.xscaling = xscaling;
        }

        public void setOffset(String offset) {
            this.offset = offset;
        }
    }
}
