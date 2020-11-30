package planning;

import representation.*;

import java.util.Map;

public interface Action {
    
    /**
     * Test si les preconditions sont contenues dans l'etat
     * 
     * @param variables -> l'etat donne qui va être teste
     * @return -> boolean donnant le resultat de l'action
     */
    boolean isApplicable(Map<Variable, Object> variables);


    /**
     * Passe a l'etat suivant
     * 
     * @param variables -> etat courant
     * @return -> etat suivant
     */
    Map<Variable, Object> successor(Map<Variable, Object> variables);

    
    /**
     * Contient le coût de l'action
     * 
     * @return -> coût de l'action
     */
    int getCost();
}
