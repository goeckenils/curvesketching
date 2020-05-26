package org.example.mathpower.points;

import java.util.ArrayList;
import java.util.List;

class IntersectionPoints {

    private Points points;

    protected IntersectionPoints(Points Points) { points = Points; }

    protected double ReturnYPoint() {

        double yIntersectionPoint = 0;

        for (var data: points.math.formulaData) {

            yIntersectionPoint = data[1] == 0 ? data[0] : 0;

        }

        return yIntersectionPoint;

    }

    protected List<double[]> ReturnXPoints(double xSteps, double lowerCap, double upperCap) {

        List<double[]> result = new ArrayList<>();
        double xValue;

        for (xValue = lowerCap; xValue <= upperCap; xValue += xSteps) {

            result.add(new double[] {
                xValue,
                points.yValue.GetYValue(points.math.formulaData, xValue)
            });

        }

        return result;

    }

}