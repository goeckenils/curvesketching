package org.example.mathpower.helpers;

import java.util.List;

public class YValue {

    /**
     * Calculates and returns the specific y-value of the current formula with the specified x-value
     * @param formulaData   list of double arrays with multiplier and grades of the formula
     * @param xValue        current x-value as a double to calculate the value
     * @return              the current y-value as a double
     */
    public double GetYValue(List<double[]> formulaData, double xValue) {

        double yValue = 0;

        //Goes through every part of the current formula.
        for (var part : formulaData) {

            //Checks if the y-value of the current part is higher than one. If not it checks if value is equal one.
            if (part[1] > 1) {

                //Checks if the x-value of the current part is not zero.
                if (part[0] != 0)
                    yValue += part[0] * Math.pow(xValue, part[1]); //Adds the result of multiplier times the x-value(of the current part)-exponent-grade (m*a^e) to the current y-value.
                else
                    yValue += Math.pow(xValue, part[1]); //Adds the results of x-value(of the current part)-exponent-grade (a^e) to the current y-value.

            } else if (part[1] == 1) {

                //Checks if the x-value of the current part is not zero.
                if (part[0] != 0)
                    yValue += part[0] * xValue; //Adds the result of the x-value of the current part times the current x-value of the loop to the current y-value.
                else
                    yValue += xValue; //Adds the current x-value to the current y-value.

            } else {

                yValue += part[0]; //Adds the x-value of the current part to the current y-value.

            }

        }

        return yValue; //Returns the result of the calculation as a double.

    }

}