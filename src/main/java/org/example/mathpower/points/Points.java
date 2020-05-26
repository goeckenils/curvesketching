package org.example.mathpower.points;

import org.example.mathpower.MathPower;
import org.example.mathpower.helpers.HornerSchema;
import org.example.mathpower.helpers.X1RootClass;
import org.example.mathpower.helpers.X2RootClass;
import org.example.mathpower.helpers.YValue;

import java.util.List;

public class Points {

    protected MathPower math;
    protected X1RootClass x1 = new X1RootClass();
    protected X2RootClass x2 = new X2RootClass();
    protected YValue yValue = new YValue();
    protected HornerSchema hornerSchema = new HornerSchema();

    private ExtremePoints _extremePoints;
    private InflectionPoints _inflectionPoints;
    private RootPoints _rootPoints;
    private IntersectionPoints _intersectionPoints;

    public Points(MathPower Math) {

        math = Math;
        _extremePoints = new ExtremePoints(Points.this);
        _inflectionPoints = new InflectionPoints(Points.this);
        _rootPoints = new RootPoints(Points.this);
        _intersectionPoints = new IntersectionPoints(Points.this);

    }

    public List<double[]> GetExtremePoints() { return _extremePoints.ReturnExtremenPoints(); }

    public List<double[]> GetInflectionPoints() { return _inflectionPoints.ReturnInflectionPoints(); }

    public List<double[]> GetRootPoints() { return _rootPoints.ReturnRootPoints(); }

    public double GetYPoint() { return _intersectionPoints.ReturnYPoint(); }

    public List<double[]> GetXPoints(double xSteps, double lowerCap, double upperCap) {

        return _intersectionPoints.ReturnXPoints(xSteps, lowerCap, upperCap);

    }

}
