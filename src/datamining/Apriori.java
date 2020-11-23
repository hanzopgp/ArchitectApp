package datamining;

import java.util.*;
import representation.BooleanVariable;


public class Apriori extends AbstractItemsetMiner{
	
	public Apriori(BooleanDatabase base){
		super(base);		
	}
	
	public Set<Itemset> frequentSingletons(float frequency){
		Set<Itemset> liste = new HashSet<Itemset>();
		for(BooleanVariable base : this.base.getItems()){
			SortedSet<BooleanVariable> sorted = new TreeSet<>(AbstractItemsetMiner.COMPARATOR);
			sorted.add(base);
			float frequence = frequency(sorted);
			if(frequency <= frequence){
				liste.add(new Itemset(sorted ,frequence));	
			}
		}
		return liste;
	}
	
	public static SortedSet<BooleanVariable> combine(SortedSet<BooleanVariable>l1, SortedSet<BooleanVariable>l2){
		if(l1.size() > 0 && l1.size() == l2.size() && !(l1.last().equals(l2.last())) && l1.size() != 0){
			Iterator<BooleanVariable> it1 = l1.iterator();
			Iterator<BooleanVariable> it2 = l2.iterator();
			while(it1.hasNext()){
				BooleanVariable it1next = it1.next();
				BooleanVariable it2next = it2.next();
				if(!(it1next.equals(l1.last())) && !(it2next.equals(l2.last()))){
					if(!(it1next.equals(it2next))){
							return null;
					}
				}	
			}
			BooleanVariable l2last = l2.last();
			l1.add(l2last);
			return l1;
		}else{
			return null;
		}
	}	

	public static boolean allSubsetsFrequent(Set<BooleanVariable>list, Collection<SortedSet<BooleanVariable>>collectionList){
		Set<BooleanVariable> listDel = new HashSet<>(list);
		for(BooleanVariable variable : listDel){
			list.remove(variable);
			if(!(collectionList.contains(list))){
				return false;
			}
			list.add(variable);
		}
		return true;
	}	


}


//pas d'iterator, crée une copie de list (avant la boucle) faire un for sur la copie, dans la boucle, on remove l'item courant, on test si collectionList ne contient pas le set, on return false. Après le if, on remet l'item courant.