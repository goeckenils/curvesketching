package org.example.Helper;

import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;

import java.util.function.Function;

public class MyGraph {

    private XYChart<Double, Double> graph;
    private double range;

    public MyGraph(final XYChart<Double, Double> graph, final double range) {

        this.graph = graph;
        this.range = range;

    }


    public void plotLine(final Function<Double, Double> function) {

        final XYChart.Series<Double, Double> series = new XYChart.Series<Double, Double>();

        for (double x = -range; x <= range; x = x + 0.1) {

            plotPoint(x, function.apply(x), series);

        }

        graph.getData().add(series);

    }

   public void TooltipOnChart () {
        for (XYChart.Series<Double,Double> s:
        graph.getData()){
            for (XYChart.Data<Double, Double> d : s.getData()) {
                var xValue = Formatter.DecimalFormat(d.getXValue());
                var yValue = Formatter.DecimalFormat(d.getYValue());

                var node = d.getNode();
                Tooltip tooltip = new Tooltip("X Axes: "+ xValue +"\n" +
                    "Y Axes: " + yValue);
                tooltip.setShowDelay(Duration.seconds(0));
                Tooltip.install(node,tooltip);

                //Adding class on hover
                d.getNode().setOnMouseEntered(event -> d.getNode().getStyleClass().add("onHover"));
                //Removing class on exit
                d.getNode().setOnMouseExited(event -> d.getNode().getStyleClass().remove("onHover"));
            }
        }
    }

    private void plotPoint(final double x, final double y, final XYChart.Series<Double, Double> series) {

        series.getData().add(new XYChart.Data<Double, Double>(x, y));



    }

    public void clear() {
        graph.getData().clear();
    }

}