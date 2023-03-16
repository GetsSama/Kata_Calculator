package edu.zhuravlev.katacalculator;

import java.util.Set;

interface Solver {
    Set<String> getAvailableOperations();
    String solve(int arg1, int arg2, String operation);
}
