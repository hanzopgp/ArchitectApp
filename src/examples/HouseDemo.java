package examples;

import representation.*;

import javax.lang.model.element.VariableElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class HouseDemo {

    private final int nbState = 4; // des variables booléennes « dalle coulée », « dalle humide », « murs élevés », « toiture terminée »

    private final int longueur;
    private final int largeur;
    private final Variable[][] listVariable;
    private final BooleanVariable[][][] listBooleanVariable;
    private final Set<Object> domaine;
    private ArrayList<Constraint> listConstraint;

    public HouseDemo(int longueur, int largeur){
        this.longueur = longueur;
        this.largeur = largeur;
        this.listVariable = new Variable[longueur][largeur];
        this.listBooleanVariable = new BooleanVariable[longueur][largeur][this.nbState];
        this.domaine = new HashSet<>(Arrays.asList("sdb", "cuisine", "salon", "chambre1", "chambre2", "toilette"));
        this.listConstraint = new ArrayList<>();
    }

    //Methodes Representation

    public String[][] buildHouseString(){
        String[][] listPieceString = new String[this.longueur][this.largeur];
        for(int i = 0; i < this.longueur; i++){
            for(int j = 0; j < this.largeur; j++){
                listPieceString[i][j] = "piece(" + i + "," + j + ")";
            }
        }
        return listPieceString;
    }

    public void buildHouseVariable(){
        String[][] listPieceString = this.buildHouseString();
        for(int i = 0; i < this.longueur; i++){
            for(int j = 0; j < this.largeur; j++){
                this.listVariable[i][j] = new Variable(listPieceString[i][j], this.domaine);
            }
        }
    }

    public void buildHouseBooleanVariable(){
        for(int i = 0; i < this.longueur; i++){
            for(int j = 0; j < this.largeur; j++){
                for(int k = 0; k < 4; k++){
                    this.listBooleanVariable[i][j][k] = new BooleanVariable(this.choseState(k));
                }
            }
        }
    }

    public String choseState(int n){
        return switch (n) {
            case 1 -> "dalle coulee";
            case 2 -> "dalle humide";
            case 3 -> "murs eleves";
            case 4 -> "toiture terminee";
            default -> "vide";
        };
    }

    public void addConstraints(){
        for(int i = 0; i < this.longueur; i++) {
            for (int j = 0; j < this.largeur; j++) {
                for(int k = 0; k < this.nbState; k++){
                    BooleanVariable boolVar1 = this.listBooleanVariable[i][j][k];
                    BooleanVariable boolVar2 = this.listBooleanVariable[i][j][k]; //next?
                    this.listConstraint.add(new Rule(boolVar1, true, boolVar2, false)); //Classe Rule
                }
                Variable var1 = this.listVariable[i][j];
                Variable var2 = this.listVariable[i][j]; //next?
                this.listConstraint.add(new DifferenceConstraint(var1, var2)); //Classe DifferenceConstraint
                this.listConstraint.add(new BinaryExtensionConstraint(var1, var2)); //Classe BinaryExtensionConstraint
            }
        }
    }

    public ArrayList<Variable> getNeighbors(Variable var){
        ArrayList<Variable> listNeighbors = new ArrayList<>();
        for(int i = 0; i < this.longueur; i++) {
            for (int j = 0; j < this.largeur; j++) {
                if(this.listVariable[i][j] == var){
                    Variable leftNeighbor = null, topNeighbor = null, rightNeighbor = null, botNeighbor = null;
                    if(i > 0){
                        leftNeighbor = this.listVariable[i-1][j];
                    }
                    if(j > 0){
                        topNeighbor = this.listVariable[i][j-1];
                    }
                    if(i < this.longueur - 1){
                        rightNeighbor = this.listVariable[i+1][j];
                    }
                    if(i < this.largeur - 1){
                        botNeighbor = this.listVariable[i][j+1];
                    }
                    listNeighbors.add(leftNeighbor);
                    listNeighbors.add(topNeighbor);
                    listNeighbors.add(rightNeighbor);
                    listNeighbors.add(botNeighbor);
                }
            }
        }
        return listNeighbors;
    }

    public boolean checkIfSatisfied(){
        for (Constraint constraint : this.listConstraint) {
//            if (!(constraint.isSatisfiedBy())) {
//                return false;
//            }
        }
        return true;
    }

    //Methodes Solvers



    //Methode Principale

    public void example(){

        //Partie representation
        this.buildHouseVariable(); //Classe Variable
        this.buildHouseBooleanVariable(); //Classe BooleanVariable
        this.addConstraints(); //Classe Contraint
        boolean isSatisfied = this.checkIfSatisfied();
        System.out.println(isSatisfied);

        //Partie Solver

    }

}