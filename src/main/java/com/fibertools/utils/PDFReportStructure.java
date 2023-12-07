package com.fibertools.utils;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import io.github.palexdev.mfxcore.utils.fx.SwingFXUtils;
import javafx.scene.chart.LineChart;

import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class PDFReportStructure {

    String tableLabel = "General Parameters";

    public void createReport(String dest, Map<String, String> genParamsData, Map<String, String> getSupParamsData, Map<String, String> getFxdParamsData, Map<String, String> getSummaryData, LineChart<Number, Number> traceChart, List<Map<String, String>> getEventData) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();

        addHeader(document);
        document.add(new Paragraph(" ")); // Gap

        PdfPTable sorFileNameTable = createLabelTable(genParamsData.get("File Name"));
        PdfPTable labelTable = createLabelTable(tableLabel);
        document.add(sorFileNameTable);

        document.add(new Paragraph(" ")); // Gap
        document.add(labelTable);



        PdfPTable parentTable = new PdfPTable(3);
        parentTable.setWidthPercentage(100);

        float[] columnWidths = {49f, 2f, 49f};
        float fixedHeight = 20;


        parentTable.setWidths(columnWidths);

        // Add GenParams data to the left column
        PdfPCell genParamsCell = new PdfPCell();
        genParamsCell.setBorder(Rectangle.NO_BORDER);
        addGenParamsToPdf(genParamsCell, genParamsData, fixedHeight);
        parentTable.addCell(genParamsCell);

        // Add an empty cell as a gap
        PdfPCell gapCell = new PdfPCell();
        gapCell.setBorder(Rectangle.NO_BORDER);
        parentTable.addCell(gapCell);

        // Add SupParams and FxdParams data to the right column
        PdfPCell supFxdParamsCell = new PdfPCell();
        supFxdParamsCell.setBorder(Rectangle.NO_BORDER);
        addSupParamsAndFxdParamsToPdf(supFxdParamsCell, getSupParamsData, getFxdParamsData, fixedHeight);
        parentTable.addCell(supFxdParamsCell);

        document.add(parentTable);

        document.add(new Paragraph(" ")); // Add a small vertical space
        PdfPTable labelSummaryTable = createLabelTable("Summary");
        document.add(labelSummaryTable); // Add the summary label

        //Add Summary Table
        PdfPCell summaryCell = new PdfPCell();
        summaryCell.setBorder(Rectangle.NO_BORDER);
        addSummaryTableToPdf(summaryCell, getSummaryData, fixedHeight);
        PdfPTable summaryTableWrapper = new PdfPTable(1); // Wrapper table with a single cell
        summaryTableWrapper.setWidthPercentage(100);
        summaryTableWrapper.addCell(summaryCell);
        document.add(summaryTableWrapper);

        //Add Trace Chart
        if (traceChart != null) {
            try {
                Image chartImage = getChartImage(traceChart);
                chartImage.scaleToFit(500, 500);
                document.add(chartImage);
            } catch (BadElementException | IOException ex) {
                System.err.println("Error adding chart image: " + ex.getMessage());
            }
        }

        //Add Event Table
        document.add(new Paragraph(" ")); // Add a small vertical space
        PdfPTable labelEventTable = createLabelTable("Event Table");
        document.add(labelEventTable); // Add the event table label

        PdfPCell eventCell = new PdfPCell();
        eventCell.setBorder(Rectangle.NO_BORDER);
        addEventTableToPdf(eventCell, getEventData, fixedHeight);
        PdfPTable eventTableWrapper = new PdfPTable(1); // Wrapper table with a single cell
        eventTableWrapper.setWidthPercentage(100);
        eventTableWrapper.addCell(eventCell);
        document.add(eventTableWrapper);


        document.close();
    }

    private void addHeader(Document document) throws IOException, DocumentException {
        // Load logo image
        Image logo = Image.getInstance(getClass().getResource("/media/fiberToolsLogo.png").getPath());
        logo.setAbsolutePosition(document.getPageSize().getWidth() - 60f, document.getPageSize().getHeight() - 60f); // Adjust position
        logo.scaleAbsolute(50, 50); // Adjust size as needed

        // Add logo to document
        document.add(logo);

        // Add title and current date/time
        Paragraph title = new Paragraph("Fiber Report", new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD));
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Paragraph dateTime = new Paragraph(sdf.format(new Date()), new Font(Font.FontFamily.HELVETICA, 12));
        dateTime.setAlignment(Element.ALIGN_CENTER);
        document.add(dateTime);
    }


    private PdfPTable createLabelTable(String labelText) {
        PdfPTable labelTable = new PdfPTable(1);
        PdfPCell labelCell = new PdfPCell(new Phrase(labelText, new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
        labelCell.setBorder(Rectangle.NO_BORDER);
        labelCell.setBackgroundColor(new BaseColor(220, 220, 220)); // Optional background color

        labelCell.setHorizontalAlignment(Element.ALIGN_CENTER);

        labelTable.addCell(labelCell);
        return labelTable;
    }

    private void addGenParamsToPdf(PdfPCell cell, Map<String, String> genParams, float fixedHeight) throws DocumentException {
        PdfPTable genParamsTable = new PdfPTable(2);
        genParamsTable.setWidthPercentage(100);
        float[] columnWidths = {1, 1};

        // Define the order of the rows
        String[] order = {"Fiber ID", "Cable ID", "Location A", "Location B", "Wavelength", "Operator", "Comments"};

        // Loop through the order array and add the cells accordingly
        for (String key : order) {
            PdfPCell keyCell = new PdfPCell(new Phrase(key));
            PdfPCell valueCell = new PdfPCell(new Phrase(genParams.getOrDefault(key, "")));

            keyCell.setFixedHeight(fixedHeight);
            valueCell.setFixedHeight(fixedHeight);
            keyCell.setPaddingLeft(5);

            genParamsTable.addCell(keyCell);
            genParamsTable.addCell(valueCell);
        }

        genParamsTable.setWidths(columnWidths);
        cell.addElement(genParamsTable);
    }


    private void addSupParamsAndFxdParamsToPdf(PdfPCell cell, Map<String, String> supParams, Map<String, String> fxdParams, float fixedHeight) throws DocumentException {
        PdfPTable supFxdParamsTable = new PdfPTable(2);
        supFxdParamsTable.setWidthPercentage(100);
        float[] columnWidths = {1, 1};

        PdfPCell companyCell = new PdfPCell(new Phrase("Company"));
        PdfPCell companyValueCell = new PdfPCell(new Phrase(supParams.getOrDefault("Company", "")));
        companyCell.setFixedHeight(fixedHeight);
        companyValueCell.setFixedHeight(fixedHeight);

        PdfPCell rangeCell = new PdfPCell(new Phrase("Range"));
        PdfPCell rangeValueCell = new PdfPCell(new Phrase(fxdParams.getOrDefault("Range", "")));
        rangeCell.setFixedHeight(fixedHeight);
        rangeValueCell.setFixedHeight(fixedHeight);

        PdfPCell pulseWidthCell = new PdfPCell(new Phrase("Pulse Width"));
        PdfPCell pulseWidthValueCell = new PdfPCell(new Phrase(fxdParams.getOrDefault("Pulse Width", "")));
        pulseWidthCell.setFixedHeight(fixedHeight);
        pulseWidthValueCell.setFixedHeight(fixedHeight);

        PdfPCell dateTimeCell = new PdfPCell(new Phrase("Date/Time"));
        PdfPCell dateTimeValueCell = new PdfPCell(new Phrase(fxdParams.getOrDefault("Date/Time", "")));
        dateTimeCell.setFixedHeight(fixedHeight);
        dateTimeValueCell.setFixedHeight(fixedHeight);

        PdfPCell modelNumberCell = new PdfPCell(new Phrase("OTDR Model Number"));
        PdfPCell modelNumberValueCell = new PdfPCell(new Phrase(supParams.getOrDefault("OTDR Model Number", "")));
        modelNumberCell.setFixedHeight(fixedHeight);
        modelNumberValueCell.setFixedHeight(fixedHeight);

        PdfPCell serialNumberCell = new PdfPCell(new Phrase("OTDR Serial Number"));
        PdfPCell serialNumberValueCell = new PdfPCell(new Phrase(supParams.getOrDefault("OTDR Serial Number", "")));
        serialNumberCell.setFixedHeight(fixedHeight);
        serialNumberValueCell.setFixedHeight(fixedHeight);

        // Add cells to table in desired order
        supFxdParamsTable.addCell(companyCell);
        supFxdParamsTable.addCell(companyValueCell);
        supFxdParamsTable.addCell(rangeCell);
        supFxdParamsTable.addCell(rangeValueCell);
        supFxdParamsTable.addCell(pulseWidthCell);
        supFxdParamsTable.addCell(pulseWidthValueCell);
        supFxdParamsTable.addCell(dateTimeCell);
        supFxdParamsTable.addCell(dateTimeValueCell);
        supFxdParamsTable.addCell(modelNumberCell);
        supFxdParamsTable.addCell(modelNumberValueCell);
        supFxdParamsTable.addCell(serialNumberCell);
        supFxdParamsTable.addCell(serialNumberValueCell);

        PdfPCell emptyCell1 = new PdfPCell(new Phrase(""));
        PdfPCell emptyCell2 = new PdfPCell(new Phrase(""));
        emptyCell1.setFixedHeight(fixedHeight);
        emptyCell2.setFixedHeight(fixedHeight);

        supFxdParamsTable.addCell(emptyCell1);
        supFxdParamsTable.addCell(emptyCell2);

        supFxdParamsTable.setWidths(columnWidths);
        cell.addElement(supFxdParamsTable);
    }

    private void addSummaryTableToPdf(PdfPCell cell, Map<String, String> summaryData, float fixedHeight) throws DocumentException {
        PdfPTable summaryTable = new PdfPTable(2); // 2 columns for key-value pairs
        summaryTable.setWidthPercentage(100);
        float[] columnWidths = {1, 1};

        for (Map.Entry<String, String> entry : summaryData.entrySet()) {
            PdfPCell keyCell = new PdfPCell(new Phrase(entry.getKey()));
            PdfPCell valueCell = new PdfPCell(new Phrase(entry.getValue()));

            keyCell.setFixedHeight(fixedHeight);
            valueCell.setFixedHeight(fixedHeight);

            summaryTable.addCell(keyCell);
            summaryTable.addCell(valueCell);
        }

        summaryTable.setWidths(columnWidths);
        cell.addElement(summaryTable);
    }

    private Image getChartImage(LineChart<Number, Number> traceChart) throws IOException, BadElementException {
        WritableImage writableImage = new WritableImage((int)traceChart.getWidth(), (int)traceChart.getHeight());
        traceChart.snapshot(null, writableImage);
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(writableImage, null);
        ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", byteOutput);
        return Image.getInstance(byteOutput.toByteArray());
    }

    private void addEventTableToPdf(PdfPCell cell, List<Map<String, String>> eventData, float fixedHeight) throws DocumentException {
        // Define the desired order of the columns
        String[] columnOrder = {"Event", "Distance", "Splice Loss", "Reflection Loss"};
        PdfPTable eventTable = new PdfPTable(columnOrder.length); // Create a table with the number of columns
        eventTable.setWidthPercentage(100);

        // Adding headers in the desired order
        for (String header : columnOrder) {
            eventTable.addCell(new PdfPCell(new Phrase(header, new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD))));
        }

        // Adding row data in the desired order
        for (Map<String, String> row : eventData) {
            for (String key : columnOrder) {
                String value = row.getOrDefault(key, ""); // Get the value for each column
                eventTable.addCell(new PdfPCell(new Phrase(value)));
            }
        }

        cell.addElement(eventTable);
    }
}
