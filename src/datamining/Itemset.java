package datamining;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import representation.BooleanVariable;

/**
 * Classe qui correspond a un motif
 */
public class Itemset{
	
	private final Set<BooleanVariable> items;
	private final float frequence;

	/**
	 * Constructeur
	 * @param items - Ensemble de variable booleennes
	 * @param frequence - Frequence du motif
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
	 * Getter de la frequence
	 * @return Retourne la frequence de l'instance
	 */
	public float getFrequency(){
		return this.frequence;
	}
}
