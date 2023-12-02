package com.fibertools.models.TaceViewerModels;

import javax.xml.bind.annotation.XmlElement;

public class SupParams {

    private String supplier; //Also known as Company to OTDR
    private String modelNumber;
    private String serialNumber;

    @XmlElement(name = "supplier")
    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    @XmlElement(name = "module")
    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    @XmlElement(name = "module_S_N")
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

}
