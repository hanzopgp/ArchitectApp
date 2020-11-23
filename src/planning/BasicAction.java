package planning;

import representation.Variable;

import java.util.HashMap;
import java.util.Map;

public class BasicAction implements Action {

    private Map<Variable, Object> preconditions;
    private Map<Variable, Object> effets;
    private int cout;

    public BasicAction(Map<Variable, Object> preconditions, Map<Variable, Object> effets, int cout) {
        this.preconditions = preconditions;
        this.effets = effets;
        this.cout = cout;
    }

    @Override
    //Retourne si précondition est contenu dans Etat
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
    public int getCost() {
        return this.cout;
    }
}
