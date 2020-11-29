package examples;

import representation.Constraint;
import representation.Variable;
import solvers.BacktrackSolver;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapSolvedGenerator {

    List<Map<Variable, Object>> listSolvedMap;

    public MapSolvedGenerator(Set<Variable> setVariable, Set<Constraint> setConstraint){
        BacktrackSolver backtrackSolver = new BacktrackSolver(setVariable, setConstraint);
        backtrackSolver.initListForbiddenMap();
        backtrackSolver.setSimulation(true);
        for(int i = 0; i < HouseDemo.NB_HOUSE_DATAMINING; i++){
            Map<Variable, Object> item = backtrackSolver.solve();
            backtrackSolver.addForbiddenItem(item);
        }
        backtrackSolver.setSimulation(false);
        this.listSolvedMap = backtrackSolver.getListForbiddenMap();
    }

    public List<Map<Variable, Object>> getListSolvedMap(){
        return this.listSolvedMap;
    }

}
