package com.fibertools.utils;

import com.fibertools.models.TaceViewerModels.Sor;
import io.github.palexdev.mfxcore.utils.fx.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.LineChart;
import javafx.scene.control.TableView;
import javafx.scene.image.WritableImage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PDFReportStructure {

    private static final float MARGIN = 50;
    private static final float TOP_MARGIN = 750;
    private static final float TABLE_WIDTH = 500;
    private static final float ROW_HEIGHT = 20;

    public void createPDFReport(String filename, TableView<?> eventsTable, TableView<?> summaryTable, LineChart<Number, Number> traceChart, Sor sor) {
        PDDocument document = new PDDocument();
        try {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Add line chart snapshot
            drawChartSnapshot(document, contentStream, traceChart, MARGIN, MARGIN);

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
