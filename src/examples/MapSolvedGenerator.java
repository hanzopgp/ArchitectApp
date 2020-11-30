package examples;

import representation.Constraint;
import representation.Variable;
import solvers.BacktrackSolverMultipleSolution;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Classe qui stocke toutes les map qui ont etes resolus
 */
public class MapSolvedGenerator {

    private final List<Map<Variable, Object>> listSolvedMap;
    private boolean isCorrect = false;

    /**
     * Constructeur
     * @param setVariable - Ensemble de variables
     * @param setConstraint - Ensemble de contraintes
     */
    public MapSolvedGenerator(Set<Variable> setVariable, Set<Constraint> setConstraint){
        BacktrackSolverMultipleSolution backtrackSolverMultipleSolution = new BacktrackSolverMultipleSolution(setVariable, setConstraint);
        backtrackSolverMultipleSolution.solve();
        this.listSolvedMap = backtrackSolverMultipleSolution.getListSolution();
        this.isCorrect = backtrackSolverMultipleSolution.testSolutions();
    }

    /**
     * Getter de listSolvedMap
     * @return listSolvedMap
     */
    public List<Map<Variable, Object>> getListSolvedMap(){
        return this.listSolvedMap;
    }

    /**
     * Printer du resultat
     */
    public void printResults(){
        System.out.println();
        System.out.println("============= CREATION DE LA BASE DE DONNEE =============");
        System.out.println("* Nombre de solutions recuperees : " + this.listSolvedMap.size());
        System.out.println("* Toutes les solutions recupees sont correct : " + this.isCorrect);
        System.out.println("* Affichage des solutions : ");
        for(Map<Variable, Object> map : this.listSolvedMap){
            System.out.println("--> " + map.values());
        }
    }

}
