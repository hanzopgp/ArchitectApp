package solvers;

import java.util.*;
import representation.*;

public class BacktrackSolver extends AbstractSolver{

	public BacktrackSolver(Set<Variable> variables, Set<Constraint> constraints){
		super(variables, constraints);
	}

	@Override
	public Map<Variable, Object> solve(){
		Map<Variable, Object> instanceP = new HashMap<Variable, Object>();
		Queue<Variable> pile = new LinkedList<>();
		for(Variable v : this.variables){
			pile.add(v);
		}
		return sra(instanceP, pile);
	}

	public Map<Variable, Object> sra(Map<Variable, Object> map, Queue<Variable> pile){
		//check si il y a encore des variables
		if(pile.isEmpty()){
			return map;
		}
		//On recupère la première variable de la liste
		Variable v = pile.poll();
		for(Object o : v.getDomain()){
			Map<Variable, Object> newMap = new HashMap<>(map);
			newMap.put(v, o);
			if(isConsistent(newMap)){
				if(newMap.keySet().containsAll(this.variables)){
					return newMap;
				}
				Queue<Variable> newPile = new LinkedList<>(pile);
				newMap = this.sra(newMap, newPile);
				if(newMap != null){
					return newMap;
				}
			}

		}
		return null;
	}

}
