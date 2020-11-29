package datamining;

import representation.BooleanVariable;
import representation.Variable;

import java.util.*;

/**
 * Classe qui correspond à un ensemble de variables non booléennes
 */
public class Database {

    private Set<Variable> variables;
    private List<Map<Variable, Object>> instances;

    /**
     * Constructeur
     * @param variables - Ensemble de variables non booléennes
     */
    public Database(Set<Variable> variables) {
        this.variables = variables;
        this.instances = new ArrayList<>();
    }

    /**
     * Permet d'ajouter une instance à la database
     * @param value
     */
    public void add(Map<Variable, Object> value){
        this.instances.add(value);
    }

    /**
     * Permet de transformer une instance en liste d'items.
     * @return
     */
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

    /**
     * Permet de transformer une liste d'item en une BooleanDatabase
     * @return
     */
    public BooleanDatabase propositionalize(){
        Map<Variable, Map<Object, BooleanVariable>> items = this.itemTable();
        Set<BooleanVariable> booleanVariables = new HashSet<>();

        //Aide pour boucler sur la map :
        // https://www.codingame.com/playgrounds/6162/6-ways-to-iterate-or-loop-a-map-in-java
        for(Map.Entry<Variable, Map<Object, BooleanVariable>> entry : items.entrySet()){
            //on boucle dans chaque map de la map items pour récupérer toutes les variables et les transformer
            //en BooleanVariable
            for(Map.Entry<Object, BooleanVariable> mapOfEntry : entry.getValue().entrySet()){
                BooleanVariable booleanVariable = mapOfEntry.getValue();
                //on peut récupérer des variables null
                if(booleanVariable != null){
                    booleanVariables.add(booleanVariable);
                }
            }
        }

        //On créé une instance de BooleanDatabase en récupérant une partie de ses données finales
        //L'autre partie correspond aux Variable actuellement stockés dans Database que l'on va transformer
        //en BooleanVariable
        BooleanDatabase booleanDatabase = new BooleanDatabase(booleanVariables);


        for(Map<Variable, Object> instanceItem : this.instances){
            Set<BooleanVariable> tmpBooleanVariables = new HashSet<>();
            //Pour chaque item de notre Database actuelle, on la transforme en item d'une BooleanDatabase :
            for(Map.Entry<Variable, Object> mapInstance : instanceItem.entrySet()){
                // on récupère la valeur de la clé associé à "items" grace à la Variable contenu dans mapInstance.getKey(),
                // ce qui renvoie --> Map<Object, BooleanVariable>
                // mapInstance->getValue() renvoie Object, et on récupère la valeur associé à cet objet,
                // donc une BooleanVariable
                if(items.get(mapInstance.getKey()).get(mapInstance.getValue()) instanceof BooleanVariable){
                    BooleanVariable v = items.get(mapInstance.getKey()).get(mapInstance.getValue());
                    tmpBooleanVariables.add(v);
                }
            }
            booleanDatabase.add(tmpBooleanVariables);
        }
        return booleanDatabase;
    }


    /*======= GETTERS =========*/

    /**
     * Getter des instances
     * @return Retourne les instances
     */
    public List<Map<Variable, Object>> getInstances(){
        return this.instances;
    }

    /**
     * Getter des variables
     * @return Retourne les variables
     */
    public Set<Variable> getVariables() {
        return variables;
    }
}
