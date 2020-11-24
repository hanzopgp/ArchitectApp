package planning;

import representation.*;

import java.util.Map;

public interface Goal {
    boolean isSatisfiedBy(Map<Variable, Object> variables);
}
