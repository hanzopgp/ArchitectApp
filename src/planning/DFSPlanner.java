package planning;

import representation.Variable;

import java.util.*;


/**
 * Cette classe represente un planificateur utilisant l'algorithme DFS.
 * Cette algorithme effectue dans un graphe une recherche en profondeur. 
 * Il va parcourir ou/et rechercher des structures de donnees arborescentes ou graphiques. 
 */
public class DFSPlanner implements Planner{

    private final Map<Variable, Object> etatInitial;
    private final Set<Action> actions;
    private final Goal but;


    /**
     * @param etatState -> etat initial
     * @param actions -> actions qui pourront être testee
     * @param goal -> but a obtenir
     */
    public DFSPlanner(Map<Variable, Object> etatInitial, Set<Action> actions, Goal but) {
        this.etatInitial = etatInitial;
        this.actions = actions;
        this.but = but;
    }

    @Override
    public List<Action> plan() {
        return this.dfs(this.etatInitial, new LinkedList<Action>(), new HashSet<Map<Variable, Object>>());
    }


    /**
     * Algorithme de recherche en profondeur : DFS
     * 
     * @param etatInitial -> etat actuel
     * @param plan -> les etapes faites precedement = plan actuel
     * @param closed -> liste des etats deja explores
     * @return -> plan obtenu
     */
    public List<Action> dfs(Map<Variable, Object> etatInitial, LinkedList<Action> plan, Set<Map<Variable, Object>> closed){
        Map<Variable, Object> next = new HashMap<Variable, Object>();
        /** test le but **/
        if(this.getGoal().isSatisfiedBy(etatInitial)){
            return plan;
        }else{
            /** boucle qui itere les actions **/
            for(Action a : this.actions){
                /** test l'action **/
                if(a.isApplicable(etatInitial)){
                    next = a.successor(etatInitial);
                    /** test la liste des etats deja explore **/
                    if(!(closed.contains(next))){
                        closed.add(next);
                        plan.add(a);
                        List<Action> subPlan = dfs(next, plan, closed);
                        if(subPlan != null){
                            return subPlan;
                        }else{
                            plan.removeLast();
                        }
                    }
                }
            }
        }
        /** pas d'actions possible pour le plan**/
        return null;
    }

    @Override
    public Map<Variable, Object> getInitialeState() {
        return this.etatInitial;
    }

    @Override
    public Set<Action> getActions() {
        return this.actions;
    }

    @Override
    public Goal getGoal() {
        return this.but;
    }
}
