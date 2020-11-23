package representation;

import java.util.*;

public class Rule implements Constraint{
	
	private final boolean l1;
	private final boolean l2;
	private final BooleanVariable v1;
	private final BooleanVariable v2;
	
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
		boolean test1 = tab.containsKey(v1);
		boolean test2 = tab.containsKey(v2);
		if(test1 & test2){
			return !l1 || l2;
		}else{
			throw new IllegalArgumentException("L'objet ne contient pas les variables");
		}
	}
	
}
