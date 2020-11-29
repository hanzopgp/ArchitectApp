package examples;

import datamining.Database;

public class HouseDatamining {

    private final HouseRepresentation houseRepresentation;

    public HouseDatamining(HouseRepresentation houseRepresentation){
        this.houseRepresentation = houseRepresentation;
    }

    public void mine(){
        Database database = new Database(HouseDemo.listToSetVariable(this.houseRepresentation.getListVariable()));



    }

    public void printResults(){

    }

}
