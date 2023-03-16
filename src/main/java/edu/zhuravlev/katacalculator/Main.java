package edu.zhuravlev.katacalculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Converter conv = DefaultConverter.newConverter();
        Solver solver = DefaultSolver.newSolver();
        ExpressionValidator validator = DefaultExpressionValidator.newValidator(solver, conv);

        try (var scn = new Scanner(System.in)) {
            while (scn.hasNext()) {
                try {
                    validator.validate(scn.nextLine());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public static String calc(String input) {
        return null;
    }
}