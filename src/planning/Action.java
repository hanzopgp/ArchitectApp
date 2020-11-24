package planning;

import representation.*;

import java.util.Map;

public interface Action {
    boolean isApplicable(Map<Variable, Object> variables);
    Map<Variable, Object> successor(Map<Variable, Object> variables);
    int getCost();
}
