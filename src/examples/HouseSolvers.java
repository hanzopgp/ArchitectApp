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
    long timeTaken;

    public HouseSolvers(Set<Variable> setVariable, Set<Constraint> setConstraint) {
        this.setVariable = setVariable;
        this.setConstraint = setConstraint;
        this.mapSolved = new HashMap<>();
    }

    public void solveWithBacktrack(){
        long start = System.currentTimeMillis();
        BacktrackSolver backtrackSolver = new BacktrackSolver(this.setVariable, this.setConstraint);
        this.mapSolved = backtrackSolver.solve();
        this.solverUsed = "Backtrack solver";
        long end = System.currentTimeMillis();
        this.timeTaken = end - start;
    }

//    public void solveWithMacAndHeuristic(){
//        long start = System.currentTimeMillis();
//        HeuristicMACSolver heuristicMACSolver = new HeuristicMACSolver(setVariable, setConstraint, );
//        this.mapSolved = heuristicMACSolver.solve();
//        this.solverUsed = "Mac solver with heuristic";
//        long end = System.currentTimeMillis();
//        this.timeTaken = end - start;
//    }
//
//    public void solveWithMac(){
//        long start = System.currentTimeMillis();
//        MacSolver macSolver = new MacSolver(setVariable, setConstraint);
//        this.mapSolved = macSolver.solve();
//        this.solverUsed = "Mac solver";
//        long end = System.currentTimeMillis();
//        this.timeTaken = end - start;
//    }

    public void printResults(){
        for (Map.Entry<Variable, Object> entry : this.mapSolved.entrySet()) {
            System.out.println("--> Variable : " + entry.getKey() + ", Affectation : " + entry.getValue());
        }
        System.out.println("* Solver utilise : " + this.solverUsed);
        System.out.println("* Temps mit par le solveur : " + this.timeTaken);
    }

    public Map<Variable, Object> getMapSolved() {
        return mapSolved;
    }
}
