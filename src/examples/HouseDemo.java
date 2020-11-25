package examples;

import representation.*;
;
import javax.lang.model.element.VariableElement;
import java.util.*;

public class HouseDemo {

    public HouseDemo(){

    }

    public static void main(String[] args){

        //Creation de la maison de base
        int longueur = 5;
        int largeur = 5;
        Set<String> listPieceNormal = new HashSet<>(Arrays.asList("salon", "chambre1", "chambre2"));
        Set<String> listPieceEau = new HashSet<>(Arrays.asList("sdb", "cuisine", "toilette"));
        HouseExample houseExample = new HouseExample(longueur, largeur, listPieceNormal, listPieceEau);

        //Partie representation
        houseExample.makeVariables();
        houseExample.makeBooleanVariables();
        houseExample.makeMapVariable();

        System.out.println("============= DOMAINE =============");
        System.out.println("--> " + houseExample.getDomaine());

        System.out.println("============= LISTE DES VARIABLES =============");
        List<Variable> listVariable = houseExample.getListVariable();
        for(Variable variable : listVariable){
            System.out.println(variable.toString());
        }

//        System.out.println("============= LISTE DES VARIABLES BOOLEENES =============");
//        List<BooleanVariable> listBooleanVariable = houseExample.getListBooleanVariable();
//        for(BooleanVariable booleanVariable : listBooleanVariable){
//            System.out.println(booleanVariable.toString());
//        }

//        System.out.println("============= LISTE DES VARIABLES + AFFECTATION =============");
//        Map<Variable, Object> mapVar = houseExample.getMapVariable();
//        for (Map.Entry<Variable, Object> entry : mapVar.entrySet()) {
//            System.out.println(entry.getKey() + ", Affectation : " + entry.getValue());
//        }
//
//        System.out.println("============= LISTE DES CONTRAINTES =============");
//        houseExample.addConstraint(new Rule());
//        houseExample.addConstraint(new DifferenceConstraint());
//        houseExample.addConstraint(new BinaryExtensionConstraint());
//        List<Constraint> listConstraint = houseExample.getListConstraint();
//        for(Constraint constraint : listConstraint){
//            System.out.println(constraint.toString());
//        }

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