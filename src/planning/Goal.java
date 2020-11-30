package planning;

import representation.*;

import java.util.Map;


/**
 * Interface representant le but a atteindre
 */
public interface Goal {


    /**
     * 
     * @param variables -> etat courant
     * @return -> le resultat du but
     */
    boolean isSatisfiedBy(Map<Variable, Object> variables);
}
