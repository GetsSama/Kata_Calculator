package edu.zhuravlev.katacalculator;

public class DefaultExpressionValidator implements ExpressionValidator{
    private static final ExpressionValidator instance = new DefaultExpressionValidator();

    private DefaultExpressionValidator() {
    }

    public static ExpressionValidator newValidator() {
        return instance;
    }

    @Override
    public void validate(String expr) throws Exception{

    }
}
