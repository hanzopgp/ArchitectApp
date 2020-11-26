package datamining;

import dataminingtests.Item;
import representation.BooleanVariable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BruteForceAssociationRuleMiner<E> extends AbstractAssociationRuleMiner{

    public BruteForceAssociationRuleMiner(BooleanDatabase database) {
        super(database);
    }

    public static Set<Set<BooleanVariable>> allCandidatePremises(Set<BooleanVariable> items){
        Set<Set<BooleanVariable>> result = new HashSet<>();

        //ON A [(A),(B),(C),(D)]
        for(BooleanVariable v : items){
            Set<BooleanVariable> vTemp = new HashSet<>();
            vTemp.add(v);
            result.add(vTemp);
        }

        ArrayList<BooleanVariable> liste = new ArrayList<>();
        liste.addAll(items);

        for(int i=0; i<liste.size();i++){
            result = extractEnsembles(result, liste, this.getDatabase())
        }

        for(Set<BooleanVariable> list : super.getDatabase().getTransactions()){
            if(list != null && list.size() > 1){
                result.add(list);
            }
        }
        if(result != null){
            return result;
        }else{
            return null;
        }

    }

    public static Set<Set<BooleanVariable>> extractEnsembles(Set<Set<BooleanVariable>> liste, ArrayList<BooleanVariable> variables, BooleanVariable variable){
        // liste = [A,B,C,D]
        // result = [A], [B], [C], [D], [A,B], etc ...
        Set<Set<BooleanVariable>> result = new HashSet<>();
        Set<Set<BooleanVariable>> tmp = new HashSet<>();
        for(int i=variables.indexOf(v))
        return null;
    }
}
