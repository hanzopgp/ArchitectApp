package planning;

import representation.Variable;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Planner {
    public List<Action> plan();
    public Map<Variable, Object> getInitialeState();
    public Set<Action> getActions();
    public Goal getGoal();
}
