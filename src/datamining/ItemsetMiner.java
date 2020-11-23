package datamining;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import representation.BooleanVariable;

public interface ItemsetMiner{
	
	public BooleanDatabase getDatabase();
	
	public Set<Itemset> extract(float frequency);
}
