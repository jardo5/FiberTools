package com.fibertools.models.TaceViewerModels;

public class Cksum {

    private int checksum;
    private int checksum_ours;
    private boolean match;

    //Getters
    public int getChecksum() {
        return checksum;
    }

    public int getChecksum_ours() {
        return checksum_ours;
    }

    public boolean getMatch() {
        return match;
    }

    //Setters
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
