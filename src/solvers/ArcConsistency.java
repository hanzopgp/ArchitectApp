package solvers;

import java.util.*;
import representation.*;

public class ArcConsistency{
	
	private ArrayList<Constraint> constraints;
	
	public ArcConsistency(ArrayList<Constraint> constraints){
		this.constraints = constraints;
	}
	
	public static boolean filter(Variable var1, Set<Object> var1Domaine, Variable var2, Set<Object> var2Domaine, Constraint constraint){
		boolean del = false;
		for(Object var1dom : var1Domaine){
			Map<Variable, Object> map = new HashMap<Variable, Object>();
			map.put(var1, var1dom);
			for(Object var2dom : var2Domaine){
				map.put(var1, var2dom);
				if(!constraint.isSatisfiedBy(map)){
					var1Domaine.remove(var1dom);
					del = true;		
				}
			}
		}			
		return del;
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
