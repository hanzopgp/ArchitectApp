package examples;

import representation.Constraint;
import representation.Variable;
import solvers.BacktrackSolver;
import solvers.BacktrackSolverMultipleSolution;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapSolvedGenerator {

    List<Map<Variable, Object>> listSolvedMap;

    public MapSolvedGenerator(Set<Variable> setVariable, Set<Constraint> setConstraint){
        BacktrackSolverMultipleSolution backtrackSolverMultipleSolution = new BacktrackSolverMultipleSolution(setVariable, setConstraint);
        backtrackSolverMultipleSolution.solve();
        System.out.println("Nombre de solutions recuperees : " + backtrackSolverMultipleSolution.getListSolution().size());
        System.out.println("Toutes les solutions recupees sont correct : " + backtrackSolverMultipleSolution.testSolutions());
        System.out.println("Affichage des solutions : ");
        for(Map<Variable, Object> map : backtrackSolverMultipleSolution.getListSolution()){
            System.out.println(map.values());
        }
    }

    public List<Map<Variable, Object>> getListSolvedMap(){
        return this.listSolvedMap;
    }

}
