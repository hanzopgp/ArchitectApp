package solvers;
import  representation.*;
import solvers.ValueHeuristic;
import solvers.VariableHeuristic;

import java.util.*;

/**
 * Cette classe correspond a un solveur dont l'objectif est de
 * resoudre un probleme d'une maniere "heuristique".
 */
public class HeuristicMACSolver extends AbstractSolver{

    protected VariableHeuristic variableHeuristic;
    protected ValueHeuristic valueHeuristic;

    /**
     * Constructeur
     * @param variables - Ensemble de variables
     * @param constraints - Ensemble de contraintes
     * @param variableHeuristic - Variable heuristique
     * @param valueHeuristic - Valeur heuristique
     */
    public HeuristicMACSolver(Set<Variable> variables, Set<Constraint> constraints, VariableHeuristic variableHeuristic, ValueHeuristic valueHeuristic){
        super(variables, constraints);
        this.variableHeuristic = variableHeuristic;
        this.valueHeuristic = valueHeuristic;
    }

    @Override
    public Map<Variable, Object> solve(){
        Map<Variable, Set<Object>> domaines = new HashMap<>();
        for(Variable v : this.variables){
            domaines.put(v, v.getDomain());
        }
        return macHeuristic(new HashMap<>(), new LinkedList<>(this.variables), domaines);
    }

    /**
     * Cette methode est un algorithme permettant de trouver une solution a un probleme,
     * en utilisant le même principe que pour MACSolver, mais en choisissant la meilleure variable
     * de maniere heuristique.
     * @param instanciation - Instanciation a verifier
     * @param variables - Liste de variables de l'instance actuelle
     * @param domaines - Domaines
     * @return Instanciation
     */
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

            //Si toutes les variables sont instanciees
            for(Variable v : variables){
                for(Map.Entry<Variable, Set<Object>> entry : domaines.entrySet()){
                    if(entry.getKey().equals(v)){
                        Set<Object> object = new HashSet<>(entry.getValue());
                        domainesCopies.put(v, object);
                    }
                }
            }

            //On recupere la meilleure variable heuristique
            Variable v = this.variableHeuristic.best(new HashSet<>(variables), domainesCopies);
            for(Map.Entry<Variable, Set<Object>> entry : domainesCopies.entrySet()){
                if(entry.getKey().equals(v)){
                    for(Object o : this.valueHeuristic.ordering(v, domainesCopies.get(v))){
                        instanciation.put(v, o);
                        if(this.isConsistent(instanciation)){
                            Set<Object> tmpDomaines = new HashSet<>();
                            tmpDomaines.add(o);
                            domainesCopies.put(v, tmpDomaines);
                            if (instanciation.keySet().containsAll(this.variables)) {
                                return instanciation;
                            }
                            Map<Variable, Object> newInstanciation = new HashMap<>();
                            newInstanciation = this.macHeuristic(instanciation, variables, domainesCopies);
                            // Solution trouvee
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

}
