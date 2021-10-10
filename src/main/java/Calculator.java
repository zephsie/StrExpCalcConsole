package main.java;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator implements ICalculator {
    public double calculate(String expression) throws CalculatorException {
        expression = expression
                .replace(" ", "")
                .replaceFirst("^-", "0-")
                .replace("(-", "(0-")
                .replace(",", ".");

        double result;

        try {
            result = getAnswer(getReversePolishNotation(getItems(expression)));
        } catch (Exception e) {
            throw new CalculatorException();
        }

        return result;
    }

    private ArrayList<String> getItems(String expression) {
        ArrayList<String> items = new ArrayList<>();

        String regex = "([0-9]+[.]?[0-9]*)|[+\\-*^/()]";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(expression);

        while (matcher.find()){
            items.add(matcher.group());
        }

        System.out.print("items: ");

        for (String item : items) {
            System.out.print(item + " ");
        }

        System.out.println();

        return items;
    }

    private ArrayList<String> getReversePolishNotation(ArrayList<String> items) {
        Deque<String> stack = new ArrayDeque<>();

        ArrayList<String> itemsRPN = new ArrayList<>();

        for (String item : items) {
            int itemPrior = getPriority(item);

            switch (itemPrior) {
                case 0 -> itemsRPN.add(item);
                case 4 -> stack.push(item);
                case 5 -> {
                    while (getPriority(stack.peek()) != 4) {
                        itemsRPN.add(stack.pop());
                    }

                    stack.pop();
                }
                default -> {
                    if (!stack.isEmpty()) {
                        boolean isPop;

                        do {
                            isPop = false;

                            int latestItemPrior = getPriority(stack.peek());

                            if (latestItemPrior >= itemPrior && latestItemPrior < 4) {
                                itemsRPN.add(stack.pop());

                                isPop = true;
                            }
                        } while (isPop && !stack.isEmpty());
                    }

                    stack.push(item);
                }
            }
        }

        if (!stack.isEmpty()) {
            do {
                itemsRPN.add(stack.pop());
            } while (!stack.isEmpty());
        }

        System.out.print("itemsRPN: ");

        for (String itemRPN : itemsRPN) {
            System.out.print(itemRPN + " ");
        }

        System.out.println();

        return itemsRPN;
    }

    private double getAnswer(ArrayList<String> itemsRPN) throws CalculatorException {
        if (itemsRPN.contains("(")) {
            throw new CalculatorException();
        }

        double temp;

        while (itemsRPN.size() > 1) {
            for (int i = 0; i < itemsRPN.size(); i++) {
                switch (itemsRPN.get(i)) {
                    case "+": {
                        temp = Double.parseDouble(itemsRPN.get(i - 2)) + Double.parseDouble(itemsRPN.get(i - 1));
                        break;
                    }
                    case "-": {
                        temp = Double.parseDouble(itemsRPN.get(i - 2)) - Double.parseDouble(itemsRPN.get(i - 1));
                        break;
                    }
                    case "*": {
                        temp = Double.parseDouble(itemsRPN.get(i - 2)) * Double.parseDouble(itemsRPN.get(i - 1));
                        break;
                    }
                    case "/": {
                        temp = Double.parseDouble(itemsRPN.get(i - 2)) / Double.parseDouble(itemsRPN.get(i - 1));
                        break;
                    }
                    case "^": {
                        temp = Math.pow(Double.parseDouble(itemsRPN.get(i - 2)), Double.parseDouble(itemsRPN.get(i - 1)));
                        break;
                    }
                    default: continue;
                }

                itemsRPN.remove(i - 2);
                itemsRPN.remove(i - 2);
                itemsRPN.set(i - 2, "" + temp);

                System.out.print("itemsRPN: ");

                for (String itemRPN : itemsRPN) {
                    System.out.print(itemRPN + " ");
                }

                System.out.println();

                break;
            }
        }

        return Double.parseDouble(itemsRPN.get(0));
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
