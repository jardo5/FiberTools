package com.fibertools.models.TaceViewerModels;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "KeyEvents")
public class KeyEvents {

    private List<Event> events;
    private Summary summary;
    private int numEvents;

    @XmlElementWrapper(name = "events")
    @XmlElement(name = "event")
    public List<Event> getEvents() {
        return events;
    }

    @XmlElement(name = "Summary")
    public Summary getSummary() {
        return summary;
    }

    @XmlElement(name = "num_events")
    public int getNumEvents() {
        return numEvents;
    }

    public static class Event {
        private String type;
        private Double distance;
        private Double slope;
        private Double spliceLoss;
        private Double reflLoss;
        private String comments;
        private Double endOfPrev;
        private Double startOfCurr;
        private Double endOfCurr;
        private Double startOfNext;
        private Double peak;

        @XmlElement(name = "type")
        public String getType() {
            return type;
        }

        @XmlElement(name = "distance")
        public Double getDistance() {
            return distance;
        }

        @XmlElement(name = "slope")
        public Double getSlope() {
            return slope;
        }

        @XmlElement(name = "splice_loss")
        public Double getSpliceLoss() {
            return spliceLoss;
        }

        @XmlElement(name = "refl_loss")
        public Double getReflLoss() {
            return reflLoss;
        }

        @XmlElement(name = "comments")
        public String getComments() {
            return comments;
        }

        @XmlElement(name = "end_of_prev")
        public Double getEndOfPrev() {
            return endOfPrev;
        }

        @XmlElement(name = "start_of_curr")
        public Double getStartOfCurr() {
            return startOfCurr;
        }

        @XmlElement(name = "end_of_curr")
        public Double getEndOfCurr() {
            return endOfCurr;
        }

        @XmlElement(name = "start_of_next")
        public Double getStartOfNext() {
            return startOfNext;
        }

        @XmlElement(name = "peak")
        public Double getPeak() {
            return peak;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setDistance(Double distance) {
            this.distance = distance;
        }

        public void setSlope(Double slope) {
            this.slope = slope;
        }

        public void setSpliceLoss(Double spliceLoss) {
            this.spliceLoss = spliceLoss;
        }

        public void setReflLoss(Double reflLoss) {
            this.reflLoss = reflLoss;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public void setEndOfPrev(Double endOfPrev) {
            this.endOfPrev = endOfPrev;
        }

        public void setStartOfCurr(Double startOfCurr) {
            this.startOfCurr = startOfCurr;
        }

        public void setEndOfCurr(Double endOfCurr) {
            this.endOfCurr = endOfCurr;
        }

        public void setStartOfNext(Double startOfNext) {
            this.startOfNext = startOfNext;
        }

        public void setPeak(Double peak) {
            this.peak = peak;
        }
    }

    public static class Summary {
        private Double totalLoss;
        private Double orl;
        private Double lossStart;
        private Double lossEnd;
        private Double orlStart;
        private Double orlFinish;

        @XmlElement(name = "total_loss")
        public Double getTotalLoss() {
            return totalLoss;
        }

        @XmlElement(name = "ORL")
        public Double getOrl() {
            return orl;
        }

        @XmlElement(name = "loss_start")
        public Double getLossStart() {
            return lossStart;
        }

        @XmlElement(name = "loss_end")
        public Double getLossEnd() {
            return lossEnd;
        }

        @XmlElement(name = "ORL_start")
        public Double getOrlStart() {
            return orlStart;
        }

        @XmlElement(name = "ORL_finish")
        public Double getOrlFinish() {
            return orlFinish;
        }

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

        public void setOrlFinish(Double orlFinish) {
            this.orlFinish = orlFinish;
        }
    }
}
