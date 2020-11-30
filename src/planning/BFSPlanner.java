package planning;
import java.util.*;
import representation.*;


/**
 * Cette classe represente un planificateur utilisant l'algorithme BFS.
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
     * @param initialState -> etat initial
     * @param actions -> actions qui pourront etre testee
     * @param goal -> but a obtenir
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
        /** tant que tous les etats n'ont pas ete ouvert **/
        while(!open.isEmpty()){
            instantiation = open.pollFirst();
            open.remove(instantiation);
            closed.add(instantiation);
            /** boucle qui itere les actions **/
            for(Action a : this.actions){
                /** test l'action **/
                if(a.isApplicable(instantiation)){
                    next = a.successor(instantiation);
                    /** test la liste des ouverts et des etats deja explore **/
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
     * Methode recursive permettant de calculer un plan a partir des structures
     * produites par la recherche.
     * La methode sera appelee a la fin de la recherche de bfs().
     * 
     * @param father -> possede le father des etats visites
     * @param plan -> les etapes faites precedement = plan actuel
     * @param goal2 -> but suivant a valider
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