package org.example.mathpower.points;

import org.example.mathpower.MathPower;

import java.util.ArrayList;
import java.util.List;

public class ExtremePoints {

    private MathPower math;

    /**
     * Injection of dependencies to build the class.
     * @param math injection of the class MathPower to use their properties.
     */
    public ExtremePoints(MathPower math) { this.math = math; }

    /**
     * Arranges the first derivative of the formula and returns extreme points if available.
     * @return a list of double arrays with the position of the extreme point.
     */
    public List<double[]> ReturnExtremePoints() {

        List<double[]> extremaPoints = new ArrayList<>();

        if (math.firstDerivative.isEmpty())
            return extremaPoints;

        double grade = math.firstDerivative.get(0)[1];

        if (grade == 0)
            return extremaPoints;

        if (math.firstDerivative.size() == 1) {

            double[] lastValue = math.secondDerivative.get(math.secondDerivative.size() - 1);

            if (lastValue[0] != 0 && lastValue[1] != 0)
                extremaPoints.add(new double[] {
                        0,
                        math.yValue.GetYValue(math.formulaData, 0)
                });

        } else {

            if (grade >= 3) {

                for (var rootPoint: math.hornersMethod.ReturnRootPointValues(math.firstDerivative))
                    if (Double.isFinite(rootPoint))
                        extremaPoints.add(new double[] {
                                rootPoint,
                                math.yValue.GetYValue(math.formulaData, rootPoint)
                        });

            } else if (grade == 2) {

                for (var rootPoint: math.x2.GetRootPoints(math.firstDerivative))
                    if (Double.isFinite(rootPoint))
                        extremaPoints.add(new double[] {
                                rootPoint,
                                math.yValue.GetYValue(math.formulaData, rootPoint)
                        });

            } else if (grade == 1) {

                double extremaPoint = math.x1.GetRootPoint(math.firstDerivative);

                extremaPoints.add(new double[]{
                        extremaPoint,
                        math.yValue.GetYValue(math.formulaData, extremaPoint)
                });

            }

        }

        return extremaPoints;

    }

}