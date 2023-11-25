package com.fibertools.models.TaceViewerModels;

import java.util.Map;

public class KeyEvents {

    private Map<String, Event> events;
    private Summary summary;

    public static class Event {
        private String comments;
        private Double distance;
        private Double slope;
        private Double startOfNext;
        private Double reflLoss;
        private Double startOfCurr;
        private Double endOfPrev;
        private Double endOfCurr;
        private String type;
        private Double peak;
        private Double spliceLoss;

        //Getters
        public String getComments() {
            return comments;
        }

        public Double getDistance() {
            return distance;
        }

        public Double getSlope() {
            return slope;
        }

        public Double getStartOfNext() {
            return startOfNext;
        }

        public Double getReflLoss() {
            return reflLoss;
        }

        public Double getStartOfCurr() {
            return startOfCurr;
        }

        public Double getEndOfPrev() {
            return endOfPrev;
        }

        public Double getEndOfCurr() {
            return endOfCurr;
        }

        public String getType() {
            return type;
        }

        public Double getPeak() {
            return peak;
        }

        public Double getSpliceLoss() {
            return spliceLoss;
        }

        //Setters
        public void setComments(String comments) {
            this.comments = comments;
        }

        public void setDistance(Double distance) {
            this.distance = distance;
        }

        public void setSlope(Double slope) {
            this.slope = slope;
        }

        public void setStartOfNext(Double startOfNext) {
            this.startOfNext = startOfNext;
        }

        public void setReflLoss(Double reflLoss) {
            this.reflLoss = reflLoss;
        }

        public void setStartOfCurr(Double startOfCurr) {
            this.startOfCurr = startOfCurr;
        }

        public void setEndOfPrev(Double endOfPrev) {
            this.endOfPrev = endOfPrev;
        }

        public void setEndOfCurr(Double endOfCurr) {
            this.endOfCurr = endOfCurr;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setPeak(Double peak) {
            this.peak = peak;
        }

        public void setSpliceLoss(Double spliceLoss) {
            this.spliceLoss = spliceLoss;
        }
    }

    public static class Summary {
        private Double totalLoss;
        private Double orl;
        private Double lossStart;
        private Double lossEnd;
        private Double orlStart;
        private Double orlEnd;

        //Getters
        public Double getTotalLoss() {
            return totalLoss;
        }

        public Double getOrl() {
            return orl;
        }

        public Double getLossStart() {
            return lossStart;
        }

        public Double getLossEnd() {
            return lossEnd;
        }

        public Double getOrlStart() {
            return orlStart;
        }

        public Double getOrlEnd() {
            return orlEnd;
        }

        //Setters
        public void setTotalLoss(Double totalLoss) {
            this.totalLoss = totalLoss;
        }

        public void setOrl(Double orl) {
            this.orl = orl;
        }

        public void setLossStart(Double lossStart) {
            this.lossStart = lossStart;
        }

        public void setLossEnd(Double lossEnd) {
            this.lossEnd = lossEnd;
        }

        public void setOrlStart(Double orlStart) {
            this.orlStart = orlStart;
        }

        public void setOrlEnd(Double orlEnd) {
            this.orlEnd = orlEnd;
        }

    }
}
