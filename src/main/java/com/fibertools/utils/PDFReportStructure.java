package com.fibertools.utils;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import io.github.palexdev.mfxcore.utils.fx.SwingFXUtils;
import javafx.scene.chart.LineChart;
import javafx.scene.control.TableView;

import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class PDFReportStructure {

    String tableLabel = "General Parameters";

    String[] eventHeaders = {"Event, Distance, Splice Loss, Refl Loss"};

    public void createReport(String dest, Map<String, String> genParamsData, Map<String, String> getSupParamsData, Map<String, String> getFxdParamsData, Map<String, String> getSummaryData, LineChart<Number, Number> traceChart) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();

        PdfPTable sorFileNameTable = createLabelTable(genParamsData.get("File Name"));
        PdfPTable labelTable = createLabelTable(tableLabel);
        document.add(sorFileNameTable);
        //Add gap
        document.add(new Paragraph(" "));
        document.add(labelTable);



        PdfPTable parentTable = new PdfPTable(3);
        parentTable.setWidthPercentage(100);

        float[] columnWidths = {49f, 2f, 49f};
        float fixedHeight = 25;


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
        document.close();
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




    private Image getChartImage(LineChart<Number, Number> traceChart) throws IOException, BadElementException {
        WritableImage writableImage = new WritableImage((int)traceChart.getWidth(), (int)traceChart.getHeight());
        traceChart.snapshot(null, writableImage);
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(writableImage, null);
        ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", byteOutput);
        return Image.getInstance(byteOutput.toByteArray());
    }
}
