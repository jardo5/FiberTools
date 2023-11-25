package com.fibertools.models.TaceViewerModels;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.Map;

public class TraceData {
    private Cksum cksum;
    private DataPts dataPts;
    private FxdParams fxdParams;
    private GenParams genParams;
    private KeyEvents keyEvents;
    private SupParams supParams;
    private Map<String, Object> blocks;
    private String filename;
    private int format;
    private Map<String, Object> mapblock;
    private String version;
    private Boolean debug;
    private String dump;

    //Getters
    public Cksum getCksum() {
        return cksum;
    }

    public DataPts getDataPts() {
        return dataPts;
    }

    public FxdParams getFxdParams() {
        return fxdParams;
    }

    public GenParams getGenParams() {
        return genParams;
    }

    public KeyEvents getKeyEvents() {
        return keyEvents;
    }

    public SupParams getSupParams() {
        return supParams;
    }

    public Map<String, Object> getBlocks() {
        return blocks;
    }

    public String getFilename() {
        return filename;
    }

    public int getFormat() {
        return format;
    }

    public Map<String, Object> getMapblock() {
        return mapblock;
    }

    public String getVersion() {
        return version;
    }

    public Boolean getDebug() {
        return debug;
    }

    public String getDump() {
        return dump;
    }

    //Setters
    public void setCksum(Cksum cksum) {
        this.cksum = cksum;
    }

    public void setDataPts(DataPts dataPts) {
        this.dataPts = dataPts;
    }

    public void setFxdParams(FxdParams fxdParams) {
        this.fxdParams = fxdParams;
    }

    public void setGenParams(GenParams genParams) {
        this.genParams = genParams;
    }

    public void setKeyEvents(KeyEvents keyEvents) {
        this.keyEvents = keyEvents;
    }

    public void setSupParams(SupParams supParams) {
        this.supParams = supParams;
    }

    public void setBlocks(Map<String, Object> blocks) {
        this.blocks = blocks;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setFormat(int format) {
        this.format = format;
    }

    public void setMapblock(Map<String, Object> mapblock) {
        this.mapblock = mapblock;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setDebug(Boolean debug) {
        this.debug = debug;
    }

    public void setDump(String dump) {
        this.dump = dump;
    }

}
