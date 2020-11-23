//package planning;
//
//import representation.Variable;
//import java.util.*;
//
//public class BFSPlanner implements Planner{
//
//    private Map<Variable, Object> etatInitial;
//    private Map<Map<Variable,Object>,Map<Variable,Object>> father;
//    private Map<Map<Variable,Object>,Action> plan;
//    private Set<Action> actions;
//    private Goal but;
//
//    public BFSPlanner(Map<Variable, Object> etatInitial,Map<Map<Variable,Object>,Map<Variable,Object>> father, Map<Map<Variable,Object>,Action> plan, Set<Action> actions, Goal but) {
//        this.etatInitial = etatInitial;
//        this.father = father;
//        this.plan = plan;
//        this.actions = actions;
//        this.but = but;
//    }
//
//    @Override
//    public List<Action> plan() {
//        return this.bfs(this.father, this.plan,  new hashMap<Map<Variable, Object>>(), new LinkedList<Map<Variable, Object>>());
//    }
//
//    public List<Action> bfs(Map<Map<Variable,Object>,Map<Variable,Object>> father, Map<Map<Variable,Object>,Action> plan, Set<Map<Variable, Object>> closed, LinkedList<Map<Variable, Object>> open){
//        Map<Variable, Object> next = new HashMap<Variable, Object>();
//        Deque<Map<Variable, Object>> deque = new LinkedList<String>();
//        father.put(this.getInitialeState(),null);
//        if(this.getGoal().isSatisfiedBy(this.getInitialeState())){
//            return new List<Action>();
//        }else{
//            while(!open.isEmpty()){
//              this.etatInitial = deque.add(open);
//              closed.add(this.etatInitial);
//              for(Action a : this.actions){
//                if(a.isApplicable(this.etatInitial)){
//                  next = a.successor(this.etatInitial);
//                }else if(!closed.contains(next) && !open.contains(next)){
//                  father.put(next,this.etatInitial);
//                  plan.put(next,this.action);
//                  if(this.getGoal().isSatisfiedBy(next)){
//                    return get_bfs_plan(father, plan, next);
//                  }else{
//                    open.add(next);
//                  }
//                }
//              }
//            }
//            return null;
//        }
//    }
//
//
//
//
//
//    public Queue<Variable> get_bfs_plan(Map<Map<Variable,Object>,Map<Variable,Object>> father, Map<Map<Variable,Object>,Set<Action>> plan){
//        Queue<Variable> bfs_plan = new PriorityQueue<Variable>();
//        ArrayList<Queue<Variable>> c = new ArrayList<Queue<Variable>>();
//        while(!this.but.isEmpty()){
//          bfs_plan.add(plan.put(next));
//          this.but.removeLast();
//          this.but.put(father,this.but);
//        }
//        c = Collections.reverse(bfs_plan);
//        return c;
//    }
//
//
//    @Override
//    public Map<Variable, Object> getInitialeState() {
//        return this.etatInitial;
//    }
//
//    @Override
//    public Set<Action> getActions() {
//        return this.actions;
//    }
//
//    @Override
//    public Goal getGoal() {
//        return this.but;
//    }
//}
