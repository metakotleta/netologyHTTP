package com.netology.HW11;

public class Main {

    public static void main(String[] args) {

        Calculator calc = Calculator.instance.get();

        int a = calc.multiply.apply(5, 55);
        int b = calc.abs.apply(5);

        calc.println.accept(b);
    }
}
