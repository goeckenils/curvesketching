package org.example.mathpower;

import org.example.mathpower.helpers.*;
import org.example.mathpower.points.*;

import java.util.List;

public class MathPower {

    public List<double[]> formulaData;
    public List<double[]> firstDerivative;
    public List<double[]> secondDerivative;
    public List<double[]> thirdDerivative;
    public String formula;
    public X1RootClass x1 = new X1RootClass();
    public X2RootClass x2 = new X2RootClass();
    public YValue yValue = new YValue();
    public HornersMethod hornersMethod = new HornersMethod();
    public FormulaValidator formulaValidator = new FormulaValidator();
    public Derivatives derivatives = new Derivatives();

    private ExtremePoints extremePoints = new ExtremePoints(MathPower.this);
    private InflectionPoints inflectionPoints = new InflectionPoints(MathPower.this);
    private RootPoints rootPoints = new RootPoints(MathPower.this);
    private IntersectionPoints intersectionPoints = new IntersectionPoints(MathPower.this);

    /**
     * Constructor of the MathPower class which includes methods to get necessary information of the graph.
     * @param formula representing the user input of a string which contains a mathematical formula. Yet not validated.
     */
    public MathPower(String formula) {

        this.formula = formula;
        this.formulaData = formulaValidator.GetFormulaValues(this.formula);
        this.firstDerivative = derivatives.GetDerivative(this.formulaData);
        this.secondDerivative = derivatives.GetDerivative(this.firstDerivative);
        this.thirdDerivative = derivatives.GetDerivative(this.secondDerivative);

    }

    /**
     * Getter of a list of positions of possible root points.
     * @return a list of double arrays which include the positions of possible root points.
     */
    public List<double[]> GetRootPoints() { return rootPoints.ReturnRootPoints(); }

    /**
     * Getter of a list of positions of possible extreme points.
     * @return a list of double arrays which include the positions of possible extreme points.
     */
    public List<double[]> GetExtremaPoints() { return extremePoints.ReturnExtremenPoints(); }

    /**
     * Getter of a list of positions of possible inflection points.
     * @return a list of double arrays which include the positions of possible inflection points.
     */
    public List<double[]> GetInflectionPoints() { return inflectionPoints.ReturnInflectionPoints(); }

    /**
     * Getter to return a list of positions in the coordinate system calculate with the formula and defined steps and boundaries.
     * @param xSteps        specifies the gap between each x-value on the x-axis.
     * @param lowerCap      specifies the lower boundary of the value table and the start of the steps on the x-axis.
     * @param upperCap      specifies the upper boundary of the value table which must not be a exact value.
     * @return              a list of double arrays with the exact coordinates of the points of the graph within the system of coordinates.
     */
    public List<double[]> GetXIntersectionPoints(double xSteps, double lowerCap, double upperCap) {

        return intersectionPoints.ReturnXPoints(xSteps, lowerCap, upperCap);

    }

    /**
     * Getter to return intersection points on the y-axis.
     * @return a double value with indicates a intersection on the y-axis.
     */
    public double GetYIntersectionPoint() { return intersectionPoints.ReturnYPoint(); }

}