package org.example.mathpower.points;

import org.example.mathpower.helpers.HornerSchema;
import org.example.mathpower.MathPower;
import org.example.mathpower.helpers.X1RootClass;
import org.example.mathpower.helpers.X2RootClass;

import java.util.ArrayList;
import java.util.List;

public class RootPoints {

    private MathPower _math;
    private HornerSchema _hornerSchema = new HornerSchema();
    private X1RootClass _x1 = new X1RootClass();
    private X2RootClass _x2 = new X2RootClass();

    public RootPoints(MathPower math) { _math = math; }

    public List<double[]> ReturnRootPoints() {

        List<double[]> rootPoints = new ArrayList<>();
        double grade = _math.formulaData.get(0)[1];

        if (_math.formulaData.size() == 1) {

            rootPoints.add(new double[] { 0, 0 });

        } else {

            if (grade >= 3) {

                for (var rootPoint: _hornerSchema.ReturnHornerSchemaResults(_math.formulaData))
                    if (Double.isFinite(rootPoint))
                        rootPoints.add(new double[] {
                                rootPoint,
                                0
                        });

            } else if (grade == 2) {

                for (var rootPoint: _x2.GetRootPoints(_math.formulaData))
                    if (Double.isFinite(rootPoint))
                        rootPoints.add(new double[] {
                                rootPoint,
                                0
                        });

            } else if (grade == 1) {

                rootPoints.add(new double[]{
                        _x1.GetRootPoint(_math.formulaData),
                        0
                });

            }

        }

        return rootPoints;

    }

}