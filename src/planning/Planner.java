package planning;

import representation.Variable;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Planner {
    List<Action> plan();
    Map<Variable, Object> getInitialeState();
    Set<Action> getActions();
    Goal getGoal();
}
