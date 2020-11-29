package planning;

import representation.*;

import java.util.Map;

public interface Action {
    
    /**
     * Test si les préconditions sont contenues dans l'état
     * 
     * @param variables -> l'état donné qui va être testé
     * @return -> boolean donnant le résultat de l'action
     */
    boolean isApplicable(Map<Variable, Object> variables);


    /**
     * Passe à l'état suivant
     * 
     * @param variables -> état courant
     * @return -> état suivant
     */
    Map<Variable, Object> successor(Map<Variable, Object> variables);

    
    /**
     * Contient le coût de l'action
     * 
     * @return -> coût de l'action
     */
    int getCost();
}
