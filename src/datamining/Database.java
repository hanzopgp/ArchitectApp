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
        //on parcours les variables de la database
        for(Variable v : this.variables){
            Map<Object, BooleanVariable> items = new HashMap<>();
            //on parcours le domaine de la variable
            for(Object domain : v.getDomain()){
                //on test si le domaine est un boolean ou pas, auquel cas c'est une BooleanVariable
                //et pas une simple Variable et on créé la BooleanVariable correspondante
                if(domain instanceof Boolean){
                    BooleanVariable booleanVariable = new BooleanVariable(v.getName());
                    //Si le domaine correspond à false, alors on renvoie un item null dans la map
                    //autrement, on renvoie la valeur de l'item en cours
                    if(domain.equals(false)){
                        items.put(domain, null);
                    }else{
                        items.put(domain, booleanVariable);
                    }
                }else{
                    BooleanVariable booleanVariable = new BooleanVariable(v.getName()+domain);
                    items.put(domain, booleanVariable);
                }
            }
            result.put(v, items);
        }
        return result;
    }


    public BooleanDatabase propositionalize(){
        Set<BooleanVariable> data = new HashSet<>();
        Map<Variable, Map<Object, BooleanVariable>> items = this.itemTable();

        Set<Variable> variables = items.keySet();

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
