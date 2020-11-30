package planning;

import representation.Variable;

import java.util.Map;


/**
 * Cette classe represente des actions qui vont etre testees.
 * Une action sera caracterisee en termes de preconditions qui devront etre
 * vraies pour qu'elle puisse etre executee, et enterme d'effets qui
 * s'ensuivent quand elle est executee.
 *
 */
public class BasicActionWithString extends BasicAction implements Action{

    private String actionString;

    /**
     *
     * @param preconditions -> precondition respectee = on va effectuer l'action
     * @param effets -> effet donne sur l'etat
     * @param cout -> coût de l'action
     * @param actionString -> nom de l'action
     */
    public BasicActionWithString(Map<Variable, Object> preconditions, Map<Variable, Object> effets, int cout, String actionString) {
        super(preconditions, effets, cout);
        this.actionString = actionString;
    }

    @Override
    public String toString(){
        String str = "--> " + this.actionString + "\n";
        str += super.toString();
        return str;

    }

}
