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
		//On vérifie si v1 et v2 sont présents, puis on compare la valeur de v1 dans la map à l1 et on récupère le boolean, de même pour v2 et l2 et enfin, on fait la condition l1 -> l2.
		boolean test1 = tab.containsKey(v1);
		boolean test2 = tab.containsKey(v2);
		if(test1 & test2){
			return true;
		}else{
			throw new IllegalArgumentException("L'objet ne contient pas les variables");
		}
	}
	
}
