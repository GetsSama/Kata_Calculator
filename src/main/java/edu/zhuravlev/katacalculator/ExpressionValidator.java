package edu.zhuravlev.katacalculator;

interface ExpressionValidator {
    void validate(String expr) throws Exception;
    void checkRomeResult(int result) throws Exception;
}
