package org.example.Helper;

import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;

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

        for (double x = -range; x <= range; x = x + 0.01) {

            plotPoint(x, function.apply(x), series);

        }

        graph.getData().add(series);

    }

/*   public void TooltipOnChart () {
        int count = 0;
        for (XYChart.Series<Double,Double> s:
        graph.getData()){
            for (XYChart.Data<Double, Double> d : s.getData()) {
                count++;
                if (count%100 != 0) {continue; }
                var node = d.getNode();
                Tooltip.install(node, new Tooltip(
                    "X Axes: "+ d.getXValue().toString() +"\n" +
                        "Y Axes: " + d.getYValue()));

                //Adding class on hover
                d.getNode().setOnMouseEntered(event -> d.getNode().getStyleClass().add("onHover"));
                d.getNode().setOnMouseEntered(event -> System.out.println(d.getXValue() + d.getYValue()));
                //Removing class on exit
                d.getNode().setOnMouseExited(event -> d.getNode().getStyleClass().remove("onHover"));
            }
        }
    }*/

    private void plotPoint(final double x, final double y, final XYChart.Series<Double, Double> series) {

        series.getData().add(new XYChart.Data<Double, Double>(x, y));



    }

    public void clear() {
        graph.getData().clear();
    }

}