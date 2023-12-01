package com.fibertools.models.TaceViewerModels;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SupParams")
public class SupParams {

    private String otdr;
    private String otdrSN;
    private String module;
    private String moduleSN;
    private String other;
    private String software;
    private String supplier;

    // Getters
    @XmlElement(name = "OTDR")
    public String getOtdr() {
        return otdr;
    }

    @XmlElement(name = "OTDR_S_N")
    public String getOtdrSN() {
        return otdrSN;
    }

    @XmlElement(name = "module")
    public String getModule() {
        return module;
    }

    @XmlElement(name = "module_S_N")
    public String getModuleSN() {
        return moduleSN;
    }

    @XmlElement(name = "other")
    public String getOther() {
        return other;
    }

    @XmlElement(name = "software")
    public String getSoftware() {
        return software;
    }

    @XmlElement(name = "supplier")
    public String getSupplier() {
        return supplier;
    }

    // Setters
    public void setOtdr(String otdr) {
        this.otdr = otdr;
    }

    public void setOtdrSN(String otdrSN) {
        this.otdrSN = otdrSN;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public void setModuleSN(String moduleSN) {
        this.moduleSN = moduleSN;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public void setSoftware(String software) {
        this.software = software;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}
