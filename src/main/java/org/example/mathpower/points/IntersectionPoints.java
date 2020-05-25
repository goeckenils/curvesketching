package org.example.mathpower.points;

import org.example.mathpower.MathPower;

import java.util.ArrayList;
import java.util.List;

public class IntersectionPoints {

    private MathPower _math;

    public IntersectionPoints(MathPower math) { _math = math; }

    public double GetYPoint() {

        double yIntersectionPoint = 0;

        for (var data: _math.formulaData) {

            if (data[1] == 0)
                yIntersectionPoint = data[0];

        }

        return yIntersectionPoint;

    }

    public List<double[]> GetXPoints(double xSteps, double lowerCap, double upperCap) {

        List<double[]> result = new ArrayList<>();
        double yValue, xValue;

        for (xValue = lowerCap; xValue <= upperCap; xValue += xSteps) {

            yValue = 0;

            for (var data: _math.formulaData) {

                data[0] = data[0] ==  0 ? 1 : data[0];

                if (data[1] != 0)
                    yValue += data[0] * Math.pow(xValue, data[1]);

            }

            result.add(new double[] { xValue, yValue });

        }

        return result;

    }

}
