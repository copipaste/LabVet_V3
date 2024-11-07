/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitarios;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.List;
import javax.imageio.ImageIO;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author teito
 */
public class HtmlBuilder {

    private static final String HTML_OPEN = "<html>";
    private static final String HTML_CLOSE = "</html>";
    private static final String BODY_OPEN = "<body>";
    private static final String BODY_CLOSE = "</body>";

    public static String generateTableHelp(String title, String explication, String[] headers, List<String[]> data) {
        String table_headers_html = "";
        for (String header : headers) {
            table_headers_html += "<th style=\"border: 1px solid black; background-color: green; color: white; font-weight: bold;\">"
                    + header + "</th>";
        }

        String table_body_html = "";
        for (String[] element : data) {
            table_body_html += "<tr style=\"border: 1px solid black;\">";
            for (String value : element) {
                table_body_html += "<td style=\"border: 1px solid black ;\">" + value + "</td>";
            }
            table_body_html += "</tr>";
        }

        String html
                = "<center><h2>" + title + "</h2></center>"
                + "<p>" + explication + "</p>"
                + "<table style=\"border: 1px solid black;\" bgcolor=\"#CCCCCC\">"
                + "<thead>"
                + table_headers_html
                + "</thead>"
                + "<tbody>"
                + table_body_html
                + "</tbody>"
                + "</table>";

        return insertInHtml(html);
    }

    public static String generateTable(String title, String[] headers, List<String[]> data) {
        String table_headers_html = "";
        for (String header : headers) {
            table_headers_html += "<th style=\"border: 1px solid black;\">" + header + "</th>";
        }

        String table_body_html = "";
        for (String[] element : data) {
            table_body_html += "<tr style=\"border: 1px solid black;\">";
            for (String value : element) {
                table_body_html += "<td style=\"border: 1px solid black ;\">" + value + "</td>";
            }
            table_body_html += "</tr>";
        }

        String html
                = "<center><h2>" + title + "</h2></center>"
                + "<table style=\"border: 1px solid black;\" bgcolor=\"#CCCCCC\">"
                + "<thead>"
                + table_headers_html
                + "</thead>"
                + "<tbody>"
                + table_body_html
                + "</tbody>"
                + "</table>";

        return insertInHtml(html);
    }

    public static String generateText(String[] args) {
        String acumulative = "<div style=\"border: 1px solid black; padding: 10px;\">"
                + "<center><h2 style=\"color: green; font-size: 24px;\">" + args[0] + "</h2></center>";
        for (int i = 1; i < args.length; i++) {
            acumulative += "<center><h3>" + args[i] + "</h3></center>";
        }
        acumulative += "</div>";
        return insertInHtml(acumulative);
    }

    public static String generateCharBar(String[] args) {
        String acumulative = "<center><h2>" + args[0] + "</h2></center>";
        for (int i = 1; i < args.length; i++) {
            acumulative += "<center><h3>" + args[i] + "</h3></center>";
        }

        String chartScript = "<script src=\"https://cdn.jsdelivr.net/npm/chart.js\"></script>\n"
                + "<canvas id=\"myChart\"></canvas>\n"
                + "<script>\n"
                + "var ctx = document.getElementById('myChart').getContext('2d');\n"
                + "new Chart(ctx, {\n"
                + "type: 'bar',\n"
                + "data: {\n"
                + "labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],\n"
                + "datasets: [{\n"
                + "label: '# of Votes',\n"
                + "data: [12, 19, 3, 5, 2, 3],\n"
                + "borderWidth: 1\n"
                + "}]\n"
                + "},\n"
                + "options: {\n"
                + "scales: {\n"
                + "y: {\n"
                + "beginAtZero: true\n"
                + "}\n"
                + "}\n"
                + "}\n"
                + "});\n"
                + "</script>";

        return insertInHtml(acumulative);
    }

    public static String generateChartPieImage() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Product A", 10);
        dataset.setValue("Product B", 20);
        dataset.setValue("Product C", 30);

        JFreeChart chart = ChartFactory.createPieChart("Chart Title", dataset);

        // Guarda la imagen del gráfico en un archivo temporal
        try {
            Path imagePath = Files.createTempFile("chart", ".png");
            ChartUtils.saveChartAsPNG(imagePath.toFile(), chart, 600, 400);

            // Lee el archivo de imagen en bytes
            byte[] imageBytes = Files.readAllBytes(imagePath);

            // Convierte los bytes de la imagen a Base64
            String imageBase64 = Base64.getEncoder().encodeToString(imageBytes);
            
            System.out.println("Imagen del gráfico en formato Base64: " + imageBase64);
            return imageBase64;
            // Borra el archivo temporal
//            Files.deleteIfExists(imagePath);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String generateTableForSimpleData(String title, String[] headers, String[] data) {
        String acumulative = "";

        for (int i = 0; i < headers.length; i++) {
            acumulative
                    += "<tr>"
                    + "<td>" + headers[i] + "</td>"
                    + "<td>" + data[i] + "</td>"
                    + "</tr>";
        }

        String table
                = "<div align=\"center\">\n"
                + "<h2>" + title + "<br>\n"
                + "</h2>\n"
                + "</div>\n"
                + "<table width=\"250\"  border=\"1\" align=\"center\" cellpadding=\"2\" cellspacing=\"2\" bgcolor=\"#CCCCCC\">\n"
                + acumulative
                + "</table>";

        return insertInHtml(table);
    }

    private static String insertInHtml(String data) {
        return HTML_OPEN + BODY_OPEN + data + BODY_CLOSE + HTML_CLOSE;
    }
}
