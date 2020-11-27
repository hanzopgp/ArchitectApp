package representation;

import java.util.*;

public class BinaryExtensionConstraint implements Constraint{
	
	private final Variable v1;
	private final Variable v2;
	public List<Tuple> coupleAllowed;
	
	public BinaryExtensionConstraint(Variable v1, Variable v2){
		this.v1 = v1;
		this.v2 = v2;
		this.coupleAllowed = new ArrayList<>();
	}

	public void addTuple(Object var1, Object var2){
		this.coupleAllowed.add(new Tuple(var1, var2));
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
		//On vérifie si v1 et v2 sont présents, puis on compare la valeur de v1
		// dans la map à l1 et on récupère le boolean, de même pour v2 et l2 et enfin,
		// on fait la condition l1 -> l2.
		boolean test1 = tab.containsKey(v1);
		boolean test2 = tab.containsKey(v2);
		if(!test1 || !test2){
			throw new IllegalArgumentException("L'objet ne contient pas les variables");
		}
		for(Tuple t : this.coupleAllowed){
			Object mapItemOne = tab.get(this.getV1());
			Object mapItemTwo = tab.get(this.getV2());
			if(mapItemOne.equals(t.getX()) && mapItemTwo.equals(t.getY())){
				return true;
			}
		}
		return false;
	}

	public Variable getV1() {
		return v1;
	}

	public Variable getV2() {
		return v2;
	}

	public List<Tuple> getCoupleAllowed() {
		return coupleAllowed;
	}

	public void setCoupleAllowed(List<Tuple> coupleAllowed) {
		this.coupleAllowed = coupleAllowed;
	}
}
