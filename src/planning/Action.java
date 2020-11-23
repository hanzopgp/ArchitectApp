package planning;

import representation.*;

import java.util.Map;

public interface Action {
    public boolean isApplicable(Map<Variable, Object> variables);
    public Map<Variable, Object> successor(Map<Variable, Object> variables);
    public int getCost();
}
