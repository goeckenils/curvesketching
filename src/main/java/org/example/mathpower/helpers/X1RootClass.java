package org.example.mathpower.helpers;

import java.util.List;

public class X1RootClass {

    public double GetRootPoint(List<double[]> formulaData) {

        double tempResult = -formulaData.get(1)[0]; //Negate
        double a = formulaData.get(0)[0];

        if (a == 0) {

            return tempResult;

        } else {

            return tempResult / a;

        }

    }

}
