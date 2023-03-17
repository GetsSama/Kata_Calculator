package edu.zhuravlev.katacalculator;

class DefaultExpressionValidator implements ExpressionValidator{
    private static ExpressionValidator instance;
    private Solver solver;
    private Converter converter;
    private DefaultExpressionValidator(Solver solver, Converter converter) {
        this.converter = converter;
        this.solver = solver;
    }

    public static ExpressionValidator newValidator(Solver solver, Converter converter) {
        if (instance == null)
            instance = new DefaultExpressionValidator(solver, converter);
        return instance;
    }

    @Override
    public void validate(String expr) throws Exception{
        var args = expr.split(" ");
        try {
            checkCountArgs(args);
            checkAllRomeOrAllArabic(args[0], args[2]);
            checkNumericArg(args[0]);
            checkNumericArg(args[2]);
            checkOperationArg(args[1]);

        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public void checkRomeResult(int result) throws Exception {
        if(result<1)
            throw new Exception(new ArithmeticException("Result of rome calculation can not be smallest then 1"));
    }

    @Override
    public boolean isRomeExpression(String expr) {
        return !isArabic(expr.split(" ")[0]);
    }

    private void checkCountArgs(String[] args) {
        if (args.length != 3)
            throw new UnsupportedOperationException("The input expression should have only 3 arguments f.e.: 'a' 'operation' 'b', but given " + args.length + " args.");
    }

    private void checkNumericArg(String a1) {
        if(isArabic(a1)) {
            int value = Integer.parseInt(a1);
            if(value < 1 || value > 10)
                throw new IllegalArgumentException("Value of arguments 1 and 3 must be in interval [1, 10]!");
        } else {
            char[] chars = a1.toCharArray();
            for(var sim : chars)
                if(!converter.getAvailableRomeCharacters().contains(sim))
                    throw new IllegalArgumentException("Incorrect character in rome argument! Available rome chars: " + converter.getAvailableRomeCharacters());
            int arabicFromRome = converter.convertRomeToArabic(a1);
            if(arabicFromRome > 10)
                throw new IllegalArgumentException("Value of arguments 1 or 3 must be in interval [1, 10]!");
        }
    }

    private void checkOperationArg(String operation) {
        if(!solver.getAvailableOperations().contains(operation))
            throw new UnsupportedOperationException("Unsupported arithmetic operation! Available operations: " + solver.getAvailableOperations());
    }

    private boolean isArabic(String arg) {
        try {
            Integer.valueOf(arg);
        } catch (NumberFormatException e) {
            try {
                Double.parseDouble(arg);
            } catch (NumberFormatException ex) {
                return false;
            }
            throw new IllegalArgumentException("Available numeric args must be integer but given double!");
        }
        return true;
    }

    private void checkAllRomeOrAllArabic(String a1, String a2) {
        if(!isArabic(a1)==isArabic(a2))
            throw new IllegalArgumentException("Pair of input operands must be only arabic or only rome!");
    }
}
