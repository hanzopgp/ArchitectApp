package representation;

import java.util.Set;
import java.util.HashSet;

/**
 * Cette classe correspond à une variable booléenne,
 * avec un ensemble de domaines composé uniquement de
 * booléens
 */
public class BooleanVariable extends Variable{
	
	protected static Set<Object> domaine = new HashSet<>();
	static {domaine.add(true);domaine.add(false);}

	/**
	 * Constructeur
	 * @param name - nom de la variable
	 */
	public BooleanVariable(String name){
		super(name, domaine);
	}

	@Override
	public String toString(){
		return "--> " + this.name + ", Domaine : " + domaine;
	}

}
