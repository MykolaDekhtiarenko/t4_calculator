package com.epam.training;

import java.util.Arrays;
import java.util.function.BinaryOperator;

public enum Operation implements Comparable<Operation> {
    MULTIPLICATION("*", (x, y) -> x * y),
    DIVISION("/", (x, y) -> x / y),
    ADDITION("+", (x, y) -> x + y),
    SUBTRACTION("-", (x, y) -> x - y);

    private String symbol;
    private BinaryOperator<Integer> operator;

    Operation(String symbol, BinaryOperator<Integer> operator) {
        this.symbol = symbol;
        this.operator = operator;
    }

    public int apply(int x, int y) {
        return operator.apply(x, y);
    }

    public static Operation of(String symbol) {
        return Arrays.stream(Operation.values())
                .filter(s -> s.symbol.equals(symbol))
                .findAny()
                .orElseThrow(UnsupportedOperationException::new);
    }

    public String getSymbol() {
        return symbol;
    }
}
