package org.example.mathpower.points;

import org.example.mathpower.MathPower;

import java.util.ArrayList;
import java.util.List;

public class RootPoints {

    private MathPower math;

    /**
     * Injection of dependencies to build the class.
     * @param math injection of the class MathPower to use their properties.
     */
    public RootPoints(MathPower math) { this.math = math; }

    /**
     * Calculates and returns the root points of the formula if available.
     * @return a list of double arrays which includes the position of the root points.
     */
    public List<double[]> ReturnRootPoints() {

        List<double[]> rootPoints = new ArrayList<>();
        double grade = math.formulaData.get(0)[1];

        if (math.formulaData.size() == 1) {

            rootPoints.add(new double[] { 0, 0 });

        } else {

            if (grade >= 3) {

                if (math.formulaData.get(1)[1] == 0) {

                    double x = -(math.formulaData.get(1)[0]) / math.formulaData.get(0)[0];
                    double n = 1 / grade;
                    double calcRoot;

                    if (x < 0 && grade%2 != 0) {

                        rootPoints.add(new double[] { -Math.pow(-x, n), 0 });

                    } else if (x > 0 && grade%2 == 0) {

                        calcRoot = Math.pow(x, n);
                        rootPoints.add(new double[] { -calcRoot, 0 });
                        rootPoints.add(new double[] { calcRoot, 0 });

                    }

                } else {

                    for (var rootPoint: math.hornersMethod.ReturnRootPointValues(math.formulaData))
                        if (Double.isFinite(rootPoint))
                            rootPoints.add(new double[] {
                                rootPoint,
                                0
                            });

                }

            } else if (grade == 2) {

                for (var rootPoint: math.x2.GetRootPoints(math.formulaData))
                    if (Double.isFinite(rootPoint))
                        rootPoints.add(new double[] {
                                rootPoint,
                                0
                        });

            } else if (grade == 1) {

                rootPoints.add(new double[]{
                        math.x1.GetRootPoint(math.formulaData),
                        0
                });

            }

        }

        return rootPoints;

    }

}