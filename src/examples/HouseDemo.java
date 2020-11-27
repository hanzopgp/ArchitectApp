package examples;

import representation.*;
import solvers.*;

import java.util.*;

public class HouseDemo {

    public static int WIDTH = 4;
    public static int HEIGHT = 3;
    public static Set<String> LIST_PIECE_NORMAL = new HashSet<>(Arrays.asList("salon", "chambre1", "chambre2"));
    public static Set<String> LIST_PIECE_EAU = new HashSet<>(Arrays.asList("sdb", "cuisine", "toilette"));
    public static int PLANNING_COST = 5;


    public static void main(String[] args){

        //----------- Utilisation package representation -----------

        //Creation de la maison de base
        System.out.println("######################## CONSTRUCTION DE LA MAISON ########################");

        HouseRepresentation houseRepresentation = new HouseRepresentation(WIDTH, HEIGHT, LIST_PIECE_NORMAL, LIST_PIECE_EAU);

        //Ajout des contraintes
        houseRepresentation.makeAllConstraint();

        //Affichage etat de la maison
        houseRepresentation.printAll();

        //----------- Utilisation package solvers -----------

        System.out.println("######################## RESOLUTION ########################");

        Set<Variable> setVariable = HouseDemo.listToSetVariable(houseRepresentation.getListVariable());
        Set<Constraint> setConstraint = HouseDemo.listToSetConstraint(houseRepresentation.getListConstraint());
        HouseSolvers houseSolvers = new HouseSolvers(setVariable, setConstraint);

        //Backtrack solver
        houseSolvers.solveWithBacktrack();
        //Mac solver avec heuristique
        //houseSolvers.solveWithMacAndHeuristic();
        //Mac solver sans heuristique
        //houseSolvers.solveWithMac();

        if(houseSolvers.getMapSolved() == null){
            System.out.println("Erreur lors de la resolution ! ");
            System.exit(1);
        }

        //Affichage du resultat
        houseSolvers.printResults();

        //----------- Utilisation package planning -----------

        System.out.println("######################## PLANNING ########################");

        //Planning avec algorithm A*
        HousePlanning housePlanning = new HousePlanning(houseRepresentation, houseSolvers.getMapSolved());
        housePlanning.planAStar();

        //Affichage du resultat
        housePlanning.printResults();

        //----------- Utilisation package datamining -----------

        System.out.println("######################## DATAMINING ########################");

    }

    //----------- Fonction utiles -----------

    public static Set<Variable> listToSetVariable(List<Variable> listVariable){
        return new HashSet<>(listVariable);
    }

    public static Set<String> objectSetToStringSet(Set<Object> setObject){
        Set<String> setString = new HashSet<>();
        for(Object o : setObject){
            if(o instanceof String){
                setString.add((String) o);
            }
        }
        return setString;
    }

    public static Set<Constraint> listToSetConstraint(List<Constraint> listConstraint){ return new HashSet<>(listConstraint); }

}