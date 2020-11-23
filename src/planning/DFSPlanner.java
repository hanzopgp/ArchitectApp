package planning;

import representation.Variable;

import java.util.*;

public class DFSPlanner implements Planner{

    private final Map<Variable, Object> etatInitial;
    private final Set<Action> actions;
    private final Goal but;

    public DFSPlanner(Map<Variable, Object> etatInitial, Set<Action> actions, Goal but) {
        this.etatInitial = etatInitial;
        this.actions = actions;
        this.but = but;
    }

    @Override
    public List<Action> plan() {
        return this.dfs(this.etatInitial, this.actions, new LinkedList<Action>(), this.but, new HashSet<Map<Variable, Object>>());
    }

    public List<Action> dfs(Map<Variable, Object> etatInitial, Set<Action> actions, LinkedList<Action> plan, Goal but, Set<Map<Variable, Object>> closed){
        Map<Variable, Object> next = new HashMap<Variable, Object>();
        if(this.getGoal().isSatisfiedBy(etatInitial)){
            return plan;
        }else{
            for(Action a : this.actions){
                if(a.isApplicable(etatInitial)){
                    next = a.successor(etatInitial);
                    if(!(closed.contains(next))){
                        closed.add(next);
                        plan.add(a);
                        List<Action> subPlan = dfs(etatInitial, actions, plan, but, closed);
                        if(subPlan != null){
                            return subPlan;
                        }else{
                            plan.removeLast();
                            return plan;
                        }
                    }
                }
            }
        }
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
