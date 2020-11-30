package datamining;
import representation.BooleanVariable;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe qui definit le comportement de base d'un mineur de regles
 */
public abstract class AbstractAssociationRuleMiner implements AssociationRuleMiner {
    protected BooleanDatabase database;

    /**
     * Constructeur
     * @param database - Base de donnees
     */
    public AbstractAssociationRuleMiner(BooleanDatabase database) {
        this.database = database;
    }

    /**
     * Retourne la frequence d'apparition d'un ensemble de variable booleennes parmis
     * un ensemble d'Itemset
     * @param variables - Ensemble de variables booleennes
     * @param items - Ensemble d'Itemset
     * @return Frequence d'apparition
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
     * Retourne la confiance de la regle a partir de la premisse et de la conclusion
     * donnee en parametre
     * @param premise - Premisse de la regle
     * @param conclusion - Conclusion de la regle
     * @param frequent - Ensemble d'Itemset
     * @return Confiance de la regle
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
     * Retourne l'ensemble un ensemble de regles (crees a partir de la Database) dont la confiance est superieure ou
     * egale a la confiance minimale passee en parametre
     * @param frequence - Frequence minimale a depasser
     * @param confiance - Confiance minimale a depasser
     * @return Ensemble de regles
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
