package datamining;

import java.util.*;

import representation.BooleanVariable;

/**
 * Classe qui definit le comportement de base d'un mineur
 */
public abstract class AbstractItemsetMiner implements ItemsetMiner{
	
	protected BooleanDatabase base;
	public static final Comparator<BooleanVariable> COMPARATOR = (var1, var2) -> var1.getName().compareTo(var2.getName());

	/**
	 * Constructeur
	 * @param base - Base de donnees
	 */
	public AbstractItemsetMiner(BooleanDatabase base){
		this.base = base;
	}
	
	@Override
	public BooleanDatabase getDatabase(){
		return this.base;
	}
	
	@Override
	public Set<Itemset> extract(float frequenceMin){
		Set<Itemset> source = new HashSet<Itemset>();
		Set<Itemset> result = new HashSet<Itemset>();
		for(Itemset item : source){
			if(item.getFrequency() >= frequenceMin){
				result.add(item);
			}
		}
		return result;
	}

	/**
	 * Retourne la frequence d'apparition d'un ensemble de variable booleens
	 * parmis les transaction de la base de donnees de l'instance actuelle.
	 * @param items - Ensemble de variables booleennes a verifier.
	 * @return Frequence d'apparition
	 */
	public float frequency(Set<BooleanVariable> items){
		float frequency = 0;
		for(int i = 0; i<this.base.getTransactions().size(); i++){
			if(this.base.getTransactions().get(i).containsAll(items)){
				frequency++;
			}			
		}
		frequency /= this.base.getTransactions().size();
		return frequency;
	}
}
