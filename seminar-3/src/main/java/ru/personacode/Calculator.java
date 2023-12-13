package ru.personacode;

public class Calculator {
    public static <T extends Number, K extends Number> double sum(T num1, K num2) {

        return num1.doubleValue() + num2.doubleValue();
    }

    public static <T extends Number, K extends Number> double multiply(T num1, K num2) {
        return num1.doubleValue() * num2.doubleValue();
    }

    public static <T extends Number, K extends Number> double divide(T num1, K num2) {
        return num1.doubleValue() / num2.doubleValue();
    }

    public static <T extends Number, K extends Number> double subtract(T num1, K num2) {
        return num1.doubleValue() - num2.doubleValue();
    }
}

