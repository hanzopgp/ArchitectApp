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
		Queue<Variable> pile = new PriorityQueue<Variable>();
		for(Variable v : variables){
			pile.add(v);
		}
		return sra(instanceP, pile);
	}
		
	public Map<Variable, Object> sra(Map<Variable, Object> map, Queue<Variable> pile){
		if(pile.isEmpty()){
			return map;
		}else{
			Variable v = pile.poll();
			for(Object o : v.getDomain()){
				map.put(v,o);
				if(isConsistent(map)){
					Map<Variable, Object> m = this.sra(map,pile);
					if(m != null){
						return m;
					}
				}
			}
		}
		return map;
	}

}
