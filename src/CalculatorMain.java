import java.text.DecimalFormat;
import java.util.Scanner;

public class CalculatorMain {
    public static final int SPACE = 10;

    public static void main(String[] args) {
        Scanner stringScanner = new Scanner(System.in);

        String expression = stringScanner.nextLine();

        Calculator calculator = new Calculator();

        double result = calculator.calculate(expression);

        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setGroupingUsed(false);

        int beforePoint = ("" + Math.round(result)).length();

        decimalFormat.setMaximumFractionDigits(SPACE - beforePoint - 1);

        String output = decimalFormat.format(result).replace(",", ".");

        System.out.println(output.length() <= SPACE ? output : "Error");
    }
}
