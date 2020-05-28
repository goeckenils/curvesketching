package org.example.mathpower.helpers;

import java.util.List;

public class ABCFormula {

    /**
     * Calculates and returns the double array result of the "ABCFormula". Validation of the return has to be withing the calling method.
     * @param formulaData   list of double arrays with multiplier and grades of the formula
     * @return              a array of doubles which includes the results of the calculation of the "ABCFormula"
     */
    public double[] GetValues(List<double[]> formulaData) {

        /*Either declares one as the value of the variable if the x-value of the first part of the formula is equal zero.
        * If not it declares the value of x-value of the first part of the formula as the value of the variable*/
        double a = formulaData.get(0)[0] == 0 ? 1 : formulaData.get(0)[0];
        /*Either declares one as the value of the variable if the x-value of the second part of the formula is equal zero.
          If not it declares the value of x-value of the second part of the formula as the value of the variable*/
        double b = formulaData.get(1)[0] == 0 ? 1 : formulaData.get(1)[0];
        double c = formulaData.get(2)[0]; //Declares the variable with the value of the x of the third part of the formula
        double calculation = Math.pow(b, 2) - 4 * (a * c); //Inner calculation of the "ABCFormula". Needed because calculation within formalUp sometimes throws exceptions or incorrect data.
        double formalUp = Math.sqrt(calculation); //Takes the square root of the result of the inner calculation.
        a = 2 * a; //Doubles the current value of a.
        b = -b; //Negates the current value of b.

        //See https://en.wikipedia.org/wiki/Quadratic_formula. NaN (Not a Number) or infinite values if the value is not calculable. Validation within the calling method.
        double[] rootPoints = new double[]{
                (b + formalUp) / a,
                (b - formalUp) / a
        };

        return rootPoints; //Returns a array with doubles. Could include NaN (Not a Number) or infinite values.

    }

}