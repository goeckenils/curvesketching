package org.example.mathpower.points;

import java.util.ArrayList;
import java.util.List;

public class RootPoints {

    private Points points;

    protected RootPoints(Points Points) { points = Points; }

    protected List<double[]> ReturnRootPoints() {

        List<double[]> rootPoints = new ArrayList<>();
        double grade = points.math.formulaData.get(0)[1];

        if (points.math.formulaData.size() == 1) {

            rootPoints.add(new double[] { 0, 0 });

        } else {

            if (grade >= 3) {

                for (var rootPoint: points.hornerSchema.ReturnHornerSchemaResults(points.math.formulaData))
                    if (Double.isFinite(rootPoint))
                        rootPoints.add(new double[] {
                                rootPoint,
                                0
                        });

            } else if (grade == 2) {

                for (var rootPoint: points.x2.GetRootPoints(points.math.formulaData))
                    if (Double.isFinite(rootPoint))
                        rootPoints.add(new double[] {
                                rootPoint,
                                0
                        });

            } else if (grade == 1) {

                rootPoints.add(new double[]{
                        points.x1.GetRootPoint(points.math.formulaData),
                        0
                });

            }

        }

        return rootPoints;

    }

}