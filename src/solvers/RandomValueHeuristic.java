package solvers;

import representation.Variable;

import java.util.*;

/**
 * Cette classe permet de mélanger un ensemble de domaines et de le retourner
 */
public class RandomValueHeuristic implements ValueHeuristic{

    private Random random;

    /**
     * Constructeur
     * @param random - Objet permettant de générer un nombre pseudo-aléatoire
     */
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
