package representation;

import java.util.*;

public class DifferenceConstraint implements Constraint{
	
	private final Variable v1;
	private final Variable v2;
	
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
		boolean test1 = tab.containsKey(v1);
		boolean test2 = tab.containsKey(v2);
		if(test1 & test2){
			return tab.get(v1) != tab.get(v2);
		}else{
			throw new IllegalArgumentException("L'objet ne contient pas les variables");
		}
	}
	
}
