package examples;

import datamining.Database;
import representation.BooleanVariable;
import representation.Variable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HouseDatamining {

    private final HouseRepresentation houseRepresentation;

    public HouseDatamining(HouseRepresentation houseRepresentation){
        this.houseRepresentation = houseRepresentation;
    }

    public void mine(){
        Database database = new Database(HouseDemo.listToSetVariable(this.houseRepresentation.getListVariable()));

        List<Variable> listVariable = this.houseRepresentation.getListVariable();
        BooleanVariable dalleCoulee = this.houseRepresentation.getDalleCoulee();
        BooleanVariable dalleHumide = this.houseRepresentation.getDalleHumide();
        BooleanVariable mursEleves = this.houseRepresentation.getMursEleves();
        BooleanVariable toitureTerminee = this.houseRepresentation.getToitureTerminee();

        // on cree les differentes instances pour la BD
        database.add(new HashMap<>());
        Map<Variable, Object> instance = new HashMap<>();
        instance.put(dalleCoulee, true);
        database.add(new HashMap<>(instance));
        instance.put(dalleHumide, true);
        database.add(new HashMap<>(instance));
        instance.put(dalleHumide, false);
        database.add(new HashMap<>(instance));
        instance.put(mursEleves, true);
        database.add(new HashMap<>(instance));
        instance.put(toitureTerminee, true);
        database.add(new HashMap<>(instance));

    }

    public void printResults(){

    }

}
