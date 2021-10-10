package main.java;

import java.text.DecimalFormat;
import java.util.Scanner;

public class CalculatorMain {

    private static final int SPACE = 15;

    public static void main(String[] args) {
        int beforePoint;

        double result;

        Scanner stringScanner = new Scanner(System.in);

        DecimalFormat decimalFormat = new DecimalFormat();

        Calculator calculator = new Calculator();

        String output;

        decimalFormat.setGroupingUsed(false);

        while (true) {
            System.out.println("Enter your expression:");

            String expression = stringScanner.nextLine();

            try {
                result = calculator.calculate(expression);

                beforePoint = ("" + Math.round(result)).length();

                decimalFormat.setMaximumFractionDigits(SPACE - beforePoint - 1);

                output = decimalFormat.format(result).replace(",", ".");

                if (Double.isInfinite(result)) {
                    System.out.println("You can divide by zero");
                } else if (Double.isNaN(result)) {
                    System.out.println("NaN");
                } else if (output.length() > SPACE) {
                    System.out.println("Your result is too large");
                } else {
                    System.out.println("Your result: " + output);
                }
            } catch (CalculatorException e) {
                System.out.println("Incorrect expression");
            }

            System.out.println();
        }
    }
}
