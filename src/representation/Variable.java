package representation;

import java.util.Set;

public class Variable{

	protected String name;
	protected Set<Object> domaine;
	
	public Variable (String name, Set<Object> domaine){
		this.name = name;
		this.domaine = domaine;
	}
	
	public String getName(){
		return this.name;
	}
	
	public Set<Object> getDomain(){
		return this.domaine;
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

	@Override
	public String toString(){
		return "--> Piece : " + this.name + ", Domaine : " + this.domaine;
	}
	
	
}
