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

        //Check if the formula starts with a minus
        if (formula.startsWith("-")) {

            formula = formula.replaceFirst("-", "");
            firstNegative = true;

        }

        //Splits the formula into parts, when a "+" appears
        for (var part : formula.split("\\+")) {

            resultString = "";

            //Checks if the part contains a minus, format and added it into the final string
            if (part.matches(".*-.*")) {

                formulaDataArray = part.split("-");

                for (var formulaData : formulaDataArray) {

                    if (formulaData.matches(".*\\(.*\\/.*\\).*")) {

                        List<String> fractionValues = FractionValues(formulaData);
                        String tempNumber = formulaData.split("\\(.*\\)")[1].split("x\\^")[1];
                        formulaData = FractionValue(fractionValues);
                        resultString = formulaData + "x^" + tempNumber;

                    } else {

                        resultString = "-" + formulaData;

                    }

                    dataFormula.add(resultString);

                }

            } else {

                String temp = "";

                if (part.matches(".*\\(.*\\/.*\\).*")) {

                    List<String> fractionValues = FractionValues(part);
                    part = part.contains("x") ? part.split("\\(.*\\)")[1] : "";
                    temp = FractionValue(fractionValues);
                    resultString += temp.concat(part);

                } else {

                    resultString = part;

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

    private List<String> FractionValues(String tempFormulaString) {

        return Arrays.asList(tempFormulaString.split("\\(*\\)*")).subList(2, 5);

    }

    private String FractionValue(List<String> fractionValues) {

        return String.valueOf(Double.valueOf(fractionValues.get(0)) / (Double.valueOf(fractionValues.get(2))));

    }

}