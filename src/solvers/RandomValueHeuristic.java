package solvers;

import representation.Variable;

import java.util.*;

public class RandomValueHeuristic implements ValueHeuristic{

    private Random random;

    public RandomValueHeuristic(Random random){
        this.random = random;
    }

    @Override
    public List<Object> ordering(Variable variable, Set<Object> domain) {
        ArrayList result = new ArrayList(domain);
        Collections.shuffle(result, this.random);
        return result;
    }
}
