import java.util.Scanner;

public class CalculatorMain {

    public static void main(String[] args) {
        Scanner stringScanner = new Scanner(System.in);

        String expression = stringScanner.nextLine();

        Calculator calculator = new Calculator();

        double result = calculator.calculate(expression);

        System.out.println(result);
    }
}
