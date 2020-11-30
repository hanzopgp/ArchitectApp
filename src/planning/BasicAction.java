package planning;

import representation.Variable;

import java.util.HashMap;
import java.util.Map;


/**
 * Cette classe représente des actions qui vont être testées.
 * Une action sera caractérisée en termes de préconditions qui devront être
 * vraies pour qu'elle puisse être éxécutée, et enterme d'effets qui 
 * s'ensuivent quand elle est éxécutée.
 * 
 */
public class BasicAction implements Action {

    private final Map<Variable, Object> preconditions;
    private final Map<Variable, Object> effets;
    private final int cout;


    /**
     * 
     * @param preconditions -> précondition respectée = on va effectuer l'action
     * @param effets -> effet donné sur l'état
     * @param cout -> coût de l'action
     */
    public BasicAction(Map<Variable, Object> preconditions, Map<Variable, Object> effets, int cout) {
        this.preconditions = preconditions;
        this.effets = effets;
        this.cout = cout;
    }

    @Override
    //Retourne vrai si les préconditions sont contenues dans l'état
    public boolean isApplicable(Map<Variable, Object> etat) {
        return etat.entrySet().containsAll(this.preconditions.entrySet());
    }

    @Override
    public Map<Variable, Object> successor(Map<Variable, Object> etat) {
        if(isApplicable(etat)){
            Map<Variable, Object> map = new HashMap<Variable, Object>(etat);
            map.putAll(this.effets);
            return map;
        }else{
            throw new IllegalArgumentException();
        }

    }

    @Override
    public String toString(){
        String str = "";
        str += "* Preconditions : " + this.preconditions + "\n";
        str += "* Effets : " + this.effets + "\n";
        str += "* Cout : " + this.cout + "\n";
        return str;

    }

    @Override
    public int getCost() {
        return this.cout;
    }
}
