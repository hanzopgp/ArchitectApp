package planning;
import representation.*;
import java.util.*;

/**
 * Interface permettant d'estimer un meilleur chemin pour aller a une solution 
 */
public interface Heuristic{

    /**
     * Estimation d'un coût
     * 
     * @param state -> etat courant
     * @return -> le coût estime pour aller a l'etat suivant
     */
    float estimate(Map<Variable, Object> state);
}