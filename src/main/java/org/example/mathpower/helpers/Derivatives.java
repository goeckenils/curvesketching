package org.example.mathpower.helpers;

import java.util.ArrayList;
import java.util.List;

public class Derivatives {

    /**
     *
     * @param inputData
     * @return
     */
    public List<double[]> GetDerivative(List<double[]> inputData) {

        List<double[]> derivative = new ArrayList<>();

        for (var data : inputData) {

            if (data[1] > 0) {

                if (data[0] == 0)
                    derivative.add(new double[]{
                            data[1],
                            data[1] - 1
                    });
                else
                    derivative.add(new double[]{
                            data[0] * data[1],
                            data[1] - 1
                    });

            } else if (data[0] != 0 && data[1] == 0) {

                break;

            } else {

                derivative.add(new double[]{
                        data[0] * data[1],
                        0
                });

            }

        }

        return derivative;

    }

}