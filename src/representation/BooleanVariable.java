package representation;

import java.util.Set;
import java.util.HashSet;

public class BooleanVariable extends Variable{
	
	protected static Set<Object> domaine = new HashSet<>();
	static {domaine.add(true);domaine.add(false);};
	
	public BooleanVariable(String name){
		super(name, domaine);
	}
	
	public Set<Object> getDomain(){
		return this.domaine;
	}
}
