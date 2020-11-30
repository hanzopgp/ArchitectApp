package planning;
import java.util.*;
import representation.*;

/**
 * Cette classe représente la validité des buts. Cette validité est montrée 
 * par les valeurs obtenues des variables instanciées.
 */

public class BasicGoal implements Goal{

    private final Map<Variable, Object> goal;

    
    /**
     * @param goal -> but à atteindre 
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