package planning;

import representation.Variable;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Cette classe represente les plans des algos suivant : DFS, BFS, Dijkstra et A*
 */
public interface Planner {

    /**
     * Renvoie le resultat de l'algorithme execute
     * @return -> liste des actions 
     */
    List<Action> plan();


    /**
     * Renvoie l'etat initial
     * @return -> etat initial
     */
    Map<Variable, Object> getInitialeState();


    /**
     * Renvoie les actions qui pourront etre testees
     * @return -> actions qui pourront etre testees
     */
    Set<Action> getActions();

    
    /**
     * Renvoie le but voulu
     * @return le but voulu
     */
    Goal getGoal();
}
