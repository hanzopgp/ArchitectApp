package datamining;
import java.util.Set;

public interface AssociationRuleMiner {
    BooleanDatabase getDatabase();
    Set<AssociationRule> extract(float frequence, float confiance);
}
