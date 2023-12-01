package com.fibertools.models.TaceViewerModels;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "sor")
public class Sor {
    private String filename;
    private int format;
    private String version;
    private GenParams genParams;
    // private FxdParams fxdParams;
    //private KeyEvents keyEvents;

    @XmlElement(name = "filename")
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @XmlElement(name = "format")
    public int getFormat() {
        return format;
    }

    public void setFormat(int format) {
        this.format = format;
    }

    @XmlElement(name = "version")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }


    @XmlElement(name = "GenParams")
    public GenParams getGenParams() {
        return genParams;
    }

    public void setGenParams(GenParams genParams) {
        this.genParams = genParams;
    }


    /*
    @XmlElement(name = "FxdParams")
    public FxdParams getFxdParams() {
        return fxdParams;
    }

    public void setFxdParams(FxdParams fxdParams) {
        this.fxdParams = fxdParams;
    }


    @XmlElement(name = "KeyEvents")
    public KeyEvents getKeyEvents() {
        return keyEvents;
    }

    public void setKeyEvents(KeyEvents keyEvents) {
        this.keyEvents = keyEvents;
    }
    */
}