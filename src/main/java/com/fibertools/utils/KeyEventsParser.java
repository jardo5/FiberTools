package com.fibertools.utils;

import com.fibertools.models.TaceViewerModels.KeyEvents;
import com.fibertools.models.TaceViewerModels.KeyEvents.Summary;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class KeyEventsParser {
    //Events
    public static List<KeyEvents.Event> parseAllKeyEvents(String filePath) {
        List<KeyEvents.Event> events = new ArrayList<>();
        int index = 1; // ONLY FOR EVENT TABLE NOT ON XML SHEET
        try {
            File inputFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("KeyEvents");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    for (int i = 1; i <= 30; i++) { // Adjust i <= __ for the max number of events
                        Element eventElement = (Element) eElement.getElementsByTagName("event_" + i).item(0);
                        if (eventElement != null) {
                            KeyEvents.Event event = new KeyEvents.Event();
                            event.setType(getElementTextContent(eventElement, "type"));
                            event.setDistance(parseDoubleOrDefault(getElementTextContent(eventElement, "distance"), 0.0));
                            event.setSlope(parseDoubleOrDefault(getElementTextContent(eventElement, "slope"), 0.0));
                            event.setSpliceLoss(parseDoubleOrDefault(getElementTextContent(eventElement, "splice_loss"), 0.0));
                            event.setReflLoss(parseDoubleOrDefault(getElementTextContent(eventElement, "refl_loss"), 0.0));
                            event.setComments(getElementTextContent(eventElement, "comments"));
                            event.setEndOfPrev(parseDoubleOrDefault(getElementTextContent(eventElement, "end_of_prev"), 0.0));
                            event.setStartOfCurr(parseDoubleOrDefault(getElementTextContent(eventElement, "start_of_curr"), 0.0));
                            event.setEndOfCurr(parseDoubleOrDefault(getElementTextContent(eventElement, "end_of_curr"), 0.0));
                            event.setStartOfNext(parseDoubleOrDefault(getElementTextContent(eventElement, "start_of_next"), 0.0));
                            event.setPeak(parseDoubleOrDefault(getElementTextContent(eventElement, "peak"), 0.0));

                            event.setIndex(index); // ONLY FOR EVENT TABLE NOT ON XML SHEET
                            events.add(event);
                            index++; // ONLY FOR EVENT TABLE NOT ON XML SHEET
                            //printEventToConsole(i, event);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return events;
    }

    private static String getElementTextContent(Element parentElement, String tagName) {
        NodeList nodeList = parentElement.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        } else {
            return "N/A";
        }
    }

    private static double parseDoubleOrDefault(String value, double defaultValue) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }


    private static void printEventToConsole(int eventNumber, KeyEvents.Event event) {
        System.out.println("Event " + eventNumber + ":");
        System.out.println("Type: " + event.getType());
        System.out.println("Distance: " + event.getDistance());
        System.out.println("Slope: " + event.getSlope());
        System.out.println("Splice Loss: " + event.getSpliceLoss());
        System.out.println("Reflection Loss: " + event.getReflLoss());
        System.out.println("Comments: " + event.getComments());
        System.out.println("End of Previous: " + event.getEndOfPrev());
        System.out.println("Start of Current: " + event.getStartOfCurr());
        System.out.println("End of Current: " + event.getEndOfCurr());
        System.out.println("Start of Next: " + event.getStartOfNext());
        System.out.println("Peak: " + event.getPeak());
        System.out.println();
    }
    //End of Events
    //Summary
    public static Summary parseSummary(String filePath) {
        Summary summary = null;
        try {
            File inputFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("KeyEvents");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    NodeList summaryList = eElement.getElementsByTagName("Summary");
                    if (summaryList.getLength() > 0) {
                        Node summaryNode = summaryList.item(0);
                        if (summaryNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element summaryElement = (Element) summaryNode;
                            summary = new Summary();
                            summary.setTotalLoss(parseDoubleOrDefault(getElementTextContent(summaryElement, "total_loss"), 0.0));
                            summary.setORL(parseDoubleOrDefault(getElementTextContent(summaryElement, "ORL"), 0.0));
                            summary.setLossStart(parseDoubleOrDefault(getElementTextContent(summaryElement, "loss_start"), 0.0));
                            summary.setLossEnd(parseDoubleOrDefault(getElementTextContent(summaryElement, "loss_end"), 0.0));
                            summary.setORLStart(parseDoubleOrDefault(getElementTextContent(summaryElement, "ORL_start"), 0.0));
                            summary.setORLEnd(parseDoubleOrDefault(getElementTextContent(summaryElement, "ORL_finish"), 0.0));

                            //printSummaryToConsole(summary);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return summary;
    }


     private static void printSummaryToConsole(Summary summary) {
        System.out.println("Summary:");
        System.out.println("Total Loss: " + summary.getTotalLoss());
        System.out.println("ORL: " + summary.getOrl());
        System.out.println("Loss Start: " + summary.getLossStart());
        System.out.println("Loss End: " + summary.getLossEnd());
        System.out.println("ORL Start: " + summary.getOrlStart());
        System.out.println("ORL End: " + summary.getOrlEnd());
        System.out.println();
    }
    //End of Summary
}
