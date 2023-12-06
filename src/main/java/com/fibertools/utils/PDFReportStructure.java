package com.fibertools.utils;

import com.fibertools.models.TaceViewerModels.Sor;
import io.github.palexdev.mfxcore.utils.fx.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.LineChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.WritableImage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.PDType0Font;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;

public class PDFReportStructure {

    private static final float MARGIN = 50;
    private static final float TOP_MARGIN = 750;
    private static final float TABLE_WIDTH = 500;
    private static final float ROW_HEIGHT = 20;

    private void addTable(PDDocument document, PDPage page, PDPageContentStream contentStream, float margin, PDFont font, float fontSize, float startY, TableView<?> table) throws IOException {
        final float rowHeight = 20f;
        final float cellMargin = 2f;

        // Calculate the number of columns and their widths based on the table
        int numColumns = table.getColumns().size();
        float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
        float cellWidth = tableWidth / numColumns;

        drawColumnNames(contentStream, margin, font, fontSize, startY, table, cellWidth);

        // Draw the header row
        float nexty = startY;
        contentStream.moveTo(margin, nexty);
        contentStream.lineTo(margin + tableWidth, nexty);
        contentStream.stroke();

        // Draw the columns
        float nextx = margin;
        for (int i = 0; i < numColumns; i++) {
            contentStream.moveTo(nextx, nexty);
            contentStream.lineTo(nextx, nexty - rowHeight);
            contentStream.stroke();
            nextx += cellWidth;
        }
        nexty -= rowHeight;

        // Draw the rows
        for (Object item : table.getItems()) {
            nextx = margin + cellMargin;
            for (int i = 0; i < numColumns; i++) {
                TableColumn<?, ?> column = (TableColumn<?, ?>) table.getColumns().get(i);
                String propertyName = ((PropertyValueFactory) column.getCellValueFactory()).getProperty();
                String text = getPropertyValue(item, propertyName);

                contentStream.beginText();
                contentStream.setFont(font, fontSize);
                contentStream.newLineAtOffset(nextx, nexty);
                contentStream.showText(text);
                contentStream.endText();
                nextx += cellWidth;
            }
            nexty -= rowHeight;
        }
    }

    private String getPropertyValue(Object item, String propertyName) {
        try {
            Field field = item.getClass().getDeclaredField(propertyName);
            field.setAccessible(true);
            Object value = field.get(item);
            return value != null ? value.toString() : "";
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void createPDFReport(String filename, TableView<?> eventsTable, TableView<?> summaryTable, LineChart<Number, Number> traceChart) {
        PDDocument document = new PDDocument();
        try {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Add line chart snapshot
            drawChartSnapshot(document, contentStream, traceChart, MARGIN, MARGIN);

            // Table
            addTable(document, page, contentStream, MARGIN, PDType1Font.HELVETICA, 12, page.getMediaBox().getHeight() - MARGIN - 100, eventsTable);

            contentStream.close();

            // Save the PDF
            document.save(filename);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (document != null) {
                    document.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void drawColumnNames(PDPageContentStream contentStream, float margin, PDFont font, float fontSize, float startY, TableView<?> table, float cellWidth) throws IOException {
        final float cellMargin = 5f; // Adjust for padding within the cell
        float nextx = margin;
        float nexty = startY;

        contentStream.setFont(font, fontSize);
        for (int i = 0; i < table.getColumns().size(); i++) {
            TableColumn<?, ?> col = table.getColumns().get(i);
            String columnName = col.getText();

            contentStream.beginText();
            contentStream.newLineAtOffset(nextx + cellMargin, nexty);
            contentStream.showText(columnName);
            contentStream.endText();

            nextx += cellWidth;
        }
    }


    private void drawChartSnapshot(PDDocument document, PDPageContentStream contentStream, LineChart<Number, Number> chart, float xPosition, float yPosition) throws IOException {
        WritableImage snapshot = chart.snapshot(new SnapshotParameters(), null);
        BufferedImage bufferedChartImage = SwingFXUtils.fromFXImage(snapshot, null);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(bufferedChartImage, "png", out);
        byte[] chartBytes = out.toByteArray();
        PDImageXObject chartImage = PDImageXObject.createFromByteArray(document, chartBytes, "chart");


        contentStream.drawImage(chartImage, xPosition, yPosition, TABLE_WIDTH, ROW_HEIGHT * 10);
    }

    // Implement additional helper methods for drawing tables and chart as needed.
}
