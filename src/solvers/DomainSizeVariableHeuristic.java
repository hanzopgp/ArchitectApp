package solvers;

import representation.Constraint;
import representation.Variable;

import java.util.Map;
import java.util.Set;

/**
 * Cette classe permet de trouver la variable qui satisfait le plus de contraintes
 * en fonction du nombre de domaines qu'elle contient
 */
public class DomainSizeVariableHeuristic implements  VariableHeuristic{

    private final Set<Variable> listVariables;
    private final Set<Constraint> listContraintes;
    private final boolean greatest;

    /**
     * Constructeur
     * @param listVariables - Ensemble de variables
     * @param listContraintes - Ensemble de contraintes
     * @param greatest - booléan indiquant ici si on préfère les variables avec
     *                   le plus grand domaine (true) ou avec le plus petit domaine (false)
     */
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
