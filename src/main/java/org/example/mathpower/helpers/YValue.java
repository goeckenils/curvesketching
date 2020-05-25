package org.example.mathpower.helpers;

import java.util.List;

public class YValue {

    public double GetYValue(List<double[]> formulaData, double xValue) {

        double yValue = 0;

        for (var data : formulaData) {

            if (data[1] > 1) {

                if (data[0] != 0)
                    yValue += data[0] * Math.pow(xValue, data[1]);
                else
                    yValue += Math.pow(xValue, data[1]);

            } else if (data[1] == 1) {

                if (data[0] != 0)
                    yValue += data[0] * xValue;
                else
                    yValue += xValue;

            } else {

                yValue += data[0];

            }

        }

        return yValue;

    }

}