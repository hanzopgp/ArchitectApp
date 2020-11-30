package datamining;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import representation.BooleanVariable;

/**
 * Classe qui correspond a un ensemble de variables booleennes
 */
public class BooleanDatabase{
	
	private final Set<BooleanVariable> items;
	private final List<Set<BooleanVariable>> transactions;

	/**
	 * Constructeur
	 * @param items - Ensemble de variable booleennes
	 */
	public BooleanDatabase(Set<BooleanVariable> items){
		this.items = items;
		this.transactions = new ArrayList<Set<BooleanVariable>>();
	}

	/**
	 * Ajoute un ensemble de variable a l'ensemble deja existant
	 * @param transactions - Ensemble de variables
	 */
	public void add(Set<BooleanVariable> transactions){
		this.transactions.add(transactions);
	}

	/**
	 * Getter de items
	 * @return Retourne l'ensemble de variables booleennes
	 */
	public Set<BooleanVariable> getItems(){
		return this.items;
	}

	/**
	 * Getter des transactions
	 * @return Retourne l'ensemble des transactions
	 */
	public List<Set<BooleanVariable>> getTransactions(){
		return this.transactions;
	}
}
