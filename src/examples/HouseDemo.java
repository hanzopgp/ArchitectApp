package examples;

import representation.*;

import java.util.*;

public class HouseDemo {

    public static int WIDTH = 4; //maximum WIDTH*HEIGHT : 12
    public static int HEIGHT = 3;
    public static List<String> LIST_PIECE_NORMAL = new ArrayList<>(Arrays.asList("salon", "chambre1", "chambre2", "salledejeu", "chambre3", "chambre4", "chambre5"));
    public static List<String> LIST_PIECE_EAU = new ArrayList<>(Arrays.asList("sdb", "cuisine", "toilette", "toilette2",  "sdb2", "sdb3"));
    public static List<String> LIST_CHAMBRE = new ArrayList<>(Arrays.asList("chambre1", "chambre2", "chambre3", "chambre4",  "chambre5"));
    public static int PLANNING_COST = 5;
    public static String SOLVERTYPE = "backtrack"; //"backtrack", "mac", "macheuristic"
    public static int NB_HOUSE_DATAMINING = 10;

    public static void main(String[] args){

        //----------- Utilisation package representation -----------

        //Creation de la maison de base
        System.out.println("######################## CONSTRUCTION DE LA MAISON ########################");

        HouseRepresentation houseRepresentation = new HouseRepresentation(WIDTH, HEIGHT, LIST_PIECE_NORMAL, LIST_PIECE_EAU, LIST_CHAMBRE);

        //Ajout des contraintes
        houseRepresentation.makeAllConstraint();

        //Affichage de la maison de base
        //houseRepresentation.printAll();

        //----------- Utilisation package solvers -----------

        System.out.println("######################## RESOLUTION ########################");

        Set<Variable> setVariable = HouseDemo.listToSetVariable(houseRepresentation.getListVariable());
        Set<Constraint> setConstraint = HouseDemo.listToSetConstraint(houseRepresentation.getListConstraint());
        HouseSolvers houseSolvers = new HouseSolvers(setVariable, setConstraint);

        //Stop le programme si il y a trop de piece pour pas assez d'element de domaine
        if((WIDTH * HEIGHT) > (LIST_PIECE_EAU.size() + LIST_PIECE_NORMAL.size())){
            System.out.println("Pas assez de pieces disponible pour votre maison");
            System.exit(1);
        }

        //Choix du solver utilise
        switch (SOLVERTYPE) {
            case "backtrack" -> houseSolvers.solveWithBacktrack();
            case "mac" -> houseSolvers.solveWithMac();
            case "macheuristic" -> houseSolvers.solveWithMacAndHeuristic();
        }

        //Stoper le programme si pas de resolution possible
        if(houseSolvers.getMapSolved() == null){
            System.out.println("Pas de solution trouvee ! ");
            System.exit(1);
        }

        //Affichage du resultat
        //houseSolvers.printResults();

        //----------- Utilisation package planning -----------

        System.out.println("######################## PLANNING ########################");

        //Planning avec algorithm A*
        HousePlanning housePlanning = new HousePlanning(houseRepresentation, houseSolvers.getMapSolved());
        housePlanning.planAStar();

        //Affichage du resultat
        //housePlanning.printResults();

        //----------- Utilisation package datamining -----------

        System.out.println("######################## DATAMINING ########################");

        //Creation de la base de donnee
        MapSolvedGenerator mapSolvedGenerator = new MapSolvedGenerator(setVariable, setConstraint);
        System.out.println(mapSolvedGenerator.getListSolvedMap().size());
        System.out.println(mapSolvedGenerator.getListSolvedMap().get(0).equals(mapSolvedGenerator.getListSolvedMap().get(1)));

        //Recuperation des informations
        HouseDatamining houseDatamining = new HouseDatamining(houseRepresentation);
        houseDatamining.mine();

        //Affichage du resultat
        houseDatamining.printResults();

    }

    //----------- Fonction utiles -----------

    public static Set<Variable> listToSetVariable(List<Variable> listVariable){
        return new HashSet<>(listVariable);
    }

    public static Set<Constraint> listToSetConstraint(List<Constraint> listConstraint){ return new HashSet<>(listConstraint); }

    public static Set<Object> listToSetObject(List<Object> listObject){ return new HashSet<>(listObject); }

    public static Set<String> objectSetToStringSet(Set<Object> setObject){
        Set<String> setString = new HashSet<>();
        for(Object o : setObject){
            if(o instanceof String){
                setString.add((String) o);
            }
        }
        return setString;
    }

    public static boolean mapIsInListMap(Map<Variable, Object> map1, List<Map<Variable, Object>> listMap){
        for(Map<Variable, Object> map2 : listMap){
            if(map1.equals(map2)){
                return true;
            }
        }
        return false;
    }

}