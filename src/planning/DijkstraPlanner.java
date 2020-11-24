package planning;

import java.util.*;

import representation.*;

public class DijkstraPlanner implements Planner {

    private Map<Variable, Object> initialState;
    private Set<Action> actions;
    private Goal goal;

    public DijkstraPlanner(Map<Variable, Object> initialState, Set<Action> actions, Goal goal) {
        this.initialState = initialState;
        this.actions = actions;
        this.goal = goal;
    }

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

        while (!open.isEmpty()) {
            instantiation = Collections.min(open, com);
            open.remove(instantiation);
            if (this.getGoal().isSatisfiedBy(instantiation)) {
                goals.add(instantiation);
            }
            for (Action act : this.actions) {
                if (act.isApplicable(instantiation)) {
                    next = act.successor(instantiation);

                    if (!distance.containsKey(next)) {
                        distance.put(next, Integer.MAX_VALUE);
                    }
                    if (distance.get(next) > distance.get(instantiation) + act.getCost()) {
                        distance.put(next, distance.get(instantiation) + act.getCost());
                        father.put(next, instantiation);
                        plan.put(next, act);
                        open.add(next);
                    }
                }
            }

        }
        if (goals.isEmpty()) {
            return null;
        } else {
            return get_dijkstra_plan(father, plan, goals, distance);
        }
    }

    @Override
    public Map<Variable, Object> getInitialeState() {
        return this.initialState;
    }

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
    public Set<Action> getActions() {
        return this.actions;
    }

    @Override
    public Goal getGoal() {
        return this.goal;
    }
}