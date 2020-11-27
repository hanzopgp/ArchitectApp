package solvers;

import java.util.*;
import representation.*;

public abstract class AbstractSolver implements Solver{
	
	protected Set<Variable> variables;
	protected Set<Constraint> constraints;
	
	public AbstractSolver(Set<Variable> variables, Set<Constraint> constraints){
		for(Constraint c : constraints){
//			System.out.println("v : " + variables);
//			System.out.println("scope : " + c.getScope());
			if(!variables.containsAll(c.getScope())){
				throw new IllegalArgumentException("Fail");
			}
		}
		this.variables = variables;
		this.constraints = constraints;
	}

	public boolean isConsistent(Map<Variable, Object> tab){
		boolean b = true;
		for(Constraint c : constraints){
			if(tab.keySet().containsAll(c.getScope())){
				if(!c.isSatisfiedBy(tab)){
					b = false;
				}
			}
		}
		return b;
	}
}
