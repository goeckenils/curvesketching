package org.example.mathpower.helpers;
import java.util.ArrayList;
import java.util.List;

public class X2RootClass {

    private final ABCFormula abcFormula = new ABCFormula();

    public List<Double> GetRootPoints(List<double[]> formulaData) {

        List<Double> rootPoints = new ArrayList<>();

        if (formulaData.size() == 3) {

            for (var rootPoint: abcFormula.GetValues(formulaData))
                if (Double.isFinite(rootPoint))
                    rootPoints.add(rootPoint);

        } else if (formulaData.size() == 2) {

            double a = formulaData.get(0)[0];
            double b = -(formulaData.get(1)[0] == 0 ? 1 : formulaData.get(1)[0]); //Negate

            if (a != 0)
                b = b / formulaData.get(0)[0];

            if (formulaData.get(1)[1] == 1) {

                rootPoints.add((double) 0);
                rootPoints.add(b);

            } else {

                if (b > 0) {

                    rootPoints.add(-Math.sqrt(b));
                    rootPoints.add(Math.sqrt(b));

                }

            }

        } else {

            rootPoints.add((double) 0);

        }

        return rootPoints;

    }

}
