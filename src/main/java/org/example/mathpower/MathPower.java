package org.example.mathpower;

import org.example.mathpower.points.ExtremePoints;
import org.example.mathpower.points.InflectionPoints;
import org.example.mathpower.points.IntersectionPoints;
import org.example.mathpower.points.RootPoints;
import org.example.mathpower.helpers.Derivatives;
import org.example.mathpower.helpers.FormulaValidator;

import java.util.List;

public class MathPower {

    public List<double[]> formulaData;
    public List<double[]> firstDerivative;
    public List<double[]> secondDerivative;
    public List<double[]> thirdDerivative;
    public String formula;

    private final RootPoints rootPoints = new RootPoints(MathPower.this);
    private final ExtremePoints extremePoints = new ExtremePoints(MathPower.this);
    private final IntersectionPoints intersectionPoints = new IntersectionPoints(MathPower.this);
    private final InflectionPoints inflectionPoints = new InflectionPoints(MathPower.this);
    private final FormulaValidator formulaValidator = new FormulaValidator();
    private final Derivatives derivatives = new Derivatives();

    public MathPower(String formula) {

        this.formula = formula;
        this.formulaData = formulaValidator.GetFormulaValues(this.formula);
        this.firstDerivative = derivatives.GetDerivative(this.formulaData);
        this.secondDerivative = derivatives.GetDerivative(this.firstDerivative);
        this.thirdDerivative = derivatives.GetDerivative(this.secondDerivative);

    }

    public List<double[]> GetRootPoints() { return rootPoints.ReturnRootPoints(); }

    public List<double[]> GetExtremaPoints() { return extremePoints.GetExtremenPoints(); }

    public List<double[]> GetInflectionPoints() { return inflectionPoints.ReturnInflectionPoints(); }

    public List<double[]> GetXIntersectionPoints(double xSteps, double lowerCap, double upperCap) {

        return intersectionPoints.GetXPoints(xSteps, lowerCap, upperCap);

    }

    public double GetYIntersectionPoint() { return intersectionPoints.GetYPoint(); }

}