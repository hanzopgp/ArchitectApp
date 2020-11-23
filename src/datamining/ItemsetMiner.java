package datamining;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import representation.BooleanVariable;

public interface ItemsetMiner{
	
	BooleanDatabase getDatabase();
	
	Set<Itemset> extract(float frequency);
}
