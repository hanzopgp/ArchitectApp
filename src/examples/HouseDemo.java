package examples;

import representation.*;
import solvers.*;

import java.util.*;

public class HouseDemo {

    public static void main(String[] args){

        //----------- Utilisation package representation -----------

        //Creation de la maison de base
        System.out.println("######################## CONSTRUCTION DE LA MAISON ########################");
        int longueur = 4;
        int largeur = 3;
        Set<String> listPieceNormal = new HashSet<>(Arrays.asList("salon", "chambre1", "chambre2"));
        Set<String> listPieceEau = new HashSet<>(Arrays.asList("sdb", "cuisine", "toilette"));
        HouseRepresentation houseRepresentation = new HouseRepresentation(longueur, largeur, listPieceNormal, listPieceEau);

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

        //Affichage du resultat
        houseSolvers.printResults();

        //----------- Utilisation package planning -----------



        //----------- Utilisation package datamining -----------
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