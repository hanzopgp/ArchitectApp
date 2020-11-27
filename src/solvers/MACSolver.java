package solvers;

import representation.Constraint;
import representation.Variable;

import java.util.*;

public class MACSolver extends AbstractSolver {

    public MACSolver(Set<Variable> variables, Set<Constraint> constraints) {
        super(variables, constraints);
    }

    @Override
    public Map<Variable, Object> solve() {
        Map<Variable, Set<Object>> domaines = new HashMap<>();
        for(Variable v : this.variables){
            domaines.put(v, new HashSet<>(v.getDomain()));
        }
        return macSolve(domaines, new HashMap<>());
    }

    public Map<Variable, Object> macSolve(Map<Variable, Set<Object>> domaines, Map<Variable, Object> instances){

        if(this.variables.size() == 0 && this.isConsistent(instances)){
            return instances;
        }

        ArrayList<Variable> variablesNonInstancies = new ArrayList<>();
        //SI toutes les variables sont instanciées
        for(Variable v : this.variables){
            if(instances.containsKey(v) == false){
                variablesNonInstancies.add(v);
            }
        }



        return null;
    }
}
