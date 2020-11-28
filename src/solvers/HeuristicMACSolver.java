/*package solvers;
import  representation.*;
import solvers.ValueHeuristic;
import solvers.VariableHeuristic;

import java.util.*;

public class HeuristicMACSolver extends AbstractSolver{

    protected VariableHeuristic variableHeuristic;
    protected ValueHeuristic valueHeuristic; 

    public HeuristicMACSolver(Set<Variable> variables, Set<Constraint> constraints, VariableHeuristic variableHeuristic, ValueHeuristic valueHeuristic){
        super(variables, constraints);
        this.variableHeuristic = variableHeuristic;
        this.valueHeuristic = valueHeuristic;
    }

    @Override 
    public Map<Variable, Object> solve(){
        Map<Variable, Set<Object>> domaines = new HashMap<>();
        LinkedList<Variable> variables = new LinkedList<>();
        for(Variable v : this.variables){
            domaines.put(v, v.getDomain());
            variables.add(v);
        }
        Map<Variable, Object> newInstanciation = new HashMap<>();
        return macHeuristic(newInstanciation, variables, domaines);
    }

    public Map<Variable, Object> macHeuristic(Map<Variable, Object> instanciation, LinkedList<Variable> variables, Map<Variable, Set<Object>> domaines){
        if(variables.isEmpty()){
            return instanciation;
        }else{
            Map<Variable, Set<Object>> domainesCopies = new HashMap<>();
            ArcConsistency arcConsistency = new ArcConsistency(this.constraints);
            boolean res = arcConsistency.enforceArcConsistency(domainesCopies);
            if(!res){
                return null;
            }

            //Si toutes les variables sont instanciées
            for(Variable v : variables){
                for(Map.Entry<Variable, Set<Object>> entry : domaines.entrySet()){
                    if(entry.getKey().equals(v)){
                        Set<Object> object = new HashSet<>(entry.getValue());
                        domainesCopies.put(v, object);
                    }
                }
            }

            //On récupère la première variable de notre liste
            Variable v = this.variableHeuristic.best(new HashSet<>(variables), domainesCopies);
            for(Map.Entry<Variable, Set<Object>> entry : domainesCopies.entrySet()){
                if(entry.getKey().equals(v)){
                    for(Object o : this.valueHeuristic.ordering(v, domainesCopies.get(v))){
                        instanciation.put(v, o);
                        if(this.isConsistent(instanciation)){
                            Set<Object> tmpDomaines = new HashSet<>();
                            tmpDomaines.add(o);
                            domainesCopies.put(v, tmpDomaines);
                            Map<Variable, Object> newInstanciation = new HashMap<>();
                            newInstanciation = this.macHeuristic(instanciation, variables, domainesCopies);
                            // Solution trouvée
                            if(newInstanciation != null){
                                return newInstanciation;
                            }
                        }
                        instanciation.remove(v);
                    }
                }
            }
            variables.add(v);
        }
        return null;
    }


    public VariableHeuristic getVariableHeuristic(){
        return this.variableHeuristic;
    }

    public ValueHeuristic getValueHeuristic(){
        return this.valueHeuristic;
    }

}*/
