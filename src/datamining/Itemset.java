package datamining;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import representation.BooleanVariable;

/**
 * Classe qui correspond à un motif
 */
public class Itemset{
	
	private final Set<BooleanVariable> items;
	private final float frequence;

	/**
	 * Constructeur
	 * @param items - Ensemble de variable booléennes
	 * @param frequence - Fréquence du motif
	 */
	public Itemset(Set<BooleanVariable> items, float frequence){
		this.items = items;
		this.frequence = frequence;
	}

	/**
	 * Getter des items
	 * @return Retourne les items de l'instance
	 */
	public Set<BooleanVariable> getItems(){
		return this.items;
	}

	/**
	 * Getter de la fréquence
	 * @return Retourne la fréquence de l'instance
	 */
	public float getFrequency(){
		return this.frequence;
	}
}
