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

    String[] eventHeaders = {"Event, Distance, Splice Loss, Refl Loss"};

    public void createReport(String dest, Map<String, String> genParamsData, Map<String, String> getSupParamsData, Map<String, String> getFxdParamsData, Map<String, String> getSummaryData, LineChart<Number, Number> traceChart) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();

        PdfPTable parentTable = new PdfPTable(3); // Two columns for tables
        parentTable.setWidthPercentage(100); // Make sure it spans the whole page width


        // Define the widths for the columns
        float[] columnWidths = {49f, 2f, 49f}; // Adjust the widths as needed

        // Set the relative widths for the columns in the parent table
        parentTable.setWidths(columnWidths);

        // Add GenParams data to the left column
        PdfPCell genParamsCell = new PdfPCell();
        genParamsCell.setBorder(Rectangle.NO_BORDER);
        addGenParamsToPdf(genParamsCell, genParamsData);
        parentTable.addCell(genParamsCell);

        // Add an empty cell as a gap (borderless table)
        PdfPCell gapCell = new PdfPCell();
        gapCell.setBorder(Rectangle.NO_BORDER);
        parentTable.addCell(gapCell);

        // Add SupParams and FxdParams data to the right column
        PdfPCell supFxdParamsCell = new PdfPCell();
        supFxdParamsCell.setBorder(Rectangle.NO_BORDER);
        addSupParamsAndFxdParamsToPdf(supFxdParamsCell, getSupParamsData, getFxdParamsData);
        parentTable.addCell(supFxdParamsCell);

        document.add(parentTable);
        document.close();
    }





    private void addGenParamsToPdf(PdfPCell cell, Map<String, String> genParams) throws DocumentException {
        PdfPTable genParamsTable = new PdfPTable(2); // Two columns: Key, Value
        genParamsTable.setWidthPercentage(100); // Make sure it spans the whole cell width
        float[] columnWidths = {1, 1}; // Adjust these values as needed

        for (Map.Entry<String, String> entry : genParams.entrySet()) {
            PdfPCell keyCell = new PdfPCell(new Phrase(entry.getKey()));
            PdfPCell valueCell = new PdfPCell(new Phrase(entry.getValue()));

            // Set the width for the key cell explicitly
            keyCell.setFixedHeight(20); // Adjust the height as needed
            keyCell.setPaddingLeft(5); // Add some left padding for better alignment

            // Add the cells to the table
            genParamsTable.addCell(keyCell);
            genParamsTable.addCell(valueCell);
        }

        // Set the relative column widths for the table
        genParamsTable.setWidths(columnWidths);

        cell.addElement(genParamsTable);
    }

    private void addSupParamsAndFxdParamsToPdf(PdfPCell cell, Map<String, String> supParams, Map<String, String> fxdParams) throws DocumentException {
        PdfPTable supFxdParamsTable = new PdfPTable(2); // Two columns: Key, Value
        supFxdParamsTable.setWidthPercentage(100); // Make sure it spans the whole cell width
        float[] columnWidths = {1, 1}; // Adjust these values as needed

        for (Map.Entry<String, String> entry : supParams.entrySet()) {
            PdfPCell keyCell = new PdfPCell(new Phrase(entry.getKey()));
            PdfPCell valueCell = new PdfPCell(new Phrase(entry.getValue()));

            // Set the width for the key cell explicitly
            keyCell.setFixedHeight(20); // Adjust the height as needed
            keyCell.setPaddingLeft(5); // Add some left padding for better alignment

            // Add the cells to the table
            supFxdParamsTable.addCell(keyCell);
            supFxdParamsTable.addCell(valueCell);
        }

        for (Map.Entry<String, String> entry : fxdParams.entrySet()) {
            PdfPCell keyCell = new PdfPCell(new Phrase(entry.getKey()));
            PdfPCell valueCell = new PdfPCell(new Phrase(entry.getValue()));

            // Set the width for the key cell explicitly
            keyCell.setFixedHeight(20); // Adjust the height as needed
            keyCell.setPaddingLeft(5); // Add some left padding for better alignment

            // Add the cells to the table
            supFxdParamsTable.addCell(keyCell);
            supFxdParamsTable.addCell(valueCell);
        }

        // Set the relative column widths for the table
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
