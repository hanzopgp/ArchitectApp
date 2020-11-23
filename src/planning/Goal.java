package planning;

import representation.*;

import java.util.Map;

public interface Goal {
    public boolean isSatisfiedBy(Map<Variable, Object> variables);
}
