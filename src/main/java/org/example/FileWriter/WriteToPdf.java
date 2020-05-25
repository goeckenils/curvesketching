package sample.FileWriter;


import java.io.FileOutputStream;
import java.io.OutputStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import rst.pdfbox.layout.elements.Document;
import rst.pdfbox.layout.elements.Paragraph;
import rst.pdfbox.layout.text.NewLine;


import java.io.IOException;
import java.util.List;

public class WriteToPdf {

    private String equation;
    private List<double[]> InflectionPoints;
    private List<double[]> ExtremaPoints;
    private List<double[]>RootPoints;
    private double YIntersectionPoint;
    private List<double[]> XIntersectionPoints;

    public WriteToPdf(String equation,
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


}
