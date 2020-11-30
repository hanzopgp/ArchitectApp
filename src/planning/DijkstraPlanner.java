package planning;

import java.math.BigInteger;
import java.util.*;

import representation.*;

/**
 * Cette classe represente un planificateur utilisant l'algorithme Dijkstra.
 * Cette algorithme resoud le probleme du chemin le plus court. 
 * L'algorithme retourne le plan de coût minimal. 
 */
public class DijkstraPlanner implements Planner {

    private final Map<Variable, Object> initialState;
    private final Set<Action> actions;
    private final Goal goal;

    /**
     * @param initialState -> etat initial
     * @param actions -> actions qui pourront etre testee
     * @param goal -> but a obtenir
     */
    public DijkstraPlanner(Map<Variable, Object> initialState, Set<Action> actions, Goal goal) {
        this.initialState = initialState;
        this.actions = actions;
        this.goal = goal;
    }

    /**
     * Algorithme retournant le plan de coût minimal : Dijkstra
     * 
     * @return -> plan obtenu
     */
    @Override
    public List<Action> plan() {

        List<Map<Variable, Object>> goals = new LinkedList<>();
        Map<Map<Variable, Object>, Map<Variable, Object>> father = new HashMap<Map<Variable, Object>, Map<Variable, Object>>();
        Map<Map<Variable, Object>, Action> plan = new HashMap<Map<Variable, Object>, Action>();
        Map<Map<Variable, Object>, Integer> distance = new HashMap<Map<Variable, Object>, Integer>();
        List<Map<Variable, Object>> open = new LinkedList<Map<Variable, Object>>();
        Map<Variable, Object> instantiation = new HashMap<Variable, Object>();
        Comparator<Map<Variable, Object>> com = (state1, state2) -> distance.get(state1) - distance.get(state2);
        Map<Variable, Object> next = new HashMap<Variable, Object>();

        father.put(this.initialState, null);
        distance.put(initialState, 0);
        open.add(initialState);

        /** tant que tous les etats n'ont pas ete ouvert **/
        while (!open.isEmpty()) {
            /** donne a instanciation l'element le plus petit **/
            instantiation = Collections.min(open, com);
            open.remove(instantiation);
            if (this.getGoal().isSatisfiedBy(instantiation)) {
                goals.add(instantiation);
            }
            /** boucle qui itere les actions **/
            for (Action act : this.actions) {
                /** test l'action **/
                if (act.isApplicable(instantiation)) {
                    next = act.successor(instantiation);
                    /** test la liste des ouverts et des etats deja explore **/
                    if (!distance.containsKey(next)) {
                        distance.put(next, Integer.MAX_VALUE);
                    }
                    /** regarde si le coût calcule est inferieur a celui retenu precedemment **/
                    if (distance.get(next) > distance.get(instantiation) + act.getCost()) {
                        distance.put(next, distance.get(instantiation) + act.getCost());
                        father.put(next, instantiation);
                        plan.put(next, act);
                        open.add(next);
                    }
                }
            }

        }
        /** test si un but est trouve **/
        if (goals.isEmpty()) {
            return null;
        /** retourne le meilleur plan **/
        } else {
            return get_dijkstra_plan(father, plan, goals, distance);
        }
    }

    /**
     * Methode recursive appelee a la fin de la recherche de Dijkstra() pour 
     * retourner la liste des actions permettant de passer de l'etat initial 
     * a un sous-plan qui est le but.
     * 
     * @param father -> possede le father des etats visites
     * @param plan -> les etapes faites precedement = plan actuel
     * @param goels -> but suivant a valider
     * @param distance -> possede les trajets les plus courts pour aller aux noeuds deja visites
     * @return -> reconstruction du plan avec les plus petites distances
     */
    public List<Action> get_dijkstra_plan(
        Map<Map<Variable, Object>, Map<Variable, Object>> father,
        Map<Map<Variable, Object>, Action> plan, 
        List<Map<Variable, Object>> goels,
        Map<Map<Variable, Object>, Integer> distance){
        
        Map<Variable, Object> goal = new HashMap<Variable, Object>();
        List<Action> sup_plan = new LinkedList<Action>();
        Comparator<Map<Variable, Object>> com = (state1, state2) -> distance.get(state1) - distance.get(state2);
        goal = Collections.min(goels, com);
        while (goal != this.getInitialeState()) {
            sup_plan.add(plan.get(goal));
            goal = father.get(goal);

        }
        Collections.reverse(sup_plan);
        return sup_plan;
    }

    @Override
    public Map<Variable, Object> getInitialeState() {
        return this.initialState;
    }

    @Override
    public Set<Action> getActions() {
        return this.actions;
    }

    @Override
    public Goal getGoal() {
        return this.goal;
    }

    @Override
    public BigInteger getNbNodes() {
        return null;
    }
}