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
        LinkedList<Variable> variables = new LinkedList<>();
        for(Variable v : this.variables){
            domaines.put(v, new HashSet<>(v.getDomain()));
            variables.add(v);
        }
        Map<Variable, Object> newInstanciation = new HashMap<>();
        return macSolve(newInstanciation, variables, domaines);
    }


    public Map<Variable, Object> macSolve(Map<Variable, Object> instanciation, LinkedList<Variable> variables, Map<Variable, Set<Object>> domaines) {
        if(variables.isEmpty()){
            return instanciation;
        }else{
            Map<Variable, Set<Object>> domainesCopie = new HashMap<>();

            ArcConsistency arcConsistency = new ArcConsistency(this.constraints);
            boolean res = arcConsistency.enforceArcConsistency(domainesCopie);

            if(!res){
                return null;
            }

            //SI toutes les variables sont instanciées
            for(Variable v : variables) {
                for (Map.Entry<Variable, Set<Object>> entry : domaines.entrySet()) {
                    if (entry.getKey().equals(v)) {
                        Set<Object> objet = new HashSet<>(entry.getValue());
                        domainesCopie.put(v, objet);
                    }
                }
            }


            //on récupère la première variable de notre liste
            Variable v = variables.poll();
            for(Map.Entry<Variable, Set<Object>> entry : domainesCopie.entrySet()){
                if(entry.getKey().equals(v)){
                    for(Object o : entry.getValue()){
                        instanciation.put(v, o);
                        if(this.isConsistent(instanciation)){
                            Set<Object> tmpDomaines = new HashSet<>();
                            tmpDomaines.add(o);
                            domainesCopie.put(v, tmpDomaines);
                            Map<Variable, Object> newInstanciation = new HashMap<>();
                            newInstanciation = this.macSolve(instanciation, variables, domainesCopie);
                            if(newInstanciation != null){ //Solution trouvé
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
