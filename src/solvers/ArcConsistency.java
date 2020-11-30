package solvers;

import java.util.*;
import representation.*;

/**
 * Cette classe correspond a un ensemble dit "arc-coherent" sur plusieurs
 * contraintes.
 */
public class ArcConsistency{
	
	private final Set<Constraint> constraints;

	/**
	 * Constructeur
	 * @param constraints - Ensemble de contraintes
	 */
	public ArcConsistency(Set<Constraint> constraints){

		this.constraints = constraints;
	}

	/**
	 * Fonction qui permet de supprimer les variables qui ne satisfassent pas
	 * la contrainte actuelle
	 * @param var1 - Premiere variable
	 * @param var1Domaine - Domaine de la premiere variable
	 * @param var2 - Deuxieme variable
	 * @param var2Domaine - Domaine de la deuxieme variable
	 * @param constraint - Contrainte que var1 et var2 doivent satisfaire
	 * @return
	 */
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

	/**
	 * Cette methode permet de rendre les domaines passes en parametres "arc-coherents"
	 * avec la contrainte en parametre
	 * @param c - Contrainte
	 * @param mapDom - Domaines
	 * @return
	 */
	public static boolean enforce(Constraint c, Map<Variable, Set<Object>> mapDom){

		Iterator<Variable> constraintIterator = c.getScope().iterator();
		//inutile de continuer si l'iterator est vide
		if(!constraintIterator.hasNext()){
			return false;
		}else{
			//on recupere la premiere variable de la contrainte
			Variable v1 = constraintIterator.next();
			if(!mapDom.containsKey(v1)){
				return false;
			}else{
				//on recupere la deuxieme variable de la contrainte
				Variable v2 = constraintIterator.next();
				if(!mapDom.containsKey(v2)){
					return false;
				}else{
					boolean res = filter(v1, mapDom.get(v1), v2, mapDom.get(v2), c);
					boolean res2 = filter(v2, mapDom.get(v2), v1, mapDom.get(v1), c);
					return res || res2;
				}
			}
		}
	}

	/**
	 * Cette methode agit de la même sorte que enforce(), mais elle permet de rendre
	 * les domaines passes en parametres "arc-coherents" avec les contraintes contenues
	 * dans cette classe.
	 * @param mapDom - Domaines
	 * @return
	 */
	public boolean enforceArcConsistency(Map<Variable, Set<Object>> mapDom){
		for(Constraint c : this.getConstraints()){
			enforce(c, mapDom);
		}

		//retourne true si et seulement si tous les domaines sont non vides a la fin du traitement
		for(Set<Object> varDomaine : mapDom.values()){
			if(varDomaine.size() == 0){
				return false;
			}
		}
		return true;
	}

	/**
	 * Getter des contraintes
	 * @return
	 */
	public Set<Constraint> getConstraints() {
		return constraints;
	}
}
