package org.example.mathpower.points;

import org.example.mathpower.MathPower;

import java.util.ArrayList;
import java.util.List;

public class IntersectionPoints {

    private MathPower math;

    /**
     * Injection of dependencies to build the class.
     * @param math injection of the class MathPower to use their properties.
     */
    public IntersectionPoints(MathPower math) { this.math = math; }

    /**
     * Calculates the intersection point of the graph with the y-axis.
     * @return a double value which indicates the y-intersection point.
     */
    public double ReturnYPoint() {

        double yIntersectionPoint = 0;

        for (var data: math.formulaData) {

            yIntersectionPoint = data[1] == 0 ? data[0] : 0;

        }

        return yIntersectionPoint;

    }

    /**
     * Calculates the value table of the graph. The steps on the x-axis are responsible for the amount of values withing the table.
     * @param xSteps        specifies the gap between each x-value on the x-axis.
     * @param lowerCap      specifies the lower boundary of the value table and the start of the steps on the x-axis.
     * @param upperCap      specifies the upper boundary of the value table which must not be a exact value.
     * @return              a list of double arrays with the exact coordinates of the points of the graph within the system of coordinates.
     */
    public List<double[]> ReturnXPoints(double xSteps, double lowerCap, double upperCap) {

        List<double[]> result = new ArrayList<>();
        double xValue;

        for (xValue = lowerCap; xValue <= upperCap; xValue += xSteps) {

            result.add(new double[] {
                xValue,
                math.yValue.GetYValue(math.formulaData, xValue)
            });

        }

        return result;

    }

}