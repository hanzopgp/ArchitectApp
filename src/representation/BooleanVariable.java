package representation;

import java.util.Set;
import java.util.HashSet;

/**
 * Cette classe correspond a une variable booleenne,
 * avec un ensemble de domaines compose uniquement de
 * booleens
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
		return "--> Nom : " + this.name + ", Domaine : " + domaine;
	}

}
