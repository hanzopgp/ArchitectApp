package planning;

import representation.Variable;

import java.util.HashMap;
import java.util.Map;


/**
 * Cette classe represente des actions qui vont être testees.
 * Une action sera caracterisee en termes de preconditions qui devront être
 * vraies pour qu'elle puisse être executee, et enterme d'effets qui 
 * s'ensuivent quand elle est executee.
 * 
 */
public class BasicAction implements Action {

    private final Map<Variable, Object> preconditions;
    private final Map<Variable, Object> effets;
    private final int cout;


    /**
     * 
     * @param preconditions -> precondition respectee = on va effectuer l'action
     * @param effets -> effet donne sur l'etat
     * @param cout -> coût de l'action
     */
    public BasicAction(Map<Variable, Object> preconditions, Map<Variable, Object> effets, int cout) {
        this.preconditions = preconditions;
        this.effets = effets;
        this.cout = cout;
    }

    @Override
    //Retourne vrai si les preconditions sont contenues dans l'etat
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
