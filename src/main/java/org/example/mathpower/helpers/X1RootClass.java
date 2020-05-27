package org.example.mathpower.helpers;

import java.util.List;

public class X1RootClass {

    /**
     *
     * @param formulaData
     * @return
     */
    public double GetRootPoint(List<double[]> formulaData) {

        double tempResult = -formulaData.get(1)[0];
        double a = formulaData.get(0)[0];

        return a == 0 ? tempResult : tempResult / a;

    }

}
