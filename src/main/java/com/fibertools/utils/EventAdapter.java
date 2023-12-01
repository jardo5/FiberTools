package com.fibertools.utils;

import com.fibertools.models.TaceViewerModels.KeyEvents.Event;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class EventAdapter extends XmlAdapter<Object, Map<String, Event>> {

    @Override
    public Map<String, Event> unmarshal(Object v) throws Exception {
        // Implement the logic to convert XML to Map of Events
        Map<String, Event> eventsMap = new HashMap<>();

        XMLStreamReader reader = (XMLStreamReader) v;
        while (reader.hasNext()) {
            if (reader.isStartElement()) {
                String elementName = reader.getLocalName();
                if (elementName.startsWith("event_")) {
                    Event event = new Event();
                    // Populate the event object from the XML content
                    readEventContent(reader, event);
                    eventsMap.put(elementName, event);
                }
            }
            reader.next();
        }

        return eventsMap;
    }

    @Override
    public Object marshal(Map<String, Event> v) throws Exception {
        // Marshaling not supported in this example
        throw new UnsupportedOperationException("Marshaling Map of Events to XML not supported");
    }

    private void readEventContent(XMLStreamReader reader, Event event) throws XMLStreamException {
        while (reader.hasNext()) {
            if (reader.isStartElement()) {
                String elementName = reader.getLocalName();
                reader.next(); // Move to the element's text content

                switch (elementName) {
                    case "type":
                        event.setType(reader.getText());
                        break;
                    case "distance":
                        event.setDistance(Double.parseDouble(reader.getText()));
                        break;
                    case "slope":
                        event.setSlope(Double.parseDouble(reader.getText()));
                        break;
                    case "splice_loss":
                        event.setSpliceLoss(Double.parseDouble(reader.getText()));
                        break;
                    case "refl_loss":
                        event.setReflLoss(Double.parseDouble(reader.getText()));
                        break;
                    case "comments":
                        event.setComments(reader.getText());
                        break;
                    case "end_of_prev":
                        event.setEndOfPrev(Double.parseDouble(reader.getText()));
                        break;
                    case "start_of_curr":
                        event.setStartOfCurr(Double.parseDouble(reader.getText()));
                        break;
                    case "end_of_curr":
                        event.setEndOfCurr(Double.parseDouble(reader.getText()));
                        break;
                    case "start_of_next":
                        event.setStartOfNext(Double.parseDouble(reader.getText()));
                        break;
                    case "peak":
                        event.setPeak(Double.parseDouble(reader.getText()));
                        break;
                    default:
                        break;
                }
            } else if (reader.isEndElement() && reader.getLocalName().startsWith("event_")) {
                // End of the current event element
                break;
            }
            reader.next();
        }
    }
}
