package solvers;

import representation.Constraint;
import representation.Variable;

import java.util.Map;
import java.util.Set;

public class DomainSizeVariableHeuristic implements  VariableHeuristic{

    private Set<Variable> listVariables;
    private Set<Constraint> listContraintes;
    private boolean greatest;

    public DomainSizeVariableHeuristic(Set<Variable> listVariables, Set<Constraint> listContraintes, boolean greatest){
        this.listVariables = listVariables;
        this.listContraintes = listContraintes;
        this.greatest = greatest;
    }

    @Override
    public Variable best (Set<Variable> variables, Map<Variable, Set<Object>> domains){
        Variable result = null;
        int best = 0;
        for(Variable tmpVar : variables){
            int tmp = domains.get(tmpVar).size();
            if((this.greatest && tmp>best) || (!this.greatest && (tmp < best || result == null))){
                result = tmpVar;
                best = tmp;
            }
        }
        return result;
    }



}
