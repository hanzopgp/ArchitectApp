package datamining;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import representation.BooleanVariable;

public class Itemset{
	
	private final Set<BooleanVariable> items;
	private final float frequence;
	
	public Itemset(Set<BooleanVariable> items, float frequence){
		this.items = items;
		this.frequence = frequence;
	}
	
	public Set<BooleanVariable> getItems(){
		return this.items;
	}
	
	public float getFrequency(){
		return this.frequence;
	}
}
