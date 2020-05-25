package org.example.mathpower.points;

import org.example.mathpower.MathPower;
import org.example.mathpower.helpers.HornerSchema;
import org.example.mathpower.helpers.YValue;
import org.example.mathpower.helpers.X1RootClass;
import org.example.mathpower.helpers.X2RootClass;

import java.util.ArrayList;
import java.util.List;

public class InflectionPoints {

    private MathPower _math;
    protected X1RootClass _x1 = new X1RootClass();
    protected X2RootClass _x2 = new X2RootClass();
    private final HornerSchema _hornerSchema = new HornerSchema();
    private final YValue _yValue = new YValue();

    public InflectionPoints(MathPower math) { _math = math; }

    public List<double[]> ReturnInflectionPoints() {

        List<double[]> inflectionPoints = new ArrayList<>();

        if (_math.thirdDerivative.size() == 0 || _math.thirdDerivative.get(0)[1] > 0 || _math.thirdDerivative.get(0)[0] == 0)
            return inflectionPoints;

        if (_math.secondDerivative.size() == 0)
            return inflectionPoints;

        double grade = _math.secondDerivative.get(0)[1];

        if (grade == 0)
            return inflectionPoints;

        if (_math.secondDerivative.size() == 1) {

            inflectionPoints.add(new double[] {
                    0,
                    _yValue.GetYValue(_math.formulaData, 0)
            });

        } else {

            if (grade >= 3) {

                for (var rootPoint: _hornerSchema.ReturnHornerSchemaResults(_math.secondDerivative))
                    if (Double.isFinite(rootPoint))
                        inflectionPoints.add(new double[] {
                                rootPoint,
                                _yValue.GetYValue(_math.formulaData, rootPoint)
                        });

            } else if (grade == 2) {

                for (var rootPoint: _x2.GetRootPoints(_math.secondDerivative))
                    if (Double.isFinite(rootPoint))
                        inflectionPoints.add(new double[] {
                                rootPoint,
                                _yValue.GetYValue(_math.formulaData, rootPoint)
                        });

            } else if (grade == 1) {

                double inflectionPoint = _x1.GetRootPoint(_math.secondDerivative);

                inflectionPoints.add(new double[]{
                        inflectionPoint,
                        _yValue.GetYValue(_math.formulaData, inflectionPoint)
                });

            }

        }

        return inflectionPoints;

    }

}
