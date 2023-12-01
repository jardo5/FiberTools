package com.fibertools.models.TaceViewerModels;

import javax.xml.bind.annotation.XmlElement;

public class GenParams {
    private String language;
    private String cableID;
    private String fiberID;
    private String fiberType;
    private String wavelength;
    private String locationA;
    private String locationB;
    private String cableCodeFiberType;
    private String buildCondition;
    private String userOffset;
    private String userOffsetDistance;
    private String operator;
    private String comments;

    // Getters and Setters

    @XmlElement(name = "language")
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @XmlElement(name = "cable_ID")
    public String getCableID() {
        return cableID;
    }

    public void setCableID(String cableID) {
        this.cableID = cableID;
    }

    @XmlElement(name = "fiber_ID")
    public String getFiberID() {
        return fiberID;
    }

    public void setFiberID(String fiberID) {
        this.fiberID = fiberID;
    }

    @XmlElement(name = "fiber_type")
    public String getFiberType() {
        return fiberType;
    }

    public void setFiberType(String fiberType) {
        this.fiberType = fiberType;
    }

    @XmlElement(name = "wavelength")
    public String getWavelength() {
        return wavelength;
    }

    public void setWavelength(String wavelength) {
        this.wavelength = wavelength;
    }

    @XmlElement(name = "location_A")
    public String getLocationA() {
        return locationA;
    }

    public void setLocationA(String locationA) {
        this.locationA = locationA;
    }

    @XmlElement(name = "location_B")
    public String getLocationB() {
        return locationB;
    }

    public void setLocationB(String locationB) {
        this.locationB = locationB;
    }

    @XmlElement(name = "cable_code_fiber_type")
    public String getCableCodeFiberType() {
        return cableCodeFiberType;
    }

    public void setCableCodeFiberType(String cableCodeFiberType) {
        this.cableCodeFiberType = cableCodeFiberType;
    }

    @XmlElement(name = "build_condition")
    public String getBuildCondition() {
        return buildCondition;
    }

    public void setBuildCondition(String buildCondition) {
        this.buildCondition = buildCondition;
    }

    @XmlElement(name = "user_offset")
    public String getUserOffset() {
        return userOffset;
    }

    public void setUserOffset(String userOffset) {
        this.userOffset = userOffset;
    }

    @XmlElement(name = "user_offset_distance")
    public String getUserOffsetDistance() {
        return userOffsetDistance;
    }

    public void setUserOffsetDistance(String userOffsetDistance) {
        this.userOffsetDistance = userOffsetDistance;
    }

    @XmlElement(name = "operator")
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @XmlElement(name = "comments")
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
