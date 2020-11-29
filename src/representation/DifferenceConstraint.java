package representation;

import java.util.*;

/**
 * Cette classe correspond à une contrainte dite "différente"
 */
public class DifferenceConstraint implements Constraint{
	
	private final Variable v1;
	private final Variable v2;

	/**
	 * Constructeur
	 * @param v1 première variable
	 * @param v2 deuxième variable
	 */
	public DifferenceConstraint(Variable v1, Variable v2){
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
		boolean test1 = tab.containsKey(this.v1);
		boolean test2 = tab.containsKey(this.v2);
		if(test1 & test2){
			return tab.get(this.v1) != tab.get(this.v2);
		}else{
			throw new IllegalArgumentException("L'objet ne contient pas les variables");
		}
	}
	
}
