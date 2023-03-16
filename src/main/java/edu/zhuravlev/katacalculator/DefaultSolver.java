package edu.zhuravlev.katacalculator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

class DefaultSolver implements Solver{
    private final Map<String, BiFunction<Integer, Integer, Integer>> operations;
    private final static DefaultSolver instance = new DefaultSolver();

    private DefaultSolver() {
        BiFunction<Integer, Integer, Integer> addition = Integer::sum;
        BiFunction<Integer, Integer, Integer> subtraction = (a1, a2) -> a1 - a2;
        BiFunction<Integer, Integer, Integer> division = (a1, a2) -> a1 / a2;
        BiFunction<Integer, Integer, Integer> multiply = (a1, a2) -> a1 * a2;

        this.operations = Map.of(
                "+", addition,
                "-", subtraction,
                "/", division,
                "*", multiply
        );
    }

    public static DefaultSolver newSolver() {
        return instance;
    }

    @Override
    public String solve(int arg1, int arg2, String operation) {
        return operations.get(operation).apply(arg1, arg2).toString();
    }

    @Override
    public Set<String> getAvailableOperations() {
        return operations.keySet();
    }
}
