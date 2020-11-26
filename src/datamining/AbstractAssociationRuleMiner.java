//package datamining;
//
//import dataminingtests.Item;
//import representation.BooleanVariable;
//
//import java.util.HashSet;
//import java.util.Set;
//
//public abstract class AbstractAssociationRuleMiner implements AssociationRuleMiner {
//    protected BooleanDatabase database;
//
//    public AbstractAssociationRuleMiner(BooleanDatabase database) {
//        this.database = database;
//    }
//
//    public BooleanDatabase getDatabase() {
//        return database;
//    }
//
//    public void setDatabase(BooleanDatabase database) {
//        this.database = database;
//    }
//
//    public static float frequency(Set<BooleanVariable> variables, Set<Itemset> items){
//        float frequency = 0;
//        for(Itemset list : items) {
//            if (list.getItems().equals(variables)) {
//                frequency += list.getFrequency();
//            }
//        }
//        return frequency;
//    }
//
//    public static float confidence(Set<BooleanVariable> premise, Set<BooleanVariable> conclusion, Set<Itemset> frequent){
//        float frequency = 0;
//        Set<BooleanVariable> test = new HashSet<>();
//        test.addAll(premise); test.addAll(conclusion);
//        frequency = frequency(test, frequent);
//        float premiseFrequency = frequency(premise, frequent);
//        return frequency/premiseFrequency;
//    }
//
//    public Set<AssociationRule> extract(float frequence, float confiance){
//        Set<AssociationRule> result = new HashSet<>();
//        Apriori apriori = new Apriori(this.getDatabase());
//        Set<Itemset> list = apriori.frequentSingletons(frequence);
//        for(Itemset item : list){
//            Set<Set<BooleanVariable>> premises = BruteForceAssociationRuleMiner.allCandidatePremises(item.getItems());
//            for(Set<BooleanVariable> premise : premises){
//                Set<BooleanVariable> conclusion = new HashSet<>();
//                //Set<BooleanVariable> conclusion = item.getItems().removeAll(premise);
//                if(confidence(premise, conclusion, list) > confiance){
//                    AssociationRule rule = new AssociationRule(premise, conclusion, frequence, confiance);
//                    result.add(rule);
//                }
//            }
//        }
//
//        return result;
//    }
//}
