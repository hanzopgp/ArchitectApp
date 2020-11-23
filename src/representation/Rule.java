package representation;

import java.util.*;

public class Rule implements Constraint{
	
	private boolean l1;
	private boolean l2;
	private BooleanVariable v1;
	private BooleanVariable v2;
	
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
			boolean un = (l1 == test1 ? true : false);
			boolean deux = (l2 == test2 ? true : false);
			if(!un || deux){
				return true;
			}else{
				return false;
			}
		}else{
			throw new IllegalArgumentException("L'objet ne contient pas les variables");
		}
	}
	
}
