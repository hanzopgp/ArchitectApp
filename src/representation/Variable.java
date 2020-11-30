package representation;

import java.util.Set;

/**
 * Cette classe correspond à une variable normale,
 * qui possède un ensemble de domaines
 */
public class Variable{

	protected String name;
	protected Set<Object> domaine;

	/**
	 * Constructeur
	 * @param name - nom de la variable
	 * @param domaine - domaine de la variable
	 */
	public Variable (String name, Set<Object> domaine){
		this.name = name;
		this.domaine = domaine;
	}
	
	@Override
	public boolean equals(Object o){
		if(o == this){
			return true;
		}else return ((Variable) o).getName().equals(this.getName()) && ((Variable) o).getDomain() == this.getDomain();
	}

	@Override
	public int hashCode(){
		return this.name.hashCode();
	}

	/**
	 * Getter du nom de la variable
	 * @return
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * Getter de l'ensemble des domaines de la variable
	 * @return
	 */
	public Set<Object> getDomain(){
		return this.domaine;
	}

	@Override
	public String toString(){
		return "--> Nom : " + this.name + ", Domaine : " + this.domaine;
	}
	
	
}
