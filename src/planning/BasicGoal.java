package planning;

import representation.Variable;

import java.util.Collection;
import java.util.Map;

//But final qu'on souhaite
public class BasicGoal implements Goal {

    private final Map<Variable, Object> etat;

    public BasicGoal(Map<Variable, Object> etat){
        this.etat = etat;
    }

    @Override
    //on check si toute les variables passés en paramètres sont les même que
    //les variables de notre état final.
    public boolean isSatisfiedBy(Map<Variable, Object> variables){
        return variables.entrySet().containsAll(etat.entrySet());
    }
}
