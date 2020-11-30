package planning;

import representation.Variable;

import java.util.Map;


/**
 * Cette classe représente des actions qui vont être testées.
 * Une action sera caractérisée en termes de préconditions qui devront être
 * vraies pour qu'elle puisse être éxécutée, et enterme d'effets qui
 * s'ensuivent quand elle est éxécutée.
 *
 */
public class BasicActionWithString extends BasicAction implements Action{

    private String actionString;

    /**
     *
     * @param preconditions -> précondition respectée = on va effectuer l'action
     * @param effets -> effet donné sur l'état
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
