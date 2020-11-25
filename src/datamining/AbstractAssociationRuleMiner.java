package datamining;

import representation.BooleanVariable;

import java.util.Set;

public abstract class AbstractAssociationRuleMiner implements AssociationRuleMiner {
    private BooleanDatabase database;

    public AbstractAssociationRuleMiner(BooleanDatabase database) {
        this.database = database;
    }

    public BooleanDatabase getDatabase() {
        return database;
    }

    public void setDatabase(BooleanDatabase database) {
        this.database = database;
    }

    public static float frequency(Set<BooleanVariable> variables, Set<Itemset> items){
        float frequency = 0;
        for(Itemset list : items) {
            if (list.getItems().equals(variables)) {
                frequency += list.getFrequency();
            }
        }
        return frequency;
    }

    public Set<AssociationRule> extract(float frequence, float confiance){



        return null;
    }
}
