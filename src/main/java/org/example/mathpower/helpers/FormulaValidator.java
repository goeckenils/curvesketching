package org.example.mathpower.helpers;

import java.util.ArrayList;
import java.util.List;

public class FormulaValidator {

    public List<double[]> GetFormulaValues(String formula) {

        List<String> dataFormula = new ArrayList<>();
        List<double[]> dataResult = new ArrayList<>();
        String minus = "-";
        Boolean firstNegative = false;

        if (formula.startsWith("-")) {

            formula = formula.replaceFirst("-", "");
            firstNegative = true;

        }

        for (var d: formula.split("\\+")) {

            if (d.matches(".*-.*")) {

                String[] temp = d.split("-");
                dataFormula.add(temp[0]);

                for (int count = 1; count < temp.length; count++)
                    dataFormula.add(minus.concat(temp[count]));

            } else
                dataFormula.add(d);

        }

        if (firstNegative == true)
            dataFormula.set(0, (minus.concat(dataFormula.get(0))));


        for (var data: dataFormula) {

            if (data.startsWith("x")) {

                if (!data.endsWith("x"))
                    dataResult.add(new double[]{0, Integer.parseInt(data.substring(data.length() - 1))});
                else
                    dataResult.add(new double[] { 0, 1 });

            } else {

                if (data.endsWith("x")) {

                    for (var singleX: data.split("x")) {

                        if (singleX.endsWith("-"))
                            dataResult.add(new double[] { -1, 1 });
                        else
                            dataResult.add(new double[] { Integer.parseInt(singleX), 1 });

                    }

                } else if (data.matches("[\\-]?([1-9])?x\\^[2-4]")) {

                    String[] tempList = data.split("x\\^");

                    if (tempList[0].endsWith("-"))
                        dataResult.add(new double[] { -1, Double.parseDouble(tempList[1]) });
                    else
                        dataResult.add(new double[] { Double.parseDouble(tempList[0]), Double.parseDouble(tempList[1]) });

                } else {

                    dataResult.add(new double[] { Integer.parseInt(data), 0 });

                }

            }

        }

        return dataResult;

    }

}