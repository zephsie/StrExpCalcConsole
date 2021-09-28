import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator implements ICalculator {
    public double calculate(String expression) {
        expression = expression
                .replace(" ", "")
                .replaceFirst("^-", "0-")
                .replace("(-", "(0-");

        String items = getItems(expression).toString();

        System.out.println(items);

        return 0;
    }

    private ArrayList<String> getItems(String expression){
        ArrayList<String> items = new ArrayList<>();

        String regex = "([0-9]+[.]?[0-9]*)|[+\\-*^/()]";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(expression);

        while (matcher.find()){
            items.add(matcher.group());
        }

        return items;
    }
}