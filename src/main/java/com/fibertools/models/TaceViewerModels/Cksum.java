package com.fibertools.models.TaceViewerModels;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Cksum")
public class Cksum {

    private int checksum;
    private int checksum_ours;
    private boolean match;

    // Getters
    @XmlElement(name = "checksum")
    public int getChecksum() {
        return checksum;
    }

    @XmlElement(name = "checksum_ours")
    public int getChecksum_ours() {
        return checksum_ours;
    }

    @XmlElement(name = "match")
    public boolean isMatch() {
        return match;
    }

    // Setters
    public void setChecksum(int checksum) {
        this.checksum = checksum;
    }

    public void setChecksum_ours(int checksum_ours) {
        this.checksum_ours = checksum_ours;
    }

    public void setMatch(boolean match) {
        this.match = match;
    }
}
