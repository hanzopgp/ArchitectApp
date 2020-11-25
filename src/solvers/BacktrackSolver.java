package solvers;

import java.util.*;
import representation.*;

public class BacktrackSolver extends AbstractSolver{
	
	protected Set<Variable> variables = new HashSet<>();
	protected Set<Constraint> constraints = new HashSet<>();
		
	public BacktrackSolver(Set<Variable> variables, Set<Constraint> constraints){
		super(variables, constraints);
	}
	
	@Override
	public Map<Variable, Object> solve(){
		Map<Variable, Object> instanceP = new HashMap<Variable, Object>();
		Deque<Variable> pile = new LinkedList<>();
		for(Variable v : variables){
			pile.add(v);
		}
		return sra(instanceP, pile);
	}
		
	public Map<Variable, Object> sra(Map<Variable, Object> map, Queue<Variable> pile){
		Variable v = null;
		if(pile.isEmpty()){
			return map;
		}else{
			v = pile.poll();
			for(Object o : v.getDomain()){
				map.put(v,o);
				if(isConsistent(map)){
					if(this.sra(map,pile) != null){

						return this.sra(map,pile);
					}
				}
			}
		}
		//  De même, pour l'appel récursif, il est quand même préférable de remettre
		//  la variable dans la liste juste avant le return null ... ?????
		pile.add(v); 
		return null;
	}

}
