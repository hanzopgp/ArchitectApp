package datamining;
import representation.BooleanVariable;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe qui définit le comportement de base d'un mineur de règles
 */
public abstract class AbstractAssociationRuleMiner implements AssociationRuleMiner {
    protected BooleanDatabase database;

    /**
     * Constructeur
     * @param database - Base de données
     */
    public AbstractAssociationRuleMiner(BooleanDatabase database) {
        this.database = database;
    }

    /**
     * Retourne la fréquence d'apparition d'un ensemble de variable booléennes parmis
     * un ensemble d'Itemset
     * @param variables - Ensemble de variables booléennes
     * @param items - Ensemble d'Itemset
     * @return Fréquence d'apparition
     */
    public static float frequency(Set<BooleanVariable> variables, Set<Itemset> items){
        float frequency = 0;
        if(items == null){
            throw new IllegalArgumentException();
        }else{
            for(Itemset list : items) {
                if (list.getItems().equals(variables)) {
                    frequency = list.getFrequency();
                }
            }
            return frequency;
        }
    }

    /**
     * Retourne la confiance de la règle à partir de la prémisse et de la conclusion
     * donnée en paramètre
     * @param premise - Prémisse de la règle
     * @param conclusion - Conclusion de la règle
     * @param frequent - Ensemble d'Itemset
     * @return Confiance de la règle
     */
    public static float confidence(Set<BooleanVariable> premise, Set<BooleanVariable> conclusion, Set<Itemset> frequent){
        float frequency = 0;
        Set<BooleanVariable> test = new HashSet<>();
        test.addAll(premise); test.addAll(conclusion);
        frequency = frequency(test, frequent);
        float premiseFrequency = frequency(premise, frequent);
        return frequency/premiseFrequency;
    }

    /**
     * Retourne l'ensemble un ensemble de règles (crées à partir de la Database) dont la confiance est supérieure ou
     * égale à la confiance minimale passée en paramètre
     * @param frequence - Fréquence minimale à dépasser
     * @param confiance - Confiance minimale à dépasser
     * @return Ensemble de règles
     */
    public Set<AssociationRule> extract(float frequence, float confiance){
        Set<AssociationRule> result = new HashSet<>();
        ItemsetMiner apriori = new Apriori(this.getDatabase());
        Set<Itemset> list = apriori.extract(frequence);
        for(Itemset item : list){
            float itemFrequency = item.getFrequency();
            if(itemFrequency >= frequence){
                Set<Set<BooleanVariable>> premises = BruteForceAssociationRuleMiner.allCandidatePremises(item.getItems());
                for(Set<BooleanVariable> premise : premises){
                    Set<BooleanVariable> conclusion = new HashSet<>(item.getItems());
                    conclusion.removeAll(premise);
                    float itemConfidence = confidence(premise, conclusion, list);
                    if(itemConfidence >= confiance){
                        AssociationRule rule = new AssociationRule(premise, conclusion, itemFrequency, itemConfidence);
                        result.add(rule);
                    }
                }
            }
        }
        return result;
    }

    /**
     * Getter de la Database
     * @return
     */
    public BooleanDatabase getDatabase() {
        return database;
    }
}
