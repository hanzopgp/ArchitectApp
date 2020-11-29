package solvers;

import java.util.*;

import examples.HouseDemo;
import representation.*;

/**
 * Cette classe correspond à un solveur dont le fonctionnement permet de
 * tester des variables vérifiés précédemment pour voir si elles fonctionnent
 * avec de nouvelles variables
 */
public class BacktrackSolver extends AbstractSolver{

	private List<Map<Variable, Object>> listForbiddenMap;
	private boolean simulation = false;

	/**
	 * Constructeur
	 * @param variables - Ensemble de variables du problème courant
	 * @param constraints - Ensemble de contraintes du problème courant
	 */
	public BacktrackSolver(Set<Variable> variables, Set<Constraint> constraints){
		super(variables, constraints);
	}

	@Override
	public Map<Variable, Object> solve(){
		Map<Variable, Object> instanceP = new HashMap<>();
		Queue<Variable> pile = new LinkedList<>(this.variables);
		return sra(instanceP, pile);
	}

	/**
	 * Fonction récursive permettant la résolution du problème
	 * @param map - Instanciation à vérifier
	 * @param pile - Ensemble de variables non instanciées
	 * @return
	 */
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
				if(this.simulation){
					if(newMap.keySet().containsAll(this.variables) && !HouseDemo.mapIsInListMap(map, this.listForbiddenMap)){
						return newMap;
					}
				}else{
					if(newMap.keySet().containsAll(this.variables)){
						return newMap;
					}
				}
				Queue<Variable> newPile = new LinkedList<>(pile);
				//newPile.remove(v);
				newMap = this.sra(newMap, newPile);
				if(newMap != null){
					return newMap;
				}
			}
		}
		return null;
	}

	public void addForbiddenItem(Map<Variable, Object> item){
		this.listForbiddenMap.add(item);
	}

	public void setSimulation(boolean simulation) {
		this.simulation = simulation;
	}

	public void initListForbiddenMap(){
		this.listForbiddenMap = new ArrayList<>();
	}

	public List<Map<Variable, Object>> getListForbiddenMap(){
		return this.listForbiddenMap;
	}


}
