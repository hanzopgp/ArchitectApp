package examples;

import datamining.AssociationRule;
import datamining.BooleanDatabase;
import datamining.BruteForceAssociationRuleMiner;
import datamining.Database;
import representation.Variable;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HouseDatamining {

    private final HouseRepresentation houseRepresentation;
    private final MapSolvedGenerator mapSolvedGenerator;
    private Set<AssociationRule> setAssociationRule;

    public HouseDatamining(HouseRepresentation houseRepresentation, MapSolvedGenerator mapSolvedGenerator){
        this.houseRepresentation = houseRepresentation;
        this.mapSolvedGenerator = mapSolvedGenerator;
        this.setAssociationRule = new HashSet<>();
    }

    public void mine(){
        Database database = new Database(HouseDemo.listToSetVariable(this.houseRepresentation.getListVariable()));
        for(Map<Variable, Object> map : mapSolvedGenerator.getListSolvedMap()){
            database.add(map);
        }
        BooleanDatabase booleanDatabase = database.propositionalize();
        BruteForceAssociationRuleMiner bruteForceAssociationRuleMiner = new BruteForceAssociationRuleMiner(booleanDatabase);
        this.setAssociationRule = bruteForceAssociationRuleMiner.extract(HouseDemo.MIN_FREQUENCY, HouseDemo.MIN_CONFIDENCE);
    }

    public void printResults(){
        System.out.println();
        System.out.println("============= RESULTATS DATAMINING =============");
        System.out.println("* Frequence minimum : " + HouseDemo.MIN_FREQUENCY);
        System.out.println("* Confiance minimum : " + HouseDemo.MIN_CONFIDENCE);
        System.out.println("* Nombre de regles pour ces parametres : " + this.setAssociationRule.size());
        for (AssociationRule associationRule : this.setAssociationRule) {
            System.out.println("++++++++++++++++++++++++++++++++++++++++");
            System.out.println("--> Regle : " + associationRule.getPremise());
            System.out.println("--> Conclusion : " + associationRule.getConclusion());
            System.out.println("--> Frequence : " + associationRule.getFrequency());
            System.out.println("--> Confiance : " + associationRule.getConfidence());
        }
    }

}
