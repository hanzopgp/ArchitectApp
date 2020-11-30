package examples;

import representation.Constraint;
import representation.Variable;
import solvers.BacktrackSolverMultipleSolution;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Classe qui stocke toutes les map qui ont étés résolus
 */
public class MapSolvedGenerator {

    private final List<Map<Variable, Object>> listSolvedMap;
    private boolean isCorrect = false;
    private long timeTaken = 0;

    /**
     * Constructeur
     * @param setVariable - Ensemble de variables
     * @param setConstraint - Ensemble de contraintes
     */
    public MapSolvedGenerator(Set<Variable> setVariable, Set<Constraint> setConstraint){
        long start = System.currentTimeMillis();
        BacktrackSolverMultipleSolution backtrackSolverMultipleSolution = new BacktrackSolverMultipleSolution(setVariable, setConstraint);
        backtrackSolverMultipleSolution.solve();
        this.listSolvedMap = backtrackSolverMultipleSolution.getListSolution();
        this.isCorrect = backtrackSolverMultipleSolution.testSolutions();
        long end = System.currentTimeMillis();
        this.timeTaken = end - start;
    }

    /**
     * Getter de listSolvedMap
     * @return listSolvedMap
     */
    public List<Map<Variable, Object>> getListSolvedMap(){
        return this.listSolvedMap;
    }

    /**
     * Printer du résultat
     */
    public void printResults(){
        System.out.println();
        System.out.println("============= CREATION DE LA BASE DE DONNEE =============");
        System.out.println("* Affichage des solutions : ");
        //        for(Map<Variable, Object> map : this.listSolvedMap){
//            System.out.println("--> " + map.values());
//        }
        this.printHouse();
        System.out.println("* Nombre de solutions recuperees : " + this.listSolvedMap.size());
        System.out.println("* Toutes les solutions recupees sont correct : " + this.isCorrect);
        System.out.println("* Temps pour la creation de la base de donnee : " + this.timeTaken + "ms");
    }

    public void printHouse(){
        System.out.println();
        //System.out.println("============= PLAN DE LA MAISON =============");
        int cpt = 0;
        for(Map<Variable, Object> map : this.listSolvedMap){
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            for (Map.Entry<Variable, Object> entry : map.entrySet()) {
                cpt = printer(cpt, entry);
            }
        }
    }

    static int printer(int cpt, Map.Entry<Variable, Object> entry) {
        if(!(entry.getValue().equals(false)) && !(entry.getValue().equals(true))){
            cpt++;
            System.out.format("%14s", "[" + entry.getValue() + "]\t\t");
            if(cpt% HouseDemo.WIDTH == 0){
                System.out.println();
            }
        }
        return cpt;
    }

}
