package org.example.mathpower.helpers;

import java.util.ArrayList;
import java.util.List;

public class HornerSchema {

    public List<Double> ReturnHornerSchemaResults(List<double[]> formulaData) {

        ABCFormula abcFormula = new ABCFormula();
        List<double[]> x2Values;
        double guessedRootPoint;
        List<Double> rootPoints = new ArrayList<>();

        if (formulaData.get(0)[1] >= 3) {

            guessedRootPoint = GuessARootPoint(formulaData);
            List<double[]> tempResult0 = HornerSchema(formulaData, guessedRootPoint);

            if (formulaData.get(0)[1] == 4) {

                if (tempResult0.get(tempResult0.size() - 1)[1] == 0)
                    rootPoints.add(guessedRootPoint);

                guessedRootPoint = GuessARootPoint(tempResult0);
                x2Values = HornerSchema(tempResult0, guessedRootPoint).subList(0, 3);

                if (x2Values.get(x2Values.size() - 1)[1] == 0)
                    rootPoints.add(guessedRootPoint);

            } else {

                if (tempResult0.get(tempResult0.size() - 1)[1] == 0)
                    rootPoints.add(guessedRootPoint);

                x2Values = tempResult0;

            }

        } else {

            guessedRootPoint = GuessARootPoint(formulaData);
            x2Values = HornerSchema(formulaData, guessedRootPoint);

        }

        double [] tempABC = abcFormula.GetValues(x2Values);

        for (var data: tempABC) {

            if (Double.isFinite(data))
                rootPoints.add(data);

        }

        return rootPoints;

    }

    protected List<double[]> HornerSchema(List<double[]> formulaData, double guessedRootPoint) {

        List<double[]> newFormula = new ArrayList<>();
        double grade = formulaData.get(0)[1];
        double count = 0;

        for (var data: formulaData) {

            if (grade == 0)
                break;

            if (count == 0) {

                if (data[0] == 0)
                    newFormula.add(new double[] { 1, data[1] - 1 });
                else
                    newFormula.add(new double[] { data[0], data[1] - 1 });

                grade--;

            } else if (data[1] == grade){

                if (data[0] == 0)
                    newFormula.add(new double[]{
                        1 + (guessedRootPoint * newFormula.get((int) count - 1)[0]),
                        data[1] - 1
                    });
                else
                    newFormula.add(new double[]{
                        data[0] + (guessedRootPoint * newFormula.get((int) count - 1)[0]),
                        data[1] - 1
                    });

                grade--;

            } else {

                while ((data[1] - 1) != --grade) {

                    newFormula.add(new double[]{
                        0 + (guessedRootPoint * newFormula.get((int) count - 1)[0]),
                        grade
                    });

                    count++;

                }

                if (data[0] == 0) {

                    newFormula.add(new double[]{
                        1 + (guessedRootPoint * newFormula.get((int) count - 1)[0]),
                        data[1] - 1
                    });

                } else if (data[1] != 0) {

                    newFormula.add(new double[]{
                        data[0] + (guessedRootPoint * newFormula.get((int) count - 1)[0]),
                        data[1] - 1
                    });

                }

            }

            if (grade == formulaData.size() - 1 && (data[1] > 0 || data[1] < 0)){

                while (grade != 0) {

                    newFormula.add(new double[] {
                        0 + (guessedRootPoint * newFormula.get((int) count)[0]),
                        --grade
                    });

                }

            }

            count++;

        }

        return newFormula;

    }

    protected double GuessARootPoint(List<double[]> formulaData) {

        YValue yValues = new YValue();
        Bisection bisection = new Bisection();
        List<double[]> yCheckValues = new ArrayList<>();
        double xCount;
        double guessedRootPoint = Double.NaN;
        double yValue = 1;

        for (xCount = -3; yValue != 0 && xCount <= 3; xCount++)
            yValue = yValues.GetYValue(formulaData, xCount);

        if (yValue == 0) {

            guessedRootPoint = xCount - 1;

        } else {

            for (xCount = -5; xCount <= 5; xCount++) {

                yValue = yValues.GetYValue(formulaData, xCount);

                if (yValue == 0) {

                    guessedRootPoint = xCount;
                    break;

                } else {

                    if (yCheckValues.isEmpty()) {

                        yCheckValues.add(0, new double[] {
                                xCount,
                                yValue
                        });
                        yCheckValues.add(1, new double[2]);

                    } else {

                        yCheckValues.get(1)[0] = yCheckValues.get(0)[0];
                        yCheckValues.get(1)[1] = yCheckValues.get(0)[1];
                        yCheckValues.get(0)[0] = xCount;
                        yCheckValues.get(0)[1] = yValue;

                        if (yCheckValues.get(0)[1] < 0 && 0 < yCheckValues.get(1)[1]) {

                            guessedRootPoint = bisection.ReturnBisectionValues(
                                    yCheckValues.get(0),
                                    yCheckValues.get(1),
                                    formulaData);
                            break;

                        } else if (yCheckValues.get(1)[1] < 0 && 0 < yCheckValues.get(0)[1]) {

                            guessedRootPoint = bisection.ReturnBisectionValues(
                                    yCheckValues.get(1),
                                    yCheckValues.get(0),
                                    formulaData);
                            break;

                        }

                    }

                }

            }

        }

        return guessedRootPoint;

    }

}