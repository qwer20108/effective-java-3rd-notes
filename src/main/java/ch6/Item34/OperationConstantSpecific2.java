package ch6.Item34;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public enum OperationConstantSpecific2 {
    PLUS("+") {
        public double apply(double x, double y) {
            return x + y;
        }
    }, MINUS("-") {
        public double apply(double x, double y) {
            return x - y;
        }
    }, TIMES("*") {
        public double apply(double x, double y) {
            return x * y;
        }
    }, DIVIDE("/") {
        public double apply(double x, double y) {
            return x / y;
        }
    };
    private final String symbol;

    OperationConstantSpecific2(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }

    public abstract double apply(double x, double y);

    // Implementing a fromString method on an enum type
    private static final Map<String, OperationConstantSpecific2> stringToEnum = Stream.of(values()).collect(toMap(Object::toString, e -> e));// Returns Operation for string, if any

    public static Optional<OperationConstantSpecific2> fromString(String symbol) {
        return Optional.ofNullable(stringToEnum.get(symbol));
    }

    // Switch on an enum to simulate a missing method
    public static OperationConstantSpecific2 inverse(OperationConstantSpecific2 op) {
        switch (op) {
            case PLUS:
                return OperationConstantSpecific2.MINUS;
            case MINUS:
                return OperationConstantSpecific2.PLUS;
            case TIMES:
                return OperationConstantSpecific2.DIVIDE;
            case DIVIDE:
                return OperationConstantSpecific2.TIMES;
            default:
                throw new AssertionError("Unknown op: " + op);
        }
    }
}
