package examples;

import representation.*;

import javax.lang.model.element.VariableElement;
import java.util.*;

public class HouseExample {

    private final int longueur;
    private final int largeur;
    private Set<String> listPieceNormal;
    private Set<String> listPieceEau;
    private Set<Object> domaine;
    private List<Variable> listVariable;
    private List<Constraint> listConstraint;

    private BooleanVariable dalleCoulee;
    private BooleanVariable dalleHumide;
    private BooleanVariable mursEleves;
    private BooleanVariable toitureTerminee;

    private BooleanVariable currentState;
    private Map<Variable, Object> mapVariable;

    public HouseExample(int longueur, int largeur, Set<String> listPieceNormal, Set<String> listPieceEau) {
        this.longueur = longueur;
        this.largeur = largeur;
        this.listPieceNormal = listPieceNormal;
        this.listPieceEau = listPieceEau;

        this.domaine = new HashSet<>();
        this.domaine.addAll(listPieceNormal);
        this.domaine.addAll(listPieceEau);

        this.listVariable = new ArrayList<>();
        this.listConstraint = new ArrayList<>();
        this.mapVariable = new HashMap<>();

        this.makeAll();
    }

    //----------- Construction -----------

    public void makeAll(){
        this.makeVariables();
        this.makeBooleanVariables();
        this.makeMapVariable();
        this.currentState = this.dalleCoulee;
    }

    public void makeVariables(){
        List<Variable> listVariable = new ArrayList<>();
        String[][] listPieceString = this.buildHouseString();
        for(int i = 0; i < this.longueur; i++){
            for(int j = 0; j < this.largeur; j++){
                listVariable.add(new Variable(listPieceString[i][j], this.domaine));
            }
        }
        this.listVariable = listVariable;
    }

    public void makeBooleanVariables(){
        this.dalleCoulee = new BooleanVariable("dalleCoulee");
        this.dalleHumide = new BooleanVariable("dalleHumide");
        this.mursEleves = new BooleanVariable("mursEleves");
        this.toitureTerminee = new BooleanVariable("toitureTerminee");
    }

    public void makeMapVariable(){
        Map<Variable, Object> mapVariable = new HashMap<>();
        for(Variable var : this.listVariable){
            for(Object o : domaine){
                mapVariable.put(var, o);
            }
        }
        this.mapVariable = mapVariable;
    }

    //----------- Creation des contraintes -----------

    //Ajout de toute les contraintes de l'exemple
    public void makeAllConstraint(){
        this.makeStateSuiteConstraint();
        this.makeOnlyOnePieceConstraint();
        this.makeEveryPieceUsedConstraint();
        this.makeWaterPartConstraint();
    }

    //Contrainte dalle coulee -> dalle humide -> murs eleves -> toiture terminee
    public void makeStateSuiteConstraint(){
        this.addConstraint(new Rule(this.toitureTerminee, true, this.mursEleves, true));
        this.addConstraint(new Rule(this.mursEleves, true, this.dalleCoulee, true));
        this.addConstraint(new Rule(this.mursEleves, true, this.dalleHumide, false));
        this.addConstraint(new Rule(this.dalleHumide, true, this.dalleCoulee, true));
    }

    //Contrainte une seule piece par case
    public void makeOnlyOnePieceConstraint(){
        for(int i = 0; i < this.listVariable.size(); i++){
            for(int j = i + 1; j < this.listVariable.size(); j++){
                Variable v1 = this.listVariable.get(i);
                Variable v2 = this.listVariable.get(j);
                this.addConstraint(new DifferenceConstraint(v1, v2));
            }
        }
    }

    //Contrainte toutes les cases occupes
    public void makeEveryPieceUsedConstraint(){
        for (Variable v : this.listVariable) {
            this.addConstraint(new DifferenceConstraint(v, null));
        }
    }

    //Contrainte pieces d'eau cote a cote
    public void makeWaterPartConstraint(){
        for(Variable v1 : this.listVariable){
            ArrayList<Variable> neighbors = this.getNeighbors(v1);
            for(Variable v2 : neighbors){
                BinaryExtensionConstraint constraint = new BinaryExtensionConstraint(v1, v2); //Contrainte sur chaque variables et chacun de ses voisins
                Set<String> domaineV1 = HouseDemo.objectSetToStringSet(v1.getDomain());
                for(String elementDomaineV1 : domaineV1){
                    if(this.listPieceEau.contains(elementDomaineV1)){
                        for(String pieceGeneral : HouseDemo.objectSetToStringSet(this.domaine)){
                            constraint.addCoupleAllowed(elementDomaineV1, pieceGeneral);
                        }
                    }else if(this.listPieceNormal.contains(elementDomaineV1)){
                        for(String pieceNormal : this.listPieceNormal){
                            constraint.addCoupleAllowed(elementDomaineV1, pieceNormal);
                        }
                    }
                }
                this.addConstraint(constraint);
            }
        }
    }

