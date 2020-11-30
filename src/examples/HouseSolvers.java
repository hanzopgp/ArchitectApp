package examples;

import representation.Constraint;
import representation.Variable;
import solvers.*;

import java.util.HashMap;
import java.util.List;
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

    public void solveWithMacAndHeuristic(){
        long start = System.currentTimeMillis();
        VariableHeuristic variableHeuristic = new VariableHeuristic() {
            @Override
            public Variable best(Set<Variable> variables, Map<Variable, Set<Object>> domains) {
                return null;
            }
        };
        ValueHeuristic valueHeuristic = new ValueHeuristic() {
            @Override
            public List<Object> ordering(Variable variable, Set<Object> domain) {
                return null;
            }
        };
        HeuristicMACSolver heuristicMACSolver = new HeuristicMACSolver(setVariable, setConstraint, variableHeuristic, valueHeuristic);
        this.mapSolved = heuristicMACSolver.solve();
        this.solverUsed = "Mac solver with heuristic";
        long end = System.currentTimeMillis();
        this.timeTaken = end - start;
    }

    public void solveWithMac(){
        long start = System.currentTimeMillis();
        MACSolver macSolver = new MACSolver(setVariable, setConstraint);
        this.mapSolved = macSolver.solve();
        this.solverUsed = "Mac solver";
        long end = System.currentTimeMillis();
        this.timeTaken = end - start;
    }

    public void printResults(){
        System.out.println();
        System.out.println("============= LISTE DES AFFECTATIONS =============");
        for (Map.Entry<Variable, Object> entry : this.mapSolved.entrySet()) {
            System.out.println("--> Variable : " + entry.getKey() + ", Affectation : " + entry.getValue());
        }
        this.printHouse();
        System.out.println("* Solver utilise : " + this.solverUsed);
        System.out.println("* Temps mit par le solveur : " + this.timeTaken + "ms");
    }

    public void printHouse(){
        System.out.println();
        System.out.println("============= PLAN DE LA MAISON =============");
        int cpt = 0;
        for (Map.Entry<Variable, Object> entry : this.mapSolved.entrySet()) {
            if(!(entry.getValue().equals(false)) && !(entry.getValue().equals(true))){
                cpt++;
                System.out.print("["+entry.getValue() + "]\t\t");
                if(cpt%HouseDemo.WIDTH == 0){
                    System.out.println();
                }
            }
        }
    }

    public Map<Variable, Object> getMapSolved() {
        return this.mapSolved;
    }
}
