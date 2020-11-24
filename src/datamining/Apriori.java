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

	@Override
	public Set<Itemset> extract(float frequenceMin){
		Set<Itemset> result = new HashSet<>();
		Set<Itemset> source = new HashSet<>(this.frequentSingletons(frequenceMin));
		result.addAll(source);
		List<SortedSet<BooleanVariable>> items = new ArrayList<>();
		for(Itemset x : source){
			SortedSet<BooleanVariable> liste = new TreeSet<>(AbstractItemsetMiner.COMPARATOR);
			liste.addAll(x.getItems());
			items.add(liste);
			result.add(x);
		}
		for(int i=2;i<=super.base.getItems().size();i++){
			List<SortedSet<BooleanVariable>> item2 = new ArrayList<>();
			for(int j=0;j<items.size();j++){
				for(int k=j+1;k<items.size();k++){
					SortedSet<BooleanVariable> tmp2 = this.combine(items.get(j), items.get(k));
					//print test allsubsetfrequent
					if(tmp2 != null && this.allSubsetsFrequent(tmp2, items)){
						//print test
						float frequence = frequency(tmp2);
						if(frequence >= frequenceMin){
							result.add(new Itemset(tmp2, frequence));
							item2.add(tmp2);
						}
					}
				}
			}
			items = item2;
		}
		System.out.println(result);
		return result;
	}

	public static SortedSet<BooleanVariable> combine(SortedSet<BooleanVariable>l1, SortedSet<BooleanVariable>l2){
		SortedSet<BooleanVariable> result = new TreeSet<>(COMPARATOR);
		if(l1.size() > 0 && l1.size() == l2.size()){
			if(!(l1.last().equals(l2.last()))){
				if(l1.subSet(l1.iterator().next(), l1.last()).equals(l2.subSet(l2.iterator().next(), l2.last()))){
					result.addAll(l1);
					result.addAll(l2);
					return result;
				}else{
					return null;
				}
			}else{
				return null;
			}
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