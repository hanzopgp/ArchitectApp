package planning;

import representation.Variable;

import java.util.*;


/**
 * Cette classe représente un planificateur utilisant l'algorithme DFS.
 * Cette algorithme effectue dans un graphe une recherche en profondeur. 
 * Il va parcourir ou/et rechercher des structures de données arborescentes ou graphiques. 
 */
public class DFSPlanner implements Planner{

    private final Map<Variable, Object> etatInitial;
    private final Set<Action> actions;
    private final Goal but;


    /**
     * @param etatState -> état initial
     * @param actions -> actions qui pourront être testée
     * @param goal -> but à obtenir
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
     * @param etatInitial -> état actuel
     * @param plan -> les étapes faites précédement = plan actuel
     * @param closed -> liste des états déjà explorés
     * @return -> plan obtenu
     */
    public List<Action> dfs(Map<Variable, Object> etatInitial, LinkedList<Action> plan, Set<Map<Variable, Object>> closed){
        Map<Variable, Object> next = new HashMap<Variable, Object>();
        /** test le but **/
        if(this.getGoal().isSatisfiedBy(etatInitial)){
            return plan;
        }else{
            /** boucle qui itère les actions **/
            for(Action a : this.actions){
                /** test l'action **/
                if(a.isApplicable(etatInitial)){
                    next = a.successor(etatInitial);
                    /** test la liste des états déjà exploré **/
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
