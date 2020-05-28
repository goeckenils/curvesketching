package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.FileWriter.FileWriter;
import org.example.Helper.Clip;
import org.example.Helper.Formatter;
import org.example.Helper.MyGraph;
import org.example.mathpower.MathPower;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Function;

public class MainAppController implements Initializable {
    public Text ExtremaTitle;
    public Text ExtremaContent;
    public Text RootsContent;
    public Text InflectionContent;
    public Text YIntersectionContent;
    public Button squaredButton;
    public Button HandleFirstDerivatives;
    public Button clearButton;
    public Text RootsTitle;
    public Text InflectionTitle;
    public Text YIntersectionTitle;
    public Button InflectionClip;
    public Button IntersectionClip;
    public Button RootsClip;
    public Button ExtremaClip;
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


    /**
     * @param event Writes the data to the GUI
     */
    @FXML
    private void handleSquaredButtonAction(final ActionEvent event) {
        ClearStage(false);

        try {
            String equation = input.getText();
            SetEquation(equation);
            MathPower math = new MathPower(equation);

            List<double[]> rootPoints = math.GetRootPoints();
            List<double[]> extremaPoints = math.GetExtremaPoints();
            List<double[]> inflectionPoints = math.GetInflectionPoints();

            Formatter.SetState(extremaPoints,ExtremaContent,ExtremaTitle, ExtremaClip);

            Formatter.SetState(rootPoints,RootsContent,RootsTitle, RootsClip);

            Formatter.SetState(inflectionPoints,InflectionContent,InflectionTitle, InflectionClip);

            YIntersectionContent.setText("0.0/"+ math.GetYIntersectionPoint());

            plotLine(x -> Result(x, math.formulaData));

        } catch (Exception ex) {
            SetError();
        }

    }


    /**
     * Set the border to red
     */
    private void SetError() {

        input.setPromptText("You have to enter a valid equation!");
        input.setStyle("-fx-prompt-text-fill: #DC4849");
        input.setStyle("-fx-border-color: #DC4849");

    }

    /**
     * @param x
     * @param formulaResults
     * @return
     */
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

    /**
     * @param event generates the plotline for the graph
     */
    @FXML
    private void HandleFirstDerivativesButtonAction(final ActionEvent event) {


        try {
            MathPower math = new MathPower(_equation);
            plotLine(x -> Result(x, math.firstDerivative));


        } catch (Exception ex) { SetError(); }

    }

    /**
     * @param event
     */
    @FXML
    private void HandleSecondDerivativesButtonAction(final ActionEvent event) {


        try {
            MathPower math = new MathPower(_equation);
            plotLine(x -> Result(x, math.secondDerivative));

        } catch (Exception ex) { SetError(); }

    }

    /**
     * @param event clears the data out of GUI
     */
    @FXML
    private void handleClearButtonAction(final ActionEvent event) {
        ClearStage(true);
    }

    private void ClearStage(boolean clearInput) {
        if (clearInput) input.setText("");
        mathsGraph.clear();
        errorText.setText("");
        input.setStyle("-fx-border-color: transparent");
        ExtremaContent.setText("");
        RootsContent.setText("");
        InflectionContent.setText("");
        YIntersectionContent.setText("");
        ExtremaTitle.setStyle("-fx-fill:  #092c4c");
        ExtremaContent.setStyle("-fx-fill:  #092c4c");
        RootsTitle.setStyle("-fx-fill:  #092c4c");
        RootsContent.setStyle("-fx-fill:  #092c4c");
        InflectionTitle.setStyle("-fx-fill:  #092c4c");
        InflectionContent.setStyle("-fx-fill:  #092c4c");
        YIntersectionTitle.setStyle("-fx-fill:  #092c4c");
        YIntersectionContent.setStyle("-fx-fill:  #092c4c");
        ExtremaClip.setDisable(false);
        RootsClip.setDisable(false);
        InflectionClip.setDisable(false);
    }
    /**
     * @param event is closing the window
     */
    @FXML
    private void handleStageClearButton(final ActionEvent event) {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }


    /**
     * @param actionEvent export the data to an pdf document
     * @throws IOException
     */
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

    /**
     * @param actionEvent export the data to an excel document
     * @throws IOException
     */
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


    /**
     * @param actionEvent Copys the content to the clipboard
     */
    public void ExtremaCopyToClipboard(ActionEvent actionEvent) {
        clip.CopyToClipboard(ExtremaContent.getText(), errorText);
    }

    /**
     * @param actionEvent Copys the content to the clipboard
     */
    public void RootsCopyToClipboard(ActionEvent actionEvent) {
        clip.CopyToClipboard(RootsContent.getText(), errorText);
    }

    /**
     * @param actionEvent Copys the content to the clipboard
     */
    public void InflectionCopyToClipboard(ActionEvent actionEvent) {
        clip.CopyToClipboard(InflectionContent.getText(), errorText);
    }

    /**
     * @param actionEvent Copys the content to the clipboard
     */
    public void YIntersectionCopyToClipboard(ActionEvent actionEvent) {
        clip.CopyToClipboard(YIntersectionContent.getText(), errorText);
    }
}