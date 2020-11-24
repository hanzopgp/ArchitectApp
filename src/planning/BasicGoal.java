package planning;
import java.util.*;
import representation.*;

public class BasicGoal implements Goal{

    private Map<Variable, Object> goal;

    public BasicGoal(Map<Variable, Object> goal){
        this.goal = goal;
    }

    public Map<Variable, Object> getGoal(){
        return this.goal;
    }

    @Override
    public boolean isSatisfiedBy(Map<Variable, Object> state){
        if(state.entrySet().containsAll(this.goal.entrySet())){
            return true;
        }else{
            return false;
        }
    }
}