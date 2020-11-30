package examples;

import representation.Constraint;
import representation.Variable;
import solvers.*;

import java.util.*;

/**
 * Cette classe permet d'utiliser differents solveurs de contraintes, sur l'exemple du fil rouge
 */
public class HouseSolvers {

    Set<Variable> setVariable;
    Set<Constraint> setConstraint;
    Map<Variable, Object> mapSolved;
    String solverUsed;
    long timeTaken;

    /**
     * Constructeur
     * @param setVariable - Ensemble de variables
     * @param setConstraint - Ensemble de contraintes
     */
    public HouseSolvers(Set<Variable> setVariable, Set<Constraint> setConstraint) {
        this.setVariable = setVariable;
        this.setConstraint = setConstraint;
        this.mapSolved = new HashMap<>();
    }

    /**
     * Methode permettant de resoudre le probleme grâce a l'algorithme Backtrack
     */
    public void solveWithBacktrack(){
        long start = System.currentTimeMillis();
        BacktrackSolver backtrackSolver = new BacktrackSolver(this.setVariable, this.setConstraint);
        this.mapSolved = backtrackSolver.solve();
        this.solverUsed = "Backtrack solver";
        long end = System.currentTimeMillis();
        this.timeTaken = end - start;
    }

    /**
     * Methode permettant de resoudre le probleme grâce a l'algorithme MacAndHeuristic
     */
    public void solveWithMacAndHeuristic(){
        long start = System.currentTimeMillis();
        VariableHeuristic variableHeuristic = new NbConstraintsVariableHeuristic(this.setVariable, this.setConstraint, true);
        ValueHeuristic valueHeuristic = new RandomValueHeuristic(new Random());
        HeuristicMACSolver heuristicMACSolver = new HeuristicMACSolver(this.setVariable, this.setConstraint, variableHeuristic, valueHeuristic);
        this.mapSolved = heuristicMACSolver.solve();
        this.solverUsed = "Mac solver with heuristic";
        long end = System.currentTimeMillis();
        this.timeTaken = end - start;
    }

    /**
     * Methode permettant de resoudre le probleme grâce a l'algorithme MAC
     */
    public void solveWithMac(){
        long start = System.currentTimeMillis();
        MACSolver macSolver = new MACSolver(this.setVariable, this.setConstraint);
        this.mapSolved = macSolver.solve();
        this.solverUsed = "Mac solver";
        long end = System.currentTimeMillis();
        this.timeTaken = end - start;
    }

    /**
     * Printer du resultat
     */
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

    /**
     * Printer de la maison generee
     */
    public void printHouse(){
        System.out.println();
        System.out.println("============= PLAN DE LA MAISON =============");
        int cpt = 0;
        for (Map.Entry<Variable, Object> entry : this.mapSolved.entrySet()) {
            cpt = MapSolvedGenerator.printer(cpt, entry);
        }
    }

    /**
     * Getter de mapSolved
     * @return mapSolved
     */
    public Map<Variable, Object> getMapSolved() {
        return this.mapSolved;
    }
}
