package planning;
import java.util.*;
import representation.*;
//import solvers.*;

public class BFSPlanner implements Planner{

    private Map<Variable, Object> initialState;
    private Set<Action> actions;
    private Goal goal;

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
        if (this.getGoal().isSatisfiedBy(initialState)) {
            return new LinkedList<Action>();
        }
        while(!open.isEmpty()){
            instantiation = open.pollFirst();
            open.remove(instantiation);
            closed.add(instantiation);
            for(Action a : this.actions){
                if(a.isApplicable(instantiation)){
                    next = a.successor(instantiation);
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
        return null;
    }


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