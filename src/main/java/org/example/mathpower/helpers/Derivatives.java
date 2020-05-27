package org.example.mathpower.helpers;

import java.util.ArrayList;
import java.util.List;

public class Derivatives {

    /**
     * Calculates and returns the derivative of the given formula data
     * @param formulaData   list of double arrays which includes the multipliers and grades of the formula
     * @return              a list of double arrays which create a new set of specific combinations of multipliers and grades
     */
    public List<double[]> GetDerivative(List<double[]> formulaData) {

        List<double[]> derivative = new ArrayList<>();

        for (var part : formulaData) {

            if (part[1] > 0) {

                if (part[0] == 0)
                    derivative.add(new double[]{
                            part[1],
                            part[1] - 1
                    });
                else
                    derivative.add(new double[]{
                            part[0] * part[1],
                            part[1] - 1
                    });

            } else if (part[0] != 0 && part[1] == 0) {

                break;

            } else {

                derivative.add(new double[]{
                        part[0] * part[1],
                        0
                });

            }

        }

        return derivative;

    }

}