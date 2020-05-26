package org.example.mathpower.points;

import java.util.ArrayList;
import java.util.List;

public class InflectionPoints {

    private Points points;

    protected InflectionPoints(Points Points) { points = Points; }

    protected List<double[]> ReturnInflectionPoints() {

        List<double[]> inflectionPoints = new ArrayList<>();

        if (points.math.thirdDerivative.size() == 0 ||
            points.math.thirdDerivative.get(0)[1] > 0 ||
            points.math.thirdDerivative.get(0)[0] == 0 ||
            points.math.secondDerivative.size() == 0)
            return inflectionPoints;

        double grade = points.math.secondDerivative.get(0)[1];

        if (grade == 0)
            return inflectionPoints;

        if (points.math.secondDerivative.size() == 1) {

            inflectionPoints.add(new double[] {
                    0,
                    points.yValue.GetYValue(points.math.formulaData, 0)
            });

        } else {

            if (grade >= 3) {

                for (var rootPoint: points.hornerSchema.ReturnHornerSchemaResults(points.math.secondDerivative))
                    if (Double.isFinite(rootPoint))
                        inflectionPoints.add(new double[] {
                                rootPoint,
                                points.yValue.GetYValue(points.math.formulaData, rootPoint)
                        });

            } else if (grade == 2) {

                for (var rootPoint: points.x2.GetRootPoints(points.math.secondDerivative))
                    if (Double.isFinite(rootPoint))
                        inflectionPoints.add(new double[] {
                                rootPoint,
                                points.yValue.GetYValue(points.math.formulaData, rootPoint)
                        });

            } else if (grade == 1) {

                double inflectionPoint = points.x1.GetRootPoint(points.math.secondDerivative);

                inflectionPoints.add(new double[]{
                        inflectionPoint,
                        points.yValue.GetYValue(points.math.formulaData, inflectionPoint)
                });

            }

        }

        return inflectionPoints;

    }

}