package com.fibertools.models.TaceViewerModels;

import com.fibertools.utils.MeasurementConversions;

import javax.xml.bind.annotation.*;

import java.util.List;


@XmlRootElement(name = "KeyEvents")
public class KeyEvents {

    private int numEvents;
    private Summary summary;

    @XmlElement(name = "num_events")
    public int getNumEvents() {
        return numEvents;
    }

    public void setNumEvents(int numEvents) {
        this.numEvents = numEvents;
    }


    @XmlElement(name = "Summary")
    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    // Inner Event class
    public static class Event {
        private int index; // ONLY FOR EVENT TABLE NOT ON XML SHEET
        private String type;
        private double distance;
        private double slope;
        private double spliceLoss;
        private double reflLoss;
        private String comments;
        private double endOfPrev;
        private double startOfCurr;
        private double endOfCurr;
        private double startOfNext;
        private double peak;

        // Getters
        public int getIndex() {
            return index;
        }

        public String getType() {
            return type;
        }

        public double getDistance() {
            return distance;
        }

        public double getSlope() {
            return slope;
        }

        public double getSpliceLoss() {
            return spliceLoss;
        }

        public double getReflLoss() {
            return reflLoss;
        }

        public String getComments() {
            return comments;
        }

        public double getEndOfPrev() {
            return endOfPrev;
        }

        public double getStartOfCurr() {
            return startOfCurr;
        }

        public double getEndOfCurr() {
            return endOfCurr;
        }

        public double getStartOfNext() {
            return startOfNext;
        }

        public double getPeak() {
            return peak;
        }

        // Setters
        public void setIndex(int index) {
            this.index = index;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setDistance(double distance) {
            this.distance = MeasurementConversions.KMtoFT(distance); // Convert to feet
        }

        public void setSlope(double slope) {
            this.slope = slope;
        }

        public void setSpliceLoss(double spliceLoss) {
            this.spliceLoss = spliceLoss;
        }

        public void setReflLoss(double reflLoss) {
            this.reflLoss = reflLoss;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public void setEndOfPrev(double endOfPrev) {
            this.endOfPrev = endOfPrev;
        }

        public void setStartOfCurr(double startOfCurr) {
            this.startOfCurr = startOfCurr;
        }

        public void setEndOfCurr(double endOfCurr) {
            this.endOfCurr = endOfCurr;
        }

        public void setStartOfNext(double startOfNext) {
            this.startOfNext = startOfNext;
        }

        public void setPeak(double peak) {
            this.peak = MeasurementConversions.KMtoFT(peak); // Convert to feet
        }
    }


    // Inner Summary class
    public static class Summary {
        private double totalLoss;
        private double ORL;
        private double lossStart;
        private double lossEnd;
        private double ORLStart;
        private double ORLEnd;

        // Getters
        public double getTotalLoss() {
            return totalLoss;
        }

        public double getOrl() {
            return ORL;
        }

        public double getLossStart() {
            return lossStart;
        }

        public double getLossEnd() {
            return lossEnd;
        }

        public double getOrlStart() {
            return ORLStart;
        }

        public double getOrlEnd() {
            return ORLEnd;
        }

        // Setters
        public void setTotalLoss(double totalLoss) {
            this.totalLoss = totalLoss;
        }

        public void setORL(double ORL) {
            this.ORL = ORL;
        }

        public void setLossStart(double lossStart) {
            this.lossStart = lossStart;
        }

        public void setLossEnd(double lossEnd) {
            this.lossEnd = MeasurementConversions.KMtoFT(lossEnd); // Convert to feet
        }

        public void setORLStart(double ORLStart) {
            this.ORLStart = MeasurementConversions.KMtoFT(ORLStart); // Convert to feet
        }

        public void setORLEnd(double ORLEnd) {
            this.ORLEnd = MeasurementConversions.KMtoFT(ORLEnd); // Convert to feet
        }
    }

}
