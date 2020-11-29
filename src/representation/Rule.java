package representation;

import java.util.*;

/**
 * Cette classe correspond à une règle impliquant v1 avec v2
 */
public class Rule implements Constraint{
	
	private final boolean l1;
	private final boolean l2;
	private final BooleanVariable v1;
	private final BooleanVariable v2;

	/**
	 * Constructeur
	 * @param v1 - première variable
	 * @param l1 - valeur de la première variable
	 * @param v2 - deuxième variable
	 * @param l2 - valeur de la deuxième variable
	 */
	public Rule(BooleanVariable v1, boolean l1, BooleanVariable v2, boolean l2){
		this.l1 = l1;
		this.l2 = l2;
		this.v1 = v1;
		this.v2 = v2;		
	}

	@Override
	public Set<Variable> getScope(){
		HashSet<Variable> scope = new HashSet<>();
		scope.add(this.v1);
		scope.add(this.v2);
		return scope;
	}

	@Override
	public boolean isSatisfiedBy(Map<Variable, Object> tab){
		//On vérifie si v1 et v2 sont présents, puis on compare la valeur de v1 dans la map à l1 et on récupère le boolean, de même pour v2 et l2 et enfin, on fait la condition l1 -> l2.
		boolean test1 = tab.containsKey(v1);
		boolean test2 = tab.containsKey(v2);
		if(test1 & test2){
			boolean bool1 = tab.get(v1).equals(l1);
			boolean bool2 = tab.get(v2).equals(l2);
			return !bool1 || bool2;
		}else{
			throw new IllegalArgumentException("L'objet ne contient pas les variables");
		}
	}
	
}
