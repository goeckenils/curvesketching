package org.example.mathpower;

import org.example.mathpower.points.*;
import org.example.mathpower.helpers.Derivatives;
import org.example.mathpower.helpers.FormulaValidator;

import java.util.List;

public class MathPower {

    public List<double[]> formulaData;
    public List<double[]> firstDerivative;
    public List<double[]> secondDerivative;
    public List<double[]> thirdDerivative;
    public String formula;

    private final Points points = new Points(MathPower.this);
    private final FormulaValidator formulaValidator = new FormulaValidator();
    private final Derivatives derivatives = new Derivatives();

    public MathPower(String formula) {

        this.formula = formula;
        this.formulaData = formulaValidator.GetFormulaValues(this.formula);
        this.firstDerivative = derivatives.GetDerivative(this.formulaData);
        this.secondDerivative = derivatives.GetDerivative(this.firstDerivative);
        this.thirdDerivative = derivatives.GetDerivative(this.secondDerivative);

    }

    public List<double[]> GetRootPoints() { return  points.GetRootPoints(); }

    public List<double[]> GetExtremaPoints() { return points.GetExtremePoints(); }

    public List<double[]> GetInflectionPoints() { return points.GetInflectionPoints(); }

    public List<double[]> GetXIntersectionPoints(double xSteps, double lowerCap, double upperCap) {

        return points.GetXPoints(xSteps, lowerCap, upperCap);

    }

    public double GetYIntersectionPoint() { return points.GetYPoint(); }

}