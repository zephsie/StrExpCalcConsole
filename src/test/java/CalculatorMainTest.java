import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class CalculatorMainTest {

    @ParameterizedTest
    @CsvFileSource(resources = "expressions.csv", delimiter = '=')
    void test(String expression, double expected) throws Exception {
        Calculator calculator = new Calculator();

        double result = calculator.calculate(expression);

        Assertions.assertEquals(expected, result, 0);
    }

}