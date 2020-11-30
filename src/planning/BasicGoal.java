package planning;
import java.util.*;
import representation.*;

/**
 * Cette classe represente la validite des buts. Cette validite est montree 
 * par les valeurs obtenues des variables instanciees.
 */

public class BasicGoal implements Goal{

    private final Map<Variable, Object> goal;
    
    /**
     * @param goal -> but a atteindre 
     */
    public BasicGoal(Map<Variable, Object> goal){
        this.goal = goal;
    }

    public Map<Variable, Object> getGoal(){
        return this.goal;
    }

    @Override
    public boolean isSatisfiedBy(Map<Variable, Object> state){
        return state.entrySet().containsAll(this.goal.entrySet());
    }
}