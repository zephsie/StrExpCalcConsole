import java.text.DecimalFormat;
import java.util.Scanner;

public class CalculatorMain {

    public static final int SPACE = 15;

    public static void main(String[] args){
        int beforePoint = 0;

        double result;

        Scanner stringScanner = new Scanner(System.in);

        DecimalFormat decimalFormat = new DecimalFormat();

        Calculator calculator = new Calculator();

        String output;

        decimalFormat.setGroupingUsed(false);
        decimalFormat.setMaximumFractionDigits(SPACE - beforePoint - 1);

        while (true) {
            String expression = stringScanner.nextLine();

            result = calculator.calculate(expression);

            output = decimalFormat.format(result).replace(",", ".");

            System.out.println(output.length() <= SPACE ? output : "ERROR");
        }
    }
}
