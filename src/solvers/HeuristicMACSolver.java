package solvers;

import java.util.*;

import representation.Constraint;
import representation.Variable;

public class HeuristicMACSolver extends AbstractSolver {

    private Set<Variable>listVariable;
    private Set<Constraint>listContraintes;
    private VariableHeuristic variableHeuristic;
    private ValueHeuristic valueHeuristic;

    public HeuristicMACSolver(Set<Variable>listVariable, Set<Constraint>listContraintes, VariableHeuristic variableHeuristic, ValueHeuristic valueHeuristic){
        super(listVariable, listContraintes);
        this.variableHeuristic = variableHeuristic;
        this.valueHeuristic = valueHeuristic;
    }

    @Override
    public Map<Variable, Object> solve(){
        return null;
    }




}
