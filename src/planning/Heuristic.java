package planning;
import representation.*;
import java.util.*;


/**
 * Interface permettant d'estimer un meilleur chemin pour aller à une solution 
 */
public interface Heuristic{


    /**
     * Estimation d'un coût
     * 
     * @param state -> état courant
     * @return -> le coût estimé pour aller à l'état suivant
     */
    float estimate(Map<Variable, Object> state);
}