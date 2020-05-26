package org.example.mathpower.helpers;

import java.util.List;

public class Bisection {

    /**
     * Calculates and returns a value which approach a x-value of a root point.
     * @param lowerLimitation   lower limitation for the biscetion
     * @param upperLimitation   upper limitation for the bisection
     * @param formulaData       list of double arrays with multiplier and grades of the formula
     * @return                  a value which approach a x-value of a root point
     */
    protected double ReturnBisectionValues(double[] lowerLimitation, double[] upperLimitation, List<double[]> formulaData) {

        double yValue;
        double rootPoint = 0;
        double average;

        //Does the bisection 25 times. Only breaks when the for-loop ends or the current y-value is equal zero.
        for (int count = 0; count <= 25; count++) {

            average = (lowerLimitation[0] + upperLimitation[0]) / 2; //Get the average of the lower- and upper-cap of the bisection.
            yValue = 0; //resets the y-value to zero.

            //Calculate the y-value with the new average
            for (var part : formulaData) {

                //Checks if the current grade of the part of the formulaData is not zero.
                if (part[1] != 0) {

                    //Checks if the current multiplier of the part of the formulaData is not zero.
                    if (part[0] != 0)
                        yValue += part[0] * Math.pow(average, part[1]); //Adds the result of multiplier times the average-exponent-grade (m*a^e) to the current value of y.
                    else
                        yValue += Math.pow(average, part[1]); //Adds the results of average-exponent-grade (a^e) to the current value of y.

                } else {

                    yValue += part[0]; //Adds the value of the multiplier to the current value of y.

                }

            }

            //Checks if the y-value is equal zero. If not the condition checks if the current y-value is negative.
            if (yValue == 0) {

                rootPoint = average; //Set the current x-value as the root point.
                break; //Breaks the for-loop to return the current root point

            } else if (String.valueOf(yValue).startsWith("-")) {

                //Checks if the y-value of the upper-cap of the bisection is positive.
                if (!String.valueOf(upperLimitation[1]).startsWith("-")) {

                    lowerLimitation[0] = average; //Sets the current average as the x-value of the lower-cap
                    lowerLimitation[1] = yValue; //Sets the current y-value as the y-value of the lower-cap

                } else {

                    upperLimitation[0] = average; //Sets the current average as the x-value of the upper-cap
                    upperLimitation[1] = yValue; //Sets the current y-value as the y-value of the upper-cap

                }

            } else {

                //Checks if the y-value of the upper-cap of the bisection is negative.
                if (String.valueOf(upperLimitation[1]).startsWith("-")) {

                    lowerLimitation[0] = average; //Sets the current average as the x-value of the lower-cap
                    lowerLimitation[1] = yValue; //Sets the current y-value as the y-value of the lower-cap

                } else {

                    upperLimitation[0] = average; //Sets the current average as the x-value of the upper-cap
                    upperLimitation[1] = yValue; //Sets the current y-value as the y-value of the upper-cap

                }

            }

            rootPoint = average; //Sets the current x-value as the root point

        }

        return rootPoint; //Returns a x-value which is near a root point

    }

}