package solvers;

import representation.Constraint;
import representation.Variable;

import java.util.*;

/**
 * Cette classe permet de trouver la variable qui satisfait le plus de contraintes
 */
public class NbConstraintsVariableHeuristic implements  VariableHeuristic{

    private Set<Variable> listVariables;
    private Set<Constraint> listContraintes;
    private boolean most;

    /**
     * Constructeur
     * @param listVariables - Ensemble de variables
     * @param listContraintes - Ensemble de contraintes
     * @param most - booléen indiquant si on préfère que les variables apparaissant
     *               dans le plus de contraintes (true) ou dans le moins de contraintes (false)
     */
    public NbConstraintsVariableHeuristic(Set<Variable> listVariables, Set<Constraint> listContraintes, boolean most){
        this.listVariables = listVariables;
        this.listContraintes = listContraintes;
        this.most = most;
    }

    @Override
    public Variable best (Set<Variable> variables, Map<Variable, Set<Object>> domains){
        Variable result = null;
        int best = 0;
        for(Variable tmpVar : variables){
            int tmp = 0;
            for(Constraint tmpConstraints : this.listContraintes){
                if(tmpConstraints.getScope().contains(tmpVar)){
                    tmp++;
                }
            }
            if((this.most && tmp>best) || (!this.most && (tmp < best || result == null))){
                result = tmpVar;
                best = tmp;
            }
        }
        return result;
    }
}
