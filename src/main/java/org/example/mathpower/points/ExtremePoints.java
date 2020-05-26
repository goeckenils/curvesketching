package org.example.mathpower.points;

import java.util.ArrayList;
import java.util.List;

class ExtremePoints {

    private Points points;

    protected ExtremePoints(Points Points) { points = Points; }

    protected List<double[]> ReturnExtremenPoints() {

        List<double[]> extremaPoints = new ArrayList<>();

        double grade = points.math.firstDerivative.get(0)[1];

        if (grade == 0)
            return extremaPoints;

        if (points.math.firstDerivative.size() == 1) {

            double[] lastValue = points.math.secondDerivative.get(points.math.secondDerivative.size() - 1);

            if (lastValue[0] != 0 && lastValue[1] != 0)
                extremaPoints.add(new double[] {
                        0,
                        points.yValue.GetYValue(points.math.formulaData, 0)
                });

        } else {

            if (grade >= 3) {

                for (var rootPoint: points.hornerSchema.ReturnHornerSchemaResults(points.math.firstDerivative))
                    if (Double.isFinite(rootPoint))
                        extremaPoints.add(new double[] {
                                rootPoint,
                                points.yValue.GetYValue(points.math.formulaData, rootPoint)
                        });

            } else if (grade == 2) {

                for (var rootPoint: points.x2.GetRootPoints(points.math.firstDerivative))
                    if (Double.isFinite(rootPoint))
                        extremaPoints.add(new double[] {
                                rootPoint,
                                points.yValue.GetYValue(points.math.formulaData, rootPoint)
                        });

            } else if (grade == 1) {

                double extremaPoint = points.x1.GetRootPoint(points.math.firstDerivative);

                extremaPoints.add(new double[]{
                        extremaPoint,
                        points.yValue.GetYValue(points.math.formulaData, extremaPoint)
                });

            }

        }

        return extremaPoints;

    }

}
