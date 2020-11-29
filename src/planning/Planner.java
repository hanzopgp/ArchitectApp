package planning;

import representation.Variable;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Cette classe représente les plans des algos suivant : DFS, BFS, Dijkstra et A*
 */
public interface Planner {

    /**
     * Renvoie le résultat de l'algorithme executé
     * @return -> liste des actions 
     */
    List<Action> plan();


    /**
     * Renvoie l'état initial
     * @return -> état initial
     */
    Map<Variable, Object> getInitialeState();


    /**
     * Renvoie les actions qui pourront être testées
     * @return -> actions qui pourront être testées
     */
    Set<Action> getActions();

    
    /**
     * Renvoie le but voulu
     * @return le but voulu
     */
    Goal getGoal();
}
