package solvers;
import java.util.*;
import representation.*;
import solvers.ArcConsistency;

public class MACSolver extends AbstractSolver{

    public MACSolver(Set<Variable> variables, Set<Constraint> constraints){
        super(variables, constraints);
    }

    @Override 
    public Map<Variable, Object> solve(){
        Map<Variable, Set<Object>> domaines = new HashMap<>();
        for(Variable v : this.variables){
            domaines.put(v, new HashSet<>(v.getDomain()));
        }
        return macSolve(domaines, new HashMap<>());
    }


    public Map<Variable, Object> macSolve(Map<Variable, Set<Object>> domaines, Map<Variable, Object> instanciation){
        List<Variable> notInstanciation = new ArrayList<>();
        Map<Variable, Set<Object>> domainesCopies = new HashMap<>(domaines);
        Map<Variable, Object> secondInstanciation = new HashMap<>(instanciation);
        ArcConsistency arcConsistency = new ArcConsistency(constraints);
        boolean res = arcConsistency.enforceArcConsistency(domainesCopies);
        if(res == false){
            return null;
        }
        
        if(notInstanciation.size() == 0 && this.isConsistent(instanciation)){
            return instanciation;
        }

        for(Variable v : this.variables){
            if(instanciation.containsKey(v) == false){
                notInstanciation.add(v);
            }
        }        
        
        for(Object dom : domainesCopies.get(notInstanciation.get(0))){
            secondInstanciation.put(notInstanciation.get(0), dom);
            if(this.isConsistent(secondInstanciation)){
                if(secondInstanciation.keySet().containsAll(this.variables)){
                    return secondInstanciation;
                }
                secondInstanciation = macSolve(domainesCopies, secondInstanciation);
                if(secondInstanciation != null){
                    return secondInstanciation;
                }
            }
        }
        return null;
    }


}
