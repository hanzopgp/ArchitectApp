package planning;
import representation.*;
import java.util.*;


public interface Heuristic{

    public float estimate(Map<Variable, Object> state);
}