package com.fibertools.models.TaceViewerModels;

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
        public void setType(String type) {
            this.type = type;
        }

        public void setDistance(double distance) {
            this.distance = distance;
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
            this.peak = peak;
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

        public double getORL() {
            return ORL;
        }

        public double getLossStart() {
            return lossStart;
        }

        public double getLossEnd() {
            return lossEnd;
        }

        public double getORLStart() {
            return ORLStart;
        }

        public double getORLEnd() {
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
            this.lossEnd = lossEnd;
        }

        public void setORLStart(double ORLStart) {
            this.ORLStart = ORLStart;
        }

        public void setORLEnd(double ORLEnd) {
            this.ORLEnd = ORLEnd;
        }
    }

}
