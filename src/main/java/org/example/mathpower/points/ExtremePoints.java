package org.example.mathpower.points;

import org.example.mathpower.MathPower;
import org.example.mathpower.helpers.HornerSchema;
import org.example.mathpower.helpers.X1RootClass;
import org.example.mathpower.helpers.X2RootClass;
import org.example.mathpower.helpers.YValue;

import java.util.ArrayList;
import java.util.List;

public class ExtremePoints {

    private MathPower _math;
    private X1RootClass x1 = new X1RootClass();
    private X2RootClass x2 = new X2RootClass();
    private final HornerSchema hornerSchema = new HornerSchema();
    private final YValue yValue = new YValue();

    public ExtremePoints(MathPower math) { _math = math; }

    public List<double[]> GetExtremenPoints() {

        List<double[]> extremaPoints = new ArrayList<>();

        double grade = _math.firstDerivative.get(0)[1];

        if (grade == 0)
            return extremaPoints;

        if (_math.firstDerivative.size() == 1) {

            double[] lastValue = _math.secondDerivative.get(_math.secondDerivative.size() - 1);

            if (lastValue[0] != 0 && lastValue[1] == 0)
                extremaPoints.add(new double[] {
                        0,
                        yValue.GetYValue(_math.formulaData, 0)
                });

        } else {

            if (grade >= 3) {

                for (var rootPoint: hornerSchema.ReturnHornerSchemaResults(_math.firstDerivative))
                    if (Double.isFinite(rootPoint))
                        extremaPoints.add(new double[] {
                                rootPoint,
                                yValue.GetYValue(_math.formulaData, rootPoint)
                        });

            } else if (grade == 2) {

                for (var rootPoint: x2.GetRootPoints(_math.firstDerivative))
                    if (Double.isFinite(rootPoint))
                        extremaPoints.add(new double[] {
                                rootPoint,
                                yValue.GetYValue(_math.formulaData, rootPoint)
                        });

            } else if (grade == 1) {

                double extremaPoint = x1.GetRootPoint(_math.firstDerivative);

                extremaPoints.add(new double[]{
                        extremaPoint,
                        yValue.GetYValue(_math.formulaData, extremaPoint)
                });

            }

        }

        return extremaPoints;

    }

}
