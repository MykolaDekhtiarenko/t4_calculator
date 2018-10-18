package com.epam.training;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Calculator {

    public static void main(String[] args) throws IOException {
        System.out.println("Enter an expression: ");

        String line = new BufferedReader(new InputStreamReader(System.in)).readLine();
        System.out.println(line);

        String[] operations = line.split("[0-9]+");
        System.out.println(Arrays.toString(operations));

        String[] numbers = line.split("[" + Pattern.quote("+-*/") + "]");
        System.out.println(Arrays.toString(numbers));

        int result = calculate(convertOperations(operations), convertNumbers(numbers));
        System.out.println(result);
    }

    private static List<Integer> convertNumbers(String[] numbers) {
        return Arrays.stream(numbers).map(Integer::valueOf).collect(Collectors.toList());
    }

    private static List<Operation> convertOperations(String[] operations) {
        return Arrays.stream(operations).skip(1).map(Operation::of).collect(Collectors.toList());
    }

    private static int calculate(List<Operation> operations, List<Integer> numbers) {
        int index = 0;
        while (index < operations.size()) {
            if (operations.get(index) == Operation.MULTIPLICATION || operations.get(index) == Operation.DIVISION) {
                perform(numbers, operations, index);
            } else {
                index++;
            }
        }

        index = 0;
        while (index < operations.size()) {
            if (operations.get(index) == Operation.ADDITION || operations.get(index) == Operation.SUBTRACTION) {
                perform(numbers, operations, index);
            } else {
                index++;
            }
        }
        return numbers.get(0);
    }

    private static void perform(List<Integer> numbers, List<Operation> operations, int index) {
        numbers.set(index + 1, operations.get(index).apply(numbers.get(index), numbers.get(index + 1)));
        numbers.remove(index);
        operations.remove(index);
    }

}







