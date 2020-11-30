package solvers;
import java.util.*;
import representation.*;

/**
 * Cette classe correspond a un solveur dont le fonctionnement permet de
 * verifier si un ensemble de domaines de variable est dit "arc-coherent" avec
 * les contraintes de l'instance MACSolver, afin de faciliter la recherche d'une
 * solution.
 */
public class MACSolver extends AbstractSolver{

    /**
     * Constructeur
     * @param variables - Ensemble de variables
     * @param constraints - Ensemble de contraintes
     */
    public MACSolver(Set<Variable> variables, Set<Constraint> constraints){
        super(variables, constraints);
    }

    @Override 
    public Map<Variable, Object> solve(){
        Map<Variable, Set<Object>> domaines = new HashMap<>();
        for(Variable v : this.variables){
            domaines.put(v, new HashSet<>(v.getDomain()));
        }
        return macSolve(new HashMap<Variable, Object>(), new LinkedList<>(this.variables), domaines);
    }


    /**
     * Fonction recursive permettant la resolution du probleme
     * @param instanciation - Instanciation a verifier
     * @param variables - Liste de variables de l'instance actuelle
     * @param domaines - Domaines
     * @return Instanciation
     */
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

            //SI toutes les variables sont instanciees
            for(Variable v : variables) {
                for (Map.Entry<Variable, Set<Object>> entry : domaines.entrySet()) {
                    if (entry.getKey().equals(v)) {
                        Set<Object> objet = new HashSet<>(entry.getValue());
                        domainesCopie.put(v, objet);
                    }
                }
            }


            //on recupere la premiere variable de notre liste
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
                            if(newInstanciation != null){ //Solution trouve
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
