package planning;

import java.util.*;

import planning.Action;
import planning.Goal;
import planning.Heuristic;
import representation.*;


/**
 * Cette classe represente un planificateur utilisant l'algorithme A*.
 * Cette algorithme se termine des qu'il a atteint l'un des noeuds solutions. 
 */
public class AStarPlanner implements Planner{

    private final Map<Variable, Object> initialState;
    private final Set<Action> actions;
    private final Goal goal;
    private final Heuristic heuristic;

    /**
     * @param initialState -> etat initial
     * @param actions -> actions qui pourront etre testee
     * @param goal -> but a obtenir
     * @param heuristic -> heuristic
     */
    public AStarPlanner(Map<Variable, Object> initialState, Set<Action> actions, Goal goal, Heuristic heuristic){
        this.initialState = initialState;
        this.actions = actions;
        this.goal = goal;
        this.heuristic = heuristic;
    }


    /**
     * Methode recursive permettant de calculer un plan a partir des structures
     * produites par la recherche.
     * La methode sera appelee ou l'on aura atteint le but.
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


    /**
     * Algorithme retournant la solution la plus rapide : A*
     * 
     * @return -> plan obtenu
     */
    @Override
    public List<Action> plan(){
        Map<Map<Variable, Object>, Action> plan = new HashMap<Map<Variable, Object>, Action>();
        Map<Map<Variable, Object>, Map<Variable, Object>> father = new HashMap<Map<Variable, Object>, Map<Variable, Object>>();
        Map<Map<Variable, Object>, Double> distance = new HashMap<>();
        Map<Map<Variable, Object>, Double> value = new HashMap<>();

        //List<Map<Variable, Object>> open = new LinkedList<Map<Variable, Object>>();
        PriorityQueue<Map<Variable, Object>> open = new PriorityQueue<>((state1, state2) -> (Double.valueOf(value.get(state1))).compareTo(Double.valueOf(value.get(state2))));

        Map<Variable, Object> instanciation = new HashMap<Variable, Object>();
        //Comparator<Map<Variable, Object>> com = (state1, state2) -> value.get(state1) - value.get(state2);
        Map<Variable, Object> next = new HashMap<Variable, Object>();

        open.add(initialState);
        father.put(this.initialState, null);
        distance.put(initialState, 0.0);
        value.put(this.initialState, Float.valueOf(this.heuristic.estimate(this.initialState)).doubleValue());

        /** tant que tous les etats n'ont pas ete ouvert **/
        while(!open.isEmpty()){
            /** donne a instanciation l'element le plus petit **/
            //instanciation = Collections.min(open, com);
            instanciation = open.poll();
            if(this.getGoal().isSatisfiedBy(instanciation)){
                return get_bfs_plan(father, plan, instanciation);
            }else{
                open.remove(instanciation);
                for(Action act : this.actions){
                    /** test l'action **/
                    if(act.isApplicable(instanciation)){
                        next = act.successor(instanciation);
                    /** test la liste des ouverts et des etats deja explore **/
                        if(!distance.containsKey(next)){
                            distance.put(next, Double.MAX_VALUE);
                        }
                    /** regarde si le coût calcule est inferieur a celui retenu precedemment **/
                        if(distance.get(next) > distance.get(instanciation) + act.getCost()){
                            distance.put(next, distance.get(instanciation) + act.getCost());
                            value.put(next, distance.get(next) + this.heuristic.estimate(next));
                            father.put(next, instanciation);
                            plan.put(next, act);
                            open.add(next);
                        }
                    }
                }
            }
        }
        /** pas d'action possible pour le plan **/
        return null;
    }

    @Override
    public Map<Variable, Object> getInitialeState() {
        return this.initialState;
    }


    public Heuristic getHeuristic(){
        return this.heuristic;
    }

    @Override
    public Set<Action> getActions() {
        return this.actions;
    }

    @Override
    public Goal getGoal() {
        return this.goal;
    }

}


    /*@Override
    public List<Action> plan(){

        Map<Map<Variable, Object>, Action> plan = new HashMap<Map<Variable, Object>, Action>();
        Map<Map<Variable, Object>, Map<Variable, Object>> father = new HashMap<Map<Variable, Object>, Map<Variable, Object>>();
        Map<Map<Variable, Object>, Integer> distance = new HashMap<Map<Variable, Object>, Integer>();
        Map<Map<Variable, Object>, Integer> value = new HashMap<Map<Variable, Object>, Integer>();
        List<Map<Variable, Object>> open = new LinkedList<Map<Variable, Object>>();
        Map<Variable, Object> instanciation = new HashMap<Variable, Object>();
        Comparator<Map<Variable, Object>> com = (state1, state2) -> value.get(state1) - value.get(state2);
        Map<Variable, Object> next = new HashMap<Variable, Object>();

        open.add(initialState);
        father.put(this.initialState, null);
        distance.put(initialState, 0);
        value.put(initialState, heuristic);

        while(!open.isEmpty()){
            instanciation = Collections.min(open, com);
            if(this.getGoal().isSatisfiedBy(instanciation)){
                return get_bfs_plan(father, plan, instanciation);
            }else{
                open.remove(instanciation);
                for(Action act : this.actions){
                    if(act.isApplicable(instanciation)){
                        next = act.successor(instanciation);
                        if(!distance.containsKey(next)){
                            distance.put(next, Integer.MAX_VALUE);
                        }
                        if(distance.get(next) > distance.get(instanciation) + act.getCost()){
                            distance.put(next, distance.get(instanciation) + act.getCost());
                            value.put(next, distance.get(next) + next.getHeuristic());
                            father.put(next, instanciation);
                            plan.put(next, act);
                            open.add(next);
                        }
                    }
                }
            }
        }
        return null;
    }*/
