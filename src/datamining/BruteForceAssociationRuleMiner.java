
package datamining;
import representation.BooleanVariable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Mineur de règle basé sur l'algorithme "brute-force"
 * @param <E>
 */
public class BruteForceAssociationRuleMiner<E> extends AbstractAssociationRuleMiner{

    /**
     * Constructeur
     * @param database - Base de données
     */
    public BruteForceAssociationRuleMiner(BooleanDatabase database) {
        super(database);
    }

    /**
     * Retourne tout les sous-ensembles possibles d'un ensemble de variable passee en parametre.
     * Code trouve sur cette page : https://www.geeksforgeeks.org/finding-all-subsets-of-a-given-set-in-java/
     *
     * @param items - Ensemble de variables booleennes
     * @return
     */
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
