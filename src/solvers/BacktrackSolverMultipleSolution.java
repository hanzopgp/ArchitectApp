package solvers;

import java.util.*;

import examples.HouseDemo;
import representation.*;

/**
 * Cette classe correspond à un solveur dont le fonctionnement permet de
 * tester des variables vérifiés précédemment pour voir si elles fonctionnent
 * avec de nouvelles variables
 */
public class BacktrackSolverMultipleSolution extends AbstractSolver{

    private List<Map<Variable, Object>> listSolution;
    private int cpt;

    /**
     * Constructeur
     * @param variables - Ensemble de variables du problème courant
     * @param constraints - Ensemble de contraintes du problème courant
     */
    public BacktrackSolverMultipleSolution(Set<Variable> variables, Set<Constraint> constraints){
        super(variables, constraints);
        this.cpt = 0;
        this.listSolution = new ArrayList<>();
    }

    @Override
    public Map<Variable, Object> solve(){
        Map<Variable, Object> instanceP = new HashMap<>();
        Queue<Variable> pile = new LinkedList<>(this.variables);
        return sra(instanceP, pile);
    }

    /**
     * Fonction récursive permettant la résolution du problème
     * @param map - Instanciation à vérifier
     * @param pile - Ensemble de variables non instanciées
     * @return
     */
    public Map<Variable, Object> sra(Map<Variable, Object> map, Queue<Variable> pile){
        //check si il y a encore des variables
        if(cpt == HouseDemo.NB_HOUSE_DATAMINING && isConsistent(map)){
            return null;
        }
        if(pile.isEmpty()){
            return map;
        }
        //On recupère la première variable de la liste
        Variable v = pile.poll();
        for(Object o : v.getDomain()){
            Map<Variable, Object> newMap = new HashMap<>(map);
            newMap.put(v, o);
            if(isConsistent(newMap)){
                if(newMap.keySet().containsAll(this.variables)){
                    this.cpt++;
                    this.listSolution.add(newMap);
                }
                Queue<Variable> newPile = new LinkedList<>(pile);
                newMap = this.sra(newMap, newPile);
//                if(newMap != null){
//                    this.cpt++;
//                    this.listSolution.add(newMap);
//                }
            }
        }
        return null;
    }

    public boolean testSolutions(){
        for(Map<Variable, Object> map : this.listSolution){
            if(!isConsistent(map)){
                return false;
            }
        }
        return true;
    }

    public List<Map<Variable, Object>> getListSolution(){
        return this.listSolution;
    }


}
