package org.example.mathpower.points;

import org.example.mathpower.MathPower;

import java.util.ArrayList;
import java.util.List;

public class InflectionPoints {

    private MathPower math;

    /**
     * Injection of dependencies to build the class.
     * @param math injection of the class MathPower to use their properties.
     */
    public InflectionPoints(MathPower math) { this.math = math; }

    /**
     * Arranges the second derivative of the formula and returns inflection points if available.
     * @return a list of double arrays with the position of the extreme point.
     */
    public List<double[]> ReturnInflectionPoints() {

        List<double[]> inflectionPoints = new ArrayList<>();

        if (math.thirdDerivative.size() == 0 ||
            math.thirdDerivative.get(0)[1] > 0 ||
            math.thirdDerivative.get(0)[0] == 0 ||
            math.secondDerivative.size() == 0)
            return inflectionPoints;

        double grade = math.secondDerivative.get(0)[1];

        if (grade == 0)
            return inflectionPoints;

        if (math.secondDerivative.size() == 1) {

            inflectionPoints.add(new double[] {
                    0,
                    math.yValue.GetYValue(math.formulaData, 0)
            });

        } else {

            if (grade >= 3) {

                for (var rootPoint: math.hornersMethod.ReturnRootPointValues(math.secondDerivative))
                    if (Double.isFinite(rootPoint))
                        inflectionPoints.add(new double[] {
                                rootPoint,
                                math.yValue.GetYValue(math.formulaData, rootPoint)
                        });

            } else if (grade == 2) {

                for (var rootPoint: math.x2.GetRootPoints(math.secondDerivative))
                    if (Double.isFinite(rootPoint))
                        inflectionPoints.add(new double[] {
                                rootPoint,
                                math.yValue.GetYValue(math.formulaData, rootPoint)
                        });

            } else if (grade == 1) {

                double inflectionPoint = math.x1.GetRootPoint(math.secondDerivative);

                inflectionPoints.add(new double[]{
                        inflectionPoint,
                        math.yValue.GetYValue(math.formulaData, inflectionPoint)
                });

            }

        }

        return inflectionPoints;

    }

}