package org.example.FileWriter;


import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import rst.pdfbox.layout.elements.Document;
import rst.pdfbox.layout.elements.Paragraph;
import rst.pdfbox.layout.text.NewLine;
import org.example.Helper.Formatter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;


public class FileWriter {

    private String equation;
    private List<double[]> InflectionPoints;
    private List<double[]> ExtremaPoints;
    private List<double[]>RootPoints;
    private double YIntersectionPoint;
    private List<double[]> XIntersectionPoints;

    /**
     * @param equation setting the equation
     * @param InflectionPoints setting the InflectionPoints
     * @param ExtremaPoints setting the ExtremaPoints
     * @param RootPoints setting the RootPoints
     * @param YIntersectionPoint setting the YIntersectionPoint
     * @param XIntersectionPoints setting the XIntersectionPoints
     */
    public FileWriter(String equation,
                      List<double[]> InflectionPoints,
                      List<double[]> ExtremaPoints,
                      List<double[]>RootPoints,
                      double YIntersectionPoint,
                      List<double[]> XIntersectionPoints) {

        this.equation = equation;
        this.InflectionPoints = InflectionPoints;
        this.ExtremaPoints = ExtremaPoints;
        this.RootPoints = RootPoints;
        this.YIntersectionPoint = YIntersectionPoint;
        this.XIntersectionPoints = XIntersectionPoints;

    }

    /**
     * @param curve is the object that you want to write to excel
     * @param filePath filepath where the file should be saved
     * @throws IOException
     */
    public void WriteToExcel(FileWriter curve, String filePath) throws IOException {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        var firstRow = sheet.createRow(0);

        var secondRow = sheet.createRow(1);

        var equation = firstRow.createCell(0);
        equation.setCellValue("Equation");

        var setEquation = secondRow.createCell(0);
        setEquation.setCellValue(curve.equation);

        var RootPoints = firstRow.createCell(1);
        RootPoints.setCellValue("RootPoints");

        var setRootPoints = secondRow.createCell(1);
        setRootPoints.setCellValue(Formatter.StringFormat(curve.RootPoints));

        var ExtremaPoints = firstRow.createCell(2);
        ExtremaPoints.setCellValue("ExtremaPoints");

        var setExtremaPoints = secondRow.createCell(2);
        setExtremaPoints.setCellValue(Formatter.StringFormat(curve.ExtremaPoints));

        var InflectionPoints = firstRow.createCell(3);
        InflectionPoints.setCellValue("InflectionPoints");

        var setInflectionPoints = secondRow.createCell(3);
        setInflectionPoints.setCellValue(Formatter.StringFormat(curve.InflectionPoints));

        var YIntersectionPoint = firstRow.createCell(4);
        YIntersectionPoint.setCellValue("YIntersectionPoint");

        var setYIntersectionPoint = secondRow.createCell(4);
        setYIntersectionPoint.setCellValue(curve.YIntersectionPoint);

        var XIntersectionPoints = firstRow.createCell(5);
        XIntersectionPoints.setCellValue("XIntersectionPoints");

        var setXIntersectionPoints = secondRow.createCell(5);
        setXIntersectionPoints.setCellValue(Formatter.StringFormat(curve.XIntersectionPoints));

        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {

            workbook.write(outputStream);

        } catch (IOException e) {

            System.out.println(e);

        }

    }


    /**
     * @param curve is the object that you want to write to pdf
     * @param filepath filepath where the file should be saved
     * @throws IOException
     */
    public void WriteToPdf(FileWriter curve, String filepath) throws IOException {
        Document document = new Document(40,60,40,60);

        Paragraph paragraph = new Paragraph();
        NewLine linebreak = new NewLine();
        paragraph.addText("Equation: " + curve.equation,20, PDType1Font.HELVETICA);
        paragraph.add(linebreak);
        paragraph.addText("InflectionPoints: " + Formatter.StringFormat(curve.InflectionPoints),20,PDType1Font.HELVETICA);
        paragraph.add(linebreak);
        paragraph.addText("ExtremaPoints: " + Formatter.StringFormat(curve.ExtremaPoints),20,PDType1Font.HELVETICA);
        paragraph.add(linebreak);
        paragraph.addText("RootPoints: " + Formatter.StringFormat(curve.RootPoints),20,PDType1Font.HELVETICA);
        paragraph.add(linebreak);
        paragraph.addText("YIntersectionPoint: " + YIntersectionPoint,20,PDType1Font.HELVETICA);
        paragraph.add(linebreak);
        paragraph.addText("XIntersectionPoints: " + Formatter.StringFormat(curve.XIntersectionPoints),20,PDType1Font.HELVETICA);
        document.add(paragraph);

        try {
            OutputStream outputStream = new FileOutputStream(filepath);
            document.save(outputStream);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * @param title sets the title for filechooser window
     * @param initName sets the inital name for the file
     * @param format sets the file format
     * @param stage sets the window from where the filechooser opens
     * @return
     */
    public String ChooseDirectory(String title, String initName, String format, Window stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.setInitialFileName(initName);
        var Path = fileChooser.showSaveDialog(stage).getPath();
        if (format.equals("pdf")) {

            return Path + ".pdf";

        } else {

            return Path + ".xlsx";

        }

    }

}
