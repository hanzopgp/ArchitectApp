package planning;

import representation.*;

import java.util.Map;


/**
 * Interface représentant le but à atteindre
 */
public interface Goal {


    /**
     * 
     * @param variables -> état courant
     * @return -> le résultat du but
     */
    boolean isSatisfiedBy(Map<Variable, Object> variables);
}
