package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.FileWriter.FileWriter;
import org.example.Helper.Clip;
import org.example.Helper.MyGraph;
import org.example.mathpower.MathPower;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Function;

public class MainAppController implements Initializable {

    public Button copyToClipboard;
    public Text ExtremaContent;
    public Text statusText;
    public Text RootsContent;
    public Text InflectionContent;
    public Text YIntersectionContent;
    public Button squaredButton;
    public Button HandleFirstDerivatives;
    public Button clearButton;
    public Text XIntersectionContent;
    private String _equation;

    private void SetEquation(String equation) { _equation = equation; }

    @FXML
    public Button clearStage;

    @FXML
    private LineChart<Double, Double> lineGraph;

    @FXML
    private TextField input;

    @FXML
    private Text errorText;

    private org.example.Helper.MyGraph mathsGraph;

    Stage stage;

    @Override
    public void initialize(final URL url, final ResourceBundle rb) { mathsGraph = new MyGraph(lineGraph, 10); }

    private void plotLine(Function<Double, Double> function) {
            mathsGraph.plotLine(function);
    }


    Clip clip = new Clip();



    @FXML
    private void handleSquaredButtonAction(final ActionEvent event) {

        try {
            String equation = input.getText();
            SetEquation(equation);
            MathPower math = new MathPower(equation);

            List<double[]> temp = math.GetRootPoints();
            List<double[]> extremaPoints = math.GetExtremaPoints();
            List<double[]> inflectionPoints = math.GetInflectionPoints();
            List<double[]> XIntersection = math.GetXIntersectionPoints(1, -10, 10);


            for (var data: temp)
                RootsContent.setText(data[0] +"/"+ data[1]);

            for (var data: extremaPoints)
                ExtremaContent.setText(data[0] +"/"+ data[1]);

            for (var data: inflectionPoints)
                InflectionContent.setText(data[0] +"/"+ data[1]);

                YIntersectionContent.setText("0.0/"+ math.GetYIntersectionPoint());

            for (var data: XIntersection)
                XIntersectionContent.setText(data[0] +"/"+ data[1]);

            plotLine(x -> Result(x, math.formulaData));

        } catch (Exception ex) {
            SetError();
        }

    }


    private void SetError() {

        input.setPromptText("You have to enter a valid equation!");
        input.setStyle("-fx-prompt-text-fill: #DC4849");
        input.setStyle("-fx-border-color: #DC4849");

    }

    private double Result(double x, List<double[]> formulaResults) {

        double temp = 0;

        for (var data: formulaResults)
            if (data[0] != 0 && data[1] != 0)
                temp += data[0] * (Math.pow(x, data[1]));
            else if (data[0] != 0 && data[1] == 0)
                temp += data[0];
            else
                temp += Math.pow(x, data[1]);

        return temp;

    }

    @FXML
    private void HandleFirstDerivativesButtonAction(final ActionEvent event) {


        try {
            MathPower math = new MathPower(_equation);
            plotLine(x -> Result(x, math.firstDerivative));


        } catch (Exception ex) { SetError(); }

    }

    @FXML
    private void HandleSecondDerivativesButtonAction(final ActionEvent event) {


        try {
            MathPower math = new MathPower(_equation);
            plotLine(x -> Result(x, math.secondDerivative));

        } catch (Exception ex) { SetError(); }

    }

    @FXML
    private void handleClearButtonAction(final ActionEvent event) {

        mathsGraph.clear();
        errorText.setText("");
        input.setText("");
        input.setStyle("-fx-border-color: transparent");
        ExtremaContent.setText("");
        RootsContent.setText("");
        InflectionContent.setText("");
        YIntersectionContent.setText("");
        XIntersectionContent.setText("");

    }
    @FXML
    private void handleStageClearButton(final ActionEvent event) {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }


    public void ExportToPdf(ActionEvent actionEvent) throws IOException {
        MathPower math = new MathPower(_equation);
        FileWriter fileWriter = new FileWriter(_equation,
                math.GetInflectionPoints(),
                math.GetExtremaPoints(),
                math.GetRootPoints(),
                math.GetYIntersectionPoint(),
                math.GetXIntersectionPoints(1,-10,10));
        String PathFileExtension = fileWriter.ChooseDirectory("Save your file","Equation","pdf", stage);
        fileWriter.WriteToPdf(fileWriter,PathFileExtension);
    }

    public void ExportToExcel(ActionEvent actionEvent) throws IOException {
        MathPower math = new MathPower(_equation);
        FileWriter fileWriter = new FileWriter(_equation,
                math.GetInflectionPoints(),
                math.GetExtremaPoints(),
                math.GetRootPoints(),
                math.GetYIntersectionPoint(),
                math.GetXIntersectionPoints(1,-10,10));
        String PathFileExtension = fileWriter.ChooseDirectory("Save your file","Equation","xlsx", stage);
        fileWriter.WriteToExcel(fileWriter,PathFileExtension);

    }


    public void ExtremaCopyToClipboard(ActionEvent actionEvent) {
        clip.CopyToClipboard(ExtremaContent.getText(), errorText);
    }

    public void RootsCopyToClipboard(ActionEvent actionEvent) {
        clip.CopyToClipboard(RootsContent.getText(), errorText);
    }

    public void InflectionCopyToClipboard(ActionEvent actionEvent) {
        clip.CopyToClipboard(InflectionContent.getText(), errorText);
    }

    public void YIntersectionCopyToClipboard(ActionEvent actionEvent) {
        clip.CopyToClipboard(YIntersectionContent.getText(), errorText);
    }

    public void XIntersectionCopyToClipboard(ActionEvent actionEvent) {
        clip.CopyToClipboard(XIntersectionContent.getText(), errorText);
    }
}