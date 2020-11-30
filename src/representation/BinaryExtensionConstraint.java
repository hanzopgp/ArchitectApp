package representation;

import java.util.*;

/**
 * Cette classe correspond a une contrainte en extension
 */
public class BinaryExtensionConstraint implements Constraint{
	
	private final Variable v1;
	private final Variable v2;
	public List<Tuple> coupleAllowed;

	/**
	 * Constructeur
	 * @param v1 - premiere variable
	 * @param v2 - deuxieme variable
	 */
	public BinaryExtensionConstraint(Variable v1, Variable v2){
		this.v1 = v1;
		this.v2 = v2;
		this.coupleAllowed = new ArrayList<>();
	}

	/**
	 * Ajoute un couple de variable autorisees
	 * @param var1
	 * @param var2
	 */
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
		//On verifie si v1 et v2 sont presents, puis on compare la valeur de v1
		// dans la map a l1 et on recupere le boolean, de même pour v2 et l2 et enfin,
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

	@Override
	public String toString(){
		return "* Contrainte en extension binaire";
	}

	/**
	 * Retourne la premiere variable
	 * @return
	 */
	public Variable getV1() {
		return v1;
	}

	/**
	 * Retourne la deuxieme variable
	 * @return
	 */
	public Variable getV2() {
		return v2;
	}

	/**
	 * Retourne l'ensemble de couples autorises par la contrainte
	 * @return
	 */
	public List<Tuple> getCoupleAllowed() {
		return coupleAllowed;
	}

	/**
	 * Definis l'ensemble de couples autorises par la contrainte
	 * @param coupleAllowed
	 */
	public void setCoupleAllowed(List<Tuple> coupleAllowed) {
		this.coupleAllowed = coupleAllowed;
	}
}
