package org.example.mathpower.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FormulaValidator {

    public List<double[]> GetFormulaValues(String formula) {

        List<String> dataFormula = new ArrayList<>();
        List<double[]> dataResult = new ArrayList<>();
        String resultString;
        String[] formulaDataArray;
        Boolean firstNegative = false;
        formula = formula.replaceAll(" ", "");

        if (formula.startsWith("-")) {

            formula = formula.replaceFirst("-", "");
            firstNegative = true;

        }

        //-(1/3)x^4+x^2+3

        for (var d : formula.split("\\+")) {

            resultString = "";

            if (d.matches(".*-.*")) {

                formulaDataArray = d.split("-");

                for (var tempString : formulaDataArray) {

                    if (tempString.matches(".*\\(.*\\/.*\\).*")) {

                        List<String> fractionValues = FractionValues(tempString);
                        String tempNumber = tempString.split("\\(.*\\)")[1].split("x\\^")[1];
                        tempString = FractionValue(fractionValues);
                        resultString = tempString + "x^" + tempNumber;

                    } else {

                        resultString = "-" + tempString;

                    }

                    dataFormula.add(resultString);

                }

            } else {

                String temp = "";

                if (d.matches(".*\\(.*\\/.*\\).*")) {

                    List<String> fractionValues = FractionValues(d);
                    d = d.contains("x") ? d.split("\\(.*\\)")[1] : "";
                    temp = FractionValue(fractionValues);
                    resultString += temp.concat(d);

                } else {

                    resultString = d;

                }

                dataFormula.add(resultString);

            }

        }

        if (firstNegative == true)
            dataFormula.set(0, ("-".concat(dataFormula.get(0))));

        for (var data : dataFormula) {

            if (data.startsWith("x")) {

                if (!data.endsWith("x"))
                    dataResult.add(new double[]{0, Integer.parseInt(data.substring(data.length() - 1))});
                else
                    dataResult.add(new double[]{0, 1});

            } else {

                if (data.endsWith("x")) {

                    for (var singleX : data.split("x")) {

                        if (singleX.endsWith("-"))
                            dataResult.add(new double[]{-1, 1});
                        else
                            dataResult.add(new double[]{Double.parseDouble(singleX), 1});

                    }

                } else if (data.matches("([\\-]?.*)?x\\^[2-4]")) {

                    String[] tempList = data.split("x\\^");

                    if (tempList[0].endsWith("-")) {

                        dataResult.add(new double[]{-1, Double.parseDouble(tempList[1])});

                    } else {

                        dataResult.add(new double[]{
                            Double.parseDouble(tempList[0]),
                            Double.parseDouble(tempList[1])
                        });

                    }

                } else {

                    dataResult.add(new double[]{Double.parseDouble(data), 0});

                }

            }

        }

        return dataResult;
    }

    private List<String> FractionValues(String tempString) {

        return Arrays.asList(tempString.split("\\(*\\)*")).subList(2, 5);

    }

    private String FractionValue(List<String> fractionValues) {

        return String.valueOf(Double.valueOf(fractionValues.get(0)) / (Double.valueOf(fractionValues.get(2))));

    }

}