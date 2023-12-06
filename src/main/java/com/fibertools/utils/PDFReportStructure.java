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

    private PdfPTable createLabelTable(String labelText) {
        PdfPTable labelTable = new PdfPTable(1);
        PdfPCell labelCell = new PdfPCell(new Phrase(labelText, new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
        labelCell.setBorder(Rectangle.NO_BORDER);
        labelCell.setBackgroundColor(new BaseColor(220, 220, 220)); // Optional background color

        labelCell.setHorizontalAlignment(Element.ALIGN_CENTER);

        labelTable.addCell(labelCell);
        return labelTable;
    }

    String[] eventHeaders = {"Event, Distance, Splice Loss, Refl Loss"};

    public void createReport(String dest, Map<String, String> genParamsData, Map<String, String> getSupParamsData, Map<String, String> getFxdParamsData, Map<String, String> getSummaryData, LineChart<Number, Number> traceChart) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();

        PdfPTable labelTable = createLabelTable(tableLabel);
        document.add(labelTable);


        PdfPTable parentTable = new PdfPTable(3);
        parentTable.setWidthPercentage(100);

        float[] columnWidths = {49f, 2f, 49f};


        parentTable.setWidths(columnWidths);

        // Add GenParams data to the left column
        PdfPCell genParamsCell = new PdfPCell();
        genParamsCell.setBorder(Rectangle.NO_BORDER);
        addGenParamsToPdf(genParamsCell, genParamsData);
        parentTable.addCell(genParamsCell);

        // Add an empty cell as a gap
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
        genParamsTable.setWidthPercentage(100);
        float[] columnWidths = {1, 1};

        for (Map.Entry<String, String> entry : genParams.entrySet()) {
            PdfPCell keyCell = new PdfPCell(new Phrase(entry.getKey()));
            PdfPCell valueCell = new PdfPCell(new Phrase(entry.getValue()));


            keyCell.setFixedHeight(20);
            keyCell.setPaddingLeft(5);

            genParamsTable.addCell(keyCell);
            genParamsTable.addCell(valueCell);
        }


        genParamsTable.setWidths(columnWidths);

        cell.addElement(genParamsTable);
    }

    private void addSupParamsAndFxdParamsToPdf(PdfPCell cell, Map<String, String> supParams, Map<String, String> fxdParams) throws DocumentException {
        PdfPTable supFxdParamsTable = new PdfPTable(2);
        supFxdParamsTable.setWidthPercentage(100);
        float[] columnWidths = {1, 1};

        for (Map.Entry<String, String> entry : supParams.entrySet()) {
            PdfPCell keyCell = new PdfPCell(new Phrase(entry.getKey()));
            PdfPCell valueCell = new PdfPCell(new Phrase(entry.getValue()));

            keyCell.setFixedHeight(20);
            keyCell.setPaddingLeft(5);

            supFxdParamsTable.addCell(keyCell);
            supFxdParamsTable.addCell(valueCell);
        }

        for (Map.Entry<String, String> entry : fxdParams.entrySet()) {
            PdfPCell keyCell = new PdfPCell(new Phrase(entry.getKey()));
            PdfPCell valueCell = new PdfPCell(new Phrase(entry.getValue()));


            keyCell.setFixedHeight(20);
            keyCell.setPaddingLeft(5);

            supFxdParamsTable.addCell(keyCell);
            supFxdParamsTable.addCell(valueCell);
        }


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
