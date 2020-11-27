package examples;

import representation.Constraint;
import representation.Variable;
import solvers.BacktrackSolver;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HouseSolvers {

    Set<Variable> setVariable;
    Set<Constraint> setConstraint;
    Map<Variable, Object> mapSolved;
    String solverUsed;


    public HouseSolvers(Set<Variable> setVariable, Set<Constraint> setConstraint) {
        this.setVariable = setVariable;
        this.setConstraint = setConstraint;
        this.mapSolved = new HashMap<>();
    }

    public void solveWithBacktrack(){
        BacktrackSolver backtrackSolver = new BacktrackSolver(this.setVariable, this.setConstraint);
        this.mapSolved = backtrackSolver.solve();
        this.solverUsed = "Backtrack solver";
    }

//    public void solveWithMacAndHeuristic(){
//        HeuristicMACSolver heuristicMACSolver = new HeuristicMACSolver(setVariable, setConstraint, );
//        this.mapSolved = heuristicMACSolver.solve();
//        this.solverUsed = "Mac solver with heuristic";
//    }
//
//    public void solveWithMac(){
//        MacSolver macSolver = new MacSolver(setVariable, setConstraint);
//        this.mapSolved = macSolver.solve();
//        this.solverUsed = "Mac solver";
//    }

    public void printResults(){
        System.out.println("Solver utilise : " + this.solverUsed);
        for (Map.Entry<Variable, Object> entry : this.mapSolved.entrySet()) {
            System.out.println("Variable : " + entry.getKey() + ", Affectation : " + entry.getValue());
        }
    }

}
