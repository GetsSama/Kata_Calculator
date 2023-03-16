package edu.zhuravlev.katacalculator;

import java.util.Scanner;

public class Main {
    private final static Solver solver = DefaultSolver.newSolver();
    private final static Converter converter = DefaultConverter.newConverter();
    private final static ExpressionValidator validator = DefaultExpressionValidator.newValidator(solver, converter);

    public static void main(String[] args) {

    }

    public static String calc(String input) throws Exception {
        validator.validate(input);

        if(validator.isRomeExpression(input)) {
            var args = input.split(" ");
            int arg1 = converter.convertRomeToArabic(args[0]);
            int arg2 = converter.convertRomeToArabic(args[2]);
            var result = solver.solve(arg1, arg2, args[1]);
            validator.checkRomeResult(Integer.parseInt(result));
            return converter.convertArabicToRome(Integer.parseInt(result));
        } else {
            var args = input.split(" ");
            int arg1 = Integer.valueOf(args[0]);

        }

        return null;
    }
}