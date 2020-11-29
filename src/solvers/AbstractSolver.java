package solvers;

import java.util.*;
import representation.*;

/**
 * Classe qui définit le comportement de base d'un solveur
 */
public abstract class AbstractSolver implements Solver{
	
	protected Set<Variable> variables;
	protected Set<Constraint> constraints;

	/**
	 * Constructeur
	 * @param variables - Ensemble de variables du problème courant
	 * @param constraints - Ensemble de contraintes du problème courant
	 */
	public AbstractSolver(Set<Variable> variables, Set<Constraint> constraints){
		for(Constraint c : constraints){
			if(!variables.containsAll(c.getScope())){
				throw new IllegalArgumentException("Fail");
			}
		}
		this.variables = variables;
		this.constraints = constraints;
	}

	/**
	 * Retourne un booléan si l'instanciation en paramètre satisfait les
	 * contraintes du problème courant
	 * @param tab - Instanciation à vérifier
	 * @return
	 */
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
