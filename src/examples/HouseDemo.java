package examples;

import representation.BooleanVariable;
import representation.Variable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class HouseDemo {

    private final int longueur;
    private final int largeur;
    private final Variable[][] listVariable;
    private final BooleanVariable[][] listBooleanVariable;
    private final Set<Object> domaine;

    public HouseDemo(int longueur, int largeur){
        this.longueur = longueur;
        this.largeur = largeur;
        this.listVariable = new Variable[longueur][largeur];
        this.listBooleanVariable = new BooleanVariable[longueur][largeur];
        this.domaine = new HashSet<>(Arrays.asList("sdb", "cuisine", "salon", "chambre1", "chambre2", "toilette"));
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
        String[][] listPieceString = this.buildHouseString();
        for(int i = 0; i < this.longueur; i++){
            for(int j = 0; j < this.largeur; j++){
                this.listBooleanVariable[i][j] = new BooleanVariable(listPieceString[i][j]);
            }
        }
    }

    public void addConstraints(){
        //Classe Rule

        //Classe DifferenceConstraint

        //Classe BinaryExtensionConstraint

    }

    //Methodes Solvers



    //Methode Principale

    public void example(){

        //Partie representation
        this.buildHouseVariable(); //Classe Variable
        this.buildHouseBooleanVariable(); //Classe BooleanVariable
        this.addConstraints(); //Classe Contraint

        //Partie Solver

    }

}

//chaque pièce envisagée occupe au plus une position
//deux pièces d'eau (salles de bains, cuisines, toilettes) doivent nécessairement être envisagées côte à côte (en (1,1) et (2,2) par exemple, mais pas en (1,1) et (1,3)),
//on réalise nécessairement « dalle coulée », « dalle sèche » (représenté par non(« dalle humide »)),  « murs élevés » et « toiture terminée » dans cet ordre.