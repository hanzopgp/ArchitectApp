//package planning;
//
//import java.util.*;
//
//import planning.Action;
//import planning.Goal;
//import planning.Heuristic;
//import representation.*;
//
//public class AStarPlanner implements Planner{
//
//    private Map<Variable, Object> initialState;
//    private Set<Action> actions;
//    private Goal goal;
//    private Heuristic heuristic;
//
//    public AStarPlanner(Map<Variable, Object> initialState, Set<Action> actions, Goal goal, Heuristic heuristic){
//        this.initialState = initialState;
//        this.actions = actions;
//        this.goal = goal;
//        this.heuristic = heuristic;
//    }
//
//
//    @Override
//    public List<Action> plan(){
//    /*
//        Map<Map<Variable, Object>, Action> plan = new HashMap<Map<Variable, Object>, Action>();
//        Map<Map<Variable, Object>, Map<Variable, Object>> father = new HashMap<Map<Variable, Object>, Map<Variable, Object>>();
//        Map<Map<Variable, Object>, Integer> distance = new HashMap<Map<Variable, Object>, Integer>();
//        Map<Map<Variable, Object>, Integer> value = new HashMap<Map<Variable, Object>, Integer>();
//        List<Map<Variable, Object>> open = new LinkedList<Map<Variable, Object>>();
//        Map<Variable, Object> instanciation = new HashMap<Variable, Object>();
//        Comparator<Map<Variable, Object>> com = (state1, state2) -> value.get(state1) - value.get(state2);
//        Map<Variable, Object> next = new HashMap<Variable, Object>();
//
//        open.add(initialState);
//        father.put(this.initialState, null);
//        distance.put(initialState, 0);
//        value.put(initialState, heuristic);
//
//        while(!open.isEmpty()){
//            instanciation = Collections.min(open, com);
//            if(this.getGoal().isSatisfiedBy(instanciation)){
//                return get_bfs_plan(father, plan, instanciation);
//            }else{
//                open.remove(instanciation);
//                for(Action act : this.actions){
//                    if(act.isApplicable(instanciation)){
//                        next = act.successor(instanciation);
//                        if(!distance.containsKey(next)){
//                            distance.put(next, Integer.MAX_VALUE);
//                        }
//                        if(distance.get(next) > distance.get(instanciation) + act.getCost()){
//                            distance.put(next, distance.get(instanciation) + act.getCost());
//                            value.put(next, distance.get(next) + next.getHeuristic());
//                            father.put(next, instanciation);
//                            plan.put(next, act);
//                            open.add(next);
//                        }
//                    }
//                }
//            }
//        }
//        return null;*/
//        return null;
//    }
//
//
//
//    public Heuristic getHeuristic(){
//        return this.heuristic;
//    }
//
//
//    @Override
//    public Map<Variable, Object> getInitialeState() {
//        return this.initialState;
//    }
//
//    @Override
//    public Set<Action> getActions() {
//        return this.actions;
//    }
//
//    @Override
//    public Goal getGoal() {
//        return this.goal;
//    }
//
//}