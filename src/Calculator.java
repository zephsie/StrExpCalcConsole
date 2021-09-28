import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator implements ICalculator {
    public double calculate(String expression) {
        expression = expression
                .replace(" ", "")
                .replaceFirst("^-", "0-")
                .replace("(-", "(0-");

        String itemsRPN = getReversePolishNotation(getItems(expression)).toString();

        System.out.println(itemsRPN);

        return 0;
    }

    private ArrayList<String> getItems(String expression) {
        ArrayList<String> items = new ArrayList<>();

        String regex = "([0-9]+[.]?[0-9]*)|[+\\-*^/()]";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(expression);

        while (matcher.find()) {
            items.add(matcher.group());
        }

        return items;
    }

    private ArrayList<String> getReversePolishNotation(ArrayList<String> items) {
        Stack<String> stack = new Stack<>();

        ArrayList<String> itemsRPN = new ArrayList<>();

        for (String item : items) {
            int itemPrior = getPriority(item);

            switch (itemPrior) {
                case 0:
                    itemsRPN.add(item);
                    break;
                case 4:
                    stack.push(item);
                    break;
                case 5:
                    while (getPriority(stack.peek()) != 4) {
                        itemsRPN.add(stack.pop());
                    }

                    stack.pop();
                    break;
                default:
                    if (stack.isEmpty()) {
                        stack.push(item);
                    } else {
                        boolean isPop;

                        do {
                            isPop = false;

                            int latestItemPrior = getPriority(stack.peek());

                            if (latestItemPrior >= itemPrior && latestItemPrior < 4) {
                                itemsRPN.add(stack.pop());

                                isPop = true;
                            }
                        } while (isPop && !stack.isEmpty());

                        stack.push(item);
                    }
                    break;
            }
        }

        if (!stack.isEmpty()) {
            do {
                itemsRPN.add(stack.pop());
            } while (!stack.isEmpty());
        }

        return itemsRPN;
    }

    private static int getPriority(String item) {
        return switch (item) {
            case ")" -> 5;
            case "(" -> 4;
            case "^" -> 3;
            case "*", "/" -> 2;
            case "+", "-" -> 1;
            default -> 0;
        };
    }
}
