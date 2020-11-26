
package datamining;
import representation.BooleanVariable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BruteForceAssociationRuleMiner<E> extends AbstractAssociationRuleMiner{

    public BruteForceAssociationRuleMiner(BooleanDatabase database) {
        super(database);
    }

    //vu sur https://www.geeksforgeeks.org/finding-all-subsets-of-a-given-set-in-java/
    public static Set<Set<BooleanVariable>> allCandidatePremises(Set<BooleanVariable> items){
        int n = items.size();
        Set<Set<BooleanVariable>> result = new HashSet<>();
        if(items.size()<=1){
            return result;
        }
        ArrayList<BooleanVariable> liste = new ArrayList<>();
        liste.addAll(items);
        for (int i = 0; i < (1<<n); i++) {
            Set<BooleanVariable> tmp = new HashSet<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0 && tmp.size()+1 <items.size()) {
                    tmp.add(liste.get(j));
                    result.add(tmp);
                }
            }
        }
        return result;
    }
}
