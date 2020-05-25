package org.example.mathpower.helpers;

import java.util.List;

public class Bisection {

    protected double ReturnBisectionValues(double[] lowerCheck, double[] upperCheck, List<double[]> formulaData) {

        double yValue = 0;
        double rootPoint = 0;
        double middleValueCheck;

        for (int count = 0; count <= 50; count++) {

            middleValueCheck = (lowerCheck[0] + upperCheck[0]) / 2;
            yValue = 0;

            for (var data : formulaData) {

                if (data[1] != 0) {

                    if (data[0] != 0)
                        yValue += data[0] * Math.pow(middleValueCheck, data[1]);
                    else
                        yValue += Math.pow(middleValueCheck, data[1]);

                } else {

                    yValue += data[0];

                }

            }

            if (yValue == 0) {

                rootPoint = middleValueCheck;
                break;

            } else if (String.valueOf(yValue).startsWith("-")) {

                if (!String.valueOf(upperCheck[1]).startsWith("-")) {

                    lowerCheck[0] = middleValueCheck;
                    lowerCheck[1] = yValue;

                } else {

                    upperCheck[0] = middleValueCheck;
                    upperCheck[1] = yValue;

                }

            } else {

                if (String.valueOf(upperCheck[1]).startsWith("-")) {

                    lowerCheck[0] = middleValueCheck;
                    lowerCheck[1] = yValue;

                } else {

                    upperCheck[0] = middleValueCheck;
                    upperCheck[1] = yValue;

                }

            }

            rootPoint = middleValueCheck;

        }

        return rootPoint;

    }

}
