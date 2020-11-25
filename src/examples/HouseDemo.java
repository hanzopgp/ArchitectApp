package examples;

import representation.*;
import solvers.*;
import planning.*;
import datamining.*;

import java.util.*;

public class HouseDemo {

    public static void main(String[] args){

        //----------- Utilisation package representation -----------

        //Creation de la maison de base
        int longueur = 3;
        int largeur = 4;
        Set<String> listPieceNormal = new HashSet<>(Arrays.asList("salon", "chambre1", "chambre2"));
        Set<String> listPieceEau = new HashSet<>(Arrays.asList("sdb", "cuisine", "toilette"));
        HouseExample houseExample = new HouseExample(longueur, largeur, listPieceNormal, listPieceEau);

        //Ajout des contraintes
//        houseExample.addConstraint(new Rule()); //Etat des pieces BooleanVariable
//        houseExample.addConstraint(new DifferenceConstraint()); //Chaque piece est unique
//        houseExample.addConstraint(new BinaryExtensionConstraint()); //Contraintes sur salles d'eau

        //Affichage etat de la maison
        houseExample.printAll();

        //----------- Utilisation package solvers -----------

        Set<Variable> setVariable = HouseDemo.listToSetVariable(houseExample.getListVariable());
        Set<Constraint> setConstraint = HouseDemo.listToSetConstraint(houseExample.getListConstraint());
        BacktrackSolver solver = new BacktrackSolver(setVariable, setConstraint);
        Map<Variable, Object> mapSolved = solver.solve();

        houseExample.printAll();

        //----------- Utilisation package planning -----------

        //----------- Utilisation package datamining -----------
    }

    //----------- Fonction utiles -----------

    public static Set<Variable> listToSetVariable(List<Variable> listVariable){
        return new HashSet<>(listVariable);
    }

    public static Set<Constraint> listToSetConstraint(List<Constraint> listConstraint){
        return new HashSet<>(listConstraint);
    }

}









//    //Methodes Representation
//
//    public void buildHouseBooleanVariable(){
//        for(int i = 0; i < this.longueur; i++){
//            for(int j = 0; j < this.largeur; j++){
//                for(int k = 0; k < 4; k++){
//                    this.listBooleanVariable[i][j][k] = new BooleanVariable(this.choseState(k));
//                }
//            }
//        }
//    }
//
//    public String choseState(int n){
//        return switch (n) {
//            case 1 -> "dalle coulee";
//            case 2 -> "dalle humide";
//            case 3 -> "murs eleves";
//            case 4 -> "toiture terminee";
//            default -> "vide";
//        };
//    }
//
//    public void addConstraints(){
//        for(int i = 0; i < this.longueur; i++) {
//            for (int j = 0; j < this.largeur; j++) {
//                for(int k = 0; k < this.nbState; k++){
//                    BooleanVariable boolVar1 = this.listBooleanVariable[i][j][k];
//                    BooleanVariable boolVar2 = this.listBooleanVariable[i][j][k]; //next?
//                    this.listConstraint.add(new Rule(boolVar1, true, boolVar2, false)); //Classe Rule
//                }
//                Variable var1 = this.listVariable[i][j];
//                Variable var2 = this.listVariable[i][j]; //next?
//                this.listConstraint.add(new DifferenceConstraint(var1, var2)); //Classe DifferenceConstraint
//                this.listConstraint.add(new BinaryExtensionConstraint(var1, var2)); //Classe BinaryExtensionConstraint
//            }
//        }
//    }
//
//    public ArrayList<Variable> getNeighbors(Variable var){
//        ArrayList<Variable> listNeighbors = new ArrayList<>();
//        for(int i = 0; i < this.longueur; i++) {
//            for (int j = 0; j < this.largeur; j++) {
//                if(this.listVariable[i][j] == var){
//                    Variable leftNeighbor = null, topNeighbor = null, rightNeighbor = null, botNeighbor = null;
//                    if(i > 0){
//                        leftNeighbor = this.listVariable[i-1][j];
//                    }
//                    if(j > 0){
//                        topNeighbor = this.listVariable[i][j-1];
//                    }
//                    if(i < this.longueur - 1){
//                        rightNeighbor = this.listVariable[i+1][j];
//                    }
//                    if(i < this.largeur - 1){
//                        botNeighbor = this.listVariable[i][j+1];
//                    }
//                    listNeighbors.add(leftNeighbor);
//                    listNeighbors.add(topNeighbor);
//                    listNeighbors.add(rightNeighbor);
//                    listNeighbors.add(botNeighbor);
//                }
//            }
//        }
//        return listNeighbors;
//    }
//
//    public boolean checkIfSatisfied(){
//        for (Constraint constraint : this.listConstraint) {
////            if (!(constraint.isSatisfiedBy())) {
////                return false;
////            }
//        }
//        return true;
//    }