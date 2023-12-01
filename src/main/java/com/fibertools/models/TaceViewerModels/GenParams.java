package com.fibertools.models.TaceViewerModels;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GenParams")
public class GenParams {

    private String buildCondition;
    private String cableId;
    private String cableCodeFiberType;
    private String comments;
    private String fiberId;
    private String fiberType;
    private String language;
    private String locationA;
    private String locationB;
    private String operator;
    private String userOffset;
    private String userOffsetDistance;
    private String wavelength;

    // Getters

    @XmlElement(name = "language")
    public String getLanguage() {
        return language;
    }

    @XmlElement(name = "cable_ID")
    public String getCableId() {
        return cableId;
    }

    @XmlElement(name = "cable_code_fiber_type")
    public String getCableCodeFiberType() {
        return cableCodeFiberType;
    }

    @XmlElement(name = "comments")
    public String getComments() {
        return comments;
    }

    @XmlElement(name = "fiber_ID")
    public String getFiberId() {
        return fiberId;
    }

    @XmlElement(name = "fiber_type")
    public String getFiberType() {
        return fiberType;
    }

    @XmlElement(name = "location_A")
    public String getLocationA() {
        return locationA;
    }

    @XmlElement(name = "location_B")
    public String getLocationB() {
        return locationB;
    }

    @XmlElement(name = "operator")
    public String getOperator() {
        return operator;
    }

    @XmlElement(name = "user_offset")
    public String getUserOffset() {
        return userOffset;
    }

    @XmlElement(name = "user_offset_distance")
    public String getUserOffsetDistance() {
        return userOffsetDistance;
    }

    @XmlElement(name = "wavelength")
    public String getWavelength() {
        return wavelength;
    }

    @XmlElement(name = "build_condition")
    public String getBuildCondition() {
        return buildCondition;
    }

    // Setters
    public void setBuildCondition(String buildCondition) {
        this.buildCondition = buildCondition;
    }

    public void setCableId(String cableId) {
        this.cableId = cableId;
    }

    public void setCableCodeFiberType(String cableCodeFiberType) {
        this.cableCodeFiberType = cableCodeFiberType;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setFiberId(String fiberId) {
        this.fiberId = fiberId;
    }

    public void setFiberType(String fiberType) {
        this.fiberType = fiberType;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setLocationA(String locationA) {
        this.locationA = locationA;
    }

    public void setLocationB(String locationB) {
        this.locationB = locationB;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void setUserOffset(String userOffset) {
        this.userOffset = userOffset;
    }

    public void setUserOffsetDistance(String userOffsetDistance) {
        this.userOffsetDistance = userOffsetDistance;
    }

    public void setWavelength(String wavelength) {
        this.wavelength = wavelength;
    }
}

