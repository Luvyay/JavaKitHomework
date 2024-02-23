package Homework3;

public class Calculator {
    public static <T extends Number, U extends Number> double sum(T value1, U value2) {
        return Double.parseDouble(value1.toString()) + Double.parseDouble(value2.toString());
    }

    public static <T extends Number, U extends Number> double subtract(T value1, U value2) {
        return Double.parseDouble(value1.toString()) - Double.parseDouble(value2.toString());
    }

    public static <T extends Number, U extends Number> double multiply(T value1, U value2) {
        return Double.parseDouble(value1.toString()) * Double.parseDouble(value2.toString());
    }

    public static <T extends Number, U extends Number> double divide(T value1, U value2) {
        return Double.parseDouble(value1.toString()) / Double.parseDouble(value2.toString());
    }
}
