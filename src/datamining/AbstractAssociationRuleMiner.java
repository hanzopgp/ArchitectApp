package datamining;

import representation.BooleanVariable;

import java.util.Set;

abstract class AbstractAssociationRuleMiner implements AssociationRuleMiner {
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
        return Float.parseFloat(null);
    }

    public Set<AssociationRule> extract(float frequence, float confiance){



        return null;
    }
}
