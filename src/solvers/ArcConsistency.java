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

		Iterator<Variable> constraintIterator = c.getScope().iterator();
		//inutile de continuer si l'iterator est vide
		if(!constraintIterator.hasNext()){
			return false;
		}else{
			//on récupère la première variable de la contrainte
			Variable v1 = constraintIterator.next();
			if(!mapDom.keySet().contains(v1)){
				return false;
			}else{
				//on récupère la deuxième variable de la contrainte
				Variable v2 = constraintIterator.next();
				if(!mapDom.keySet().contains(v2)){
					return false;
				}else{
					boolean res = filter(v1, mapDom.get(v1), v2, mapDom.get(v2), c);
					boolean res2 = filter(v2, mapDom.get(v2), v1, mapDom.get(v1), c);
					return res || res2;
				}
			}
		}
	}
	
	
	/*public static void enforceArcConsistency(Map<Variable, Set<Object>>){

	}*/
	
	
}