    //----------- Affichage -----------

    public void printAll(){
        this.printCurrentState();
        this.printDomaine();
        this.printVariables();
        this.printBooleanVariables();
        this.printMapVariable();
        this.printConstraints();
    }

    public void printCurrentState(){
        System.out.println();
        System.out.println("============= ETAT ACTUEL =============");
        System.out.println(this.currentState);
    }

    public void printDomaine(){
        System.out.println();
        System.out.println("============= DOMAINE =============");
        for(Object o : this.domaine){
            System.out.println("* " + o.toString());
        }
    }

    public void printVariables(){
        System.out.println();
        System.out.println("============= LISTE DES VARIABLES =============");
        for(Variable variable : this.listVariable){
            System.out.println(variable.toString());
        }
    }

    public void printBooleanVariables(){
        System.out.println();
        System.out.println("============= LISTE DES VARIABLES BOOLEENES =============");
        System.out.println("* Dalle coulee : " + this.dalleCoulee.toString());
        System.out.println("* Dalle humide : " + this.dalleHumide.toString());
        System.out.println("* Murs eleves : " + this.mursEleves.toString());
        System.out.println("* Toiture terminee : " + this.toitureTerminee.toString());
    }

    public void printMapVariable(){
        System.out.println();
        System.out.println("============= LISTE DES VARIABLES + AFFECTATION =============");
        for (Map.Entry<Variable, Object> entry : this.mapVariable.entrySet()) {
            System.out.println(entry.getKey() + ", Affectation : " + entry.getValue());
        }
    }

    public void printConstraints(){
        System.out.println();
        System.out.println("============= LISTE DES CONTRAINTES =============");
        for(Constraint constraint : this.listConstraint){
            System.out.println(constraint);
        }
    }

    //----------- Fonction utiles -----------

    public void addConstraint(Constraint constraint){
        this.listConstraint.add(constraint);
    }

    //Creer les attributs "name" des Variables
    public String[][] buildHouseString(){
        String[][] listPieceString = new String[this.longueur][this.largeur];
        for(int i = 0; i < this.longueur; i++){
            for(int j = 0; j < this.largeur; j++){
                listPieceString[i][j] = "piece(" + i + "," + j + ")";
            }
        }
        return listPieceString;
    }

    //Transforme la liste de Variable en un tableau 2D
    public Variable[][] listTo2DArray(List<Variable> listVariable){
        Variable[][] array = new Variable[this.longueur][this.largeur];
        for(int i = 0; i < this.longueur; i++){
            for(int j = 0; j < this.largeur; j++){
                array[i][j] = listVariable.get((j * this.longueur) + i);
            }
        }
        return array;
    }

    //Renvoie la liste des voisins d'une case donnee
    public ArrayList<Variable> getNeighbors(Variable var){
        ArrayList<Variable> neighbors = new ArrayList<>();
        Variable[][] arrayVariable = this.listTo2DArray(this.listVariable);
        for(int i = 0; i < this.longueur; i++) {
            for (int j = 0; j < this.largeur; j++) {
                if(arrayVariable[i][j] == var){
                    if(i > 0 && j > 0){
                        neighbors.add(arrayVariable[i-1][j-1]);
                    }
                    if(i > 0){
                        neighbors.add(arrayVariable[i-1][j]);
                    }
                    if(i > 0 && j < this.largeur - 1){
                        neighbors.add(arrayVariable[i-1][j+1]);
                    }
                    if(j > 0){
                        neighbors.add(arrayVariable[i][j-1]);
                    }
                    if(j < this.largeur - 1){
                        neighbors.add(arrayVariable[i][j+1]);
                    }
                    if(i < this.longueur - 1 && j > 0){
                        neighbors.add(arrayVariable[i+1][j-1]);
                    }
                    if(i < this.longueur - 1){
                        neighbors.add(arrayVariable[i+1][j]);
                    }
                    if(i < this.longueur - 1 && j < this.largeur - 1){
                        neighbors.add(arrayVariable[i+1][j+1]);
                    }
                }
            }
        }
        return neighbors;
    }

    //----------- Getter et Setter -----------

    public List<Constraint> getListConstraint(){
        return this.listConstraint;
    }

    public List<Variable> getListVariable(){
        return this.listVariable;
    }

}
