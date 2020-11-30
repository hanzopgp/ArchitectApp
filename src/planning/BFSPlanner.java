package planning;
import java.util.*;
import representation.*;


/**
 * Cette classe représente un planificateur utilisant l'algorithme BFS.
 * Cette algorithme effectue dans un graphe une recherche en largeur. 
 * L'algorithme trouve le plan le plus court en premier mais peut explorer 
 * inutilement des noeuds. 
 */
public class BFSPlanner implements Planner{

    private final Map<Variable, Object> initialState;
    private final Set<Action> actions;
    private final Goal goal;



    /**
     * 
     * @param initialState -> état initial
     * @param actions -> actions qui pourront être testée
     * @param goal -> but à obtenir
     */
    public BFSPlanner(Map<Variable, Object> initialState, Set<Action> actions, Goal goal) {
        this.initialState = initialState;
        this.actions = actions;
        this.goal = goal;
    }

    @Override
    public List<Action> plan() {
        return bfs();
    }

    @Override
    public Map<Variable, Object> getInitialeState() {
        return this.initialState;
    }



    /**
     * Algorithme de recherche en largeur : BFS
     * 
     * @return -> plan obtenu
     */
    public List<Action> bfs(){
        Map<Map<Variable, Object>, Map<Variable, Object>> father = new HashMap<Map<Variable, Object>, Map<Variable, Object>>(); 
        Map<Map<Variable, Object>, Action> plan = new HashMap<Map<Variable, Object>, Action>();
        Set<Map<Variable, Object>> closed = new HashSet<Map<Variable, Object>>();
        LinkedList<Map<Variable, Object>> open = new LinkedList<Map<Variable, Object>>();
        Map<Variable, Object> next = new HashMap<Variable, Object>();
        Map<Variable, Object> instantiation = new HashMap<>();
        closed.add(this.initialState);
        open.add(initialState);
        father.put(this.initialState, null);
        /** test le but **/
        if (this.getGoal().isSatisfiedBy(initialState)) {
            return new LinkedList<Action>();
        }
        /** tant que tous les états n'ont pas été ouvert **/
        while(!open.isEmpty()){
            instantiation = open.pollFirst();
            open.remove(instantiation);
            closed.add(instantiation);
            /** boucle qui itère les actions **/
            for(Action a : this.actions){
                /** test l'action **/
                if(a.isApplicable(instantiation)){
                    next = a.successor(instantiation);
                    /** test la liste des ouverts et des états déjà exploré **/
                    if(!closed.contains(next) && !open.contains(next)){
                        father.put(next,instantiation);
                        plan.put(next,a);
                        if(this.getGoal().isSatisfiedBy(next)){
                            return get_bfs_plan(father, plan, next);
                        }else{
                            open.add(next);
                        }
                    }
                }
            }
        }
        /** pas d'action possible pour le plan **/
        return null;
    }



    /**
     * Méthode récursive permettant de calculer un plan à partir des structures
     * produites par la recherche.
     * La méthode sera appelée à la fin de la recherche de bfs().
     * 
     * @param father -> possède le father des états visités
     * @param plan -> les étapes faites précédement = plan actuel
     * @param goal2 -> but suivant à valider
     * @return -> reconstruction du plan
     */
    public List<Action> get_bfs_plan(Map<Map<Variable,Object>,Map<Variable,Object>> father,
                                     Map<Map<Variable,Object>,Action> plan,
                                     Map<Variable, Object> goal2){
        List<Action> bfs_plan = new LinkedList<Action>();
        while(goal2 != this.initialState){
          bfs_plan.add(plan.get(goal2));
          goal2 = father.get(goal2);
        }
        Collections.reverse(bfs_plan);
        return bfs_plan;
    }

    @Override
    public Set<Action> getActions(){
        return this.actions;
    }

    @Override
    public Goal getGoal(){
        return this.goal;
    }


}