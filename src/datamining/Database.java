package datamining;

import representation.BooleanVariable;
import representation.Variable;

import java.util.*;

public class Database {

    private Set<Variable> variables;
    private List<Map<Variable, Object>> instances;

    public Database(Set<Variable> variables) {
        this.variables = variables;
    }

    public void add(Map<Variable, Object> value){
        this.instances.add(value);
    }

    public Map<Variable, Map<Object, BooleanVariable>> itemTable(){
        Map<Variable, Map<Object, BooleanVariable>> result = new HashMap<>();

        for(Variable v : this.variables){

        }

        return result;
    }


    public BooleanDatabase propositionalize(){
        Set<BooleanVariable> data = new HashSet<>();
        Map<Variable, Map<Object, BooleanVariable>> items = this.itemTable();



        return null;
    }


    /*======= GETTERS =========*/

    public List<Map<Variable, Object>> getInstances(){
        return this.instances;
    }

    public Set<Variable> getVariables() {
        return variables;
    }

    public void setVariables(Set<Variable> variables) {
        this.variables = variables;
    }
}
