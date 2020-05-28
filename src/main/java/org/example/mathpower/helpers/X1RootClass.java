package org.example.mathpower.helpers;

import java.util.List;

public class X1RootClass {

    /**
     * Returns a root point of a formula with only one specific x without further attachments.
     * @param formulaData   list of double arrays with multiplier and grades of the formula.
     * @return              a double value which indicates the position of the root point.
     */
    public double GetRootPoint(List<double[]> formulaData) {

        double tempResult = -formulaData.get(1)[0];
        double a = formulaData.get(0)[0];

        return a == 0 ? tempResult : tempResult / a;

    }

}