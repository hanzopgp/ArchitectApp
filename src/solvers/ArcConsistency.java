package solvers;

import java.util.*;
import representation.*;

public class ArcConsistency{
	
	private final ArrayList<Constraint> constraints;
	
	public ArcConsistency(ArrayList<Constraint> constraints){
		this.constraints = constraints;
	}
	
	public static boolean filter(Variable var1, Set<Object> var1Domaine, Variable var2, Set<Object> var2Domaine, Constraint constraint){
		Set<Object> tmp = new HashSet<>();
		for(Object var1dom : var1Domaine){
			boolean del = true;
			Map<Variable, Object> map = new HashMap<Variable, Object>();
			map.put(var1, var1dom);
			for(Object var2dom : var2Domaine) {
				map.put(var2, var2dom);
				del = del && !constraint.isSatisfiedBy(map);
			}
			if(del){
				tmp.add(var1dom);
				//ne pas remove ici sinon CurrentModificationException
				// -> voir sur le sujet ecampus !
			}
		}
		var1Domaine.removeAll(tmp);
		return tmp.size() != 0;
	}
	
	public static boolean enforce(Constraint c, Map<Variable, Set<Object>> mapDom){
		List<Variable> variables = new ArrayList<Variable>(mapDom.keySet());
		List<Set<Object>> domaines = new ArrayList<Set<Object>>(mapDom.values());
		boolean res = false;
		for(int i =0; i < variables.size()-1; i++){
			for(int j =0; j < domaines.size()-1; j++){
				res = filter(variables.get(i),domaines.get(j), variables.get(i+1), domaines.get(j+1),c);
			}
		}
		return res;
	}
	
	
	/*public static void enforceArcConsistency(Map<Variable, Set<Object>>){

	}*/
	
	
}
