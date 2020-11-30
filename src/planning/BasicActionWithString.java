package planning;

import examples.HouseDemo;
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

    private final String actionString;
    private String domaineString;

    /**
     *
     * @param preconditions -> precondition respectee = on va effectuer l'action
     * @param effets -> effet donne sur l'etat
     * @param cout -> coût de l'action
     * @param actionString -> nom de l'action
     */
    public BasicActionWithString(Map<Variable, Object> preconditions, Map<Variable, Object> effets, int cout, String actionString, String domaineString) {
        super(preconditions, effets, cout);
        this.actionString = actionString;
        this.domaineString = domaineString;
    }

    public BasicActionWithString(Map<Variable, Object> preconditions, Map<Variable, Object> effets, int cout, String actionString) {
        super(preconditions, effets, cout);
        this.actionString = actionString;
    }

    @Override
    public String toString(){
        String str = "";
        if(domaineString == null){
            str += "--> " + this.actionString;
        }else{
            str += "--> " + this.actionString + ", Element du domaine : " + this.domaineString;
        }
        str += "\n";
        if(HouseDemo.FULL_DISPLAY){
            str += super.toString();
        }
        return str;

    }

}
