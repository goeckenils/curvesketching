package org.example.mathpower.helpers;

import java.util.List;

public class ABCFormula {

    public double[] GetValues(List<double[]> formulaData) {

        double a = formulaData.get(0)[0] == 0 ? 1 : formulaData.get(0)[0];
        double b = formulaData.get(1)[0] == 0 ? 1 : formulaData.get(1)[0];
        double c = formulaData.get(2)[0];

        double calculation = Math.pow(b, 2) - 4 * (a * c);
        double formalUp = Math.sqrt(calculation);
        double formalDown = 2 * a;
        double negatedB = -b;

        double[] rootPoints = new double[]{
                (negatedB + formalUp) / formalDown,
                (negatedB - formalUp) / formalDown
        };

        return (rootPoints);

    }

}
