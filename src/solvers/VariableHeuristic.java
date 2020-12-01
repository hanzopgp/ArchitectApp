package solvers;

import representation.Variable;

import java.util.*;

/**
 *
 */
public interface VariableHeuristic {

    Variable best(Set<Variable> variables, Map<Variable, Set<Object>> domains);
}
