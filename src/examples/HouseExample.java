package examples;

import representation.BooleanVariable;
import representation.Constraint;
import representation.Variable;

import java.util.*;

public class HouseExample {

    private final int longueur;
    private final int largeur;
    private Set<String> listPieceNormal;
    private Set<String> listPieceEau;
    private Set<Object> domaine;
    private List<Variable> listVariable;
    private List<BooleanVariable> listBooleanVariable;
    private List<Constraint> listConstraint;
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
        this.listBooleanVariable = new ArrayList<>();
        this.listConstraint = new ArrayList<>();
        this.mapVariable = new HashMap<>();
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
        List<BooleanVariable> listBooleanVariable = new ArrayList<>();
        String[][] listPieceString = this.buildHouseString();
        for(int i = 0; i < this.longueur; i++){
            for(int j = 0; j < this.largeur; j++){
                listBooleanVariable.add(new BooleanVariable(listPieceString[i][j]));
            }
        }
        this.listBooleanVariable = listBooleanVariable;
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

    public void printDomaine(){
        System.out.println("============= DOMAINE =============");
        for(Object o : this.domaine){
            System.out.println("* " + o.toString());
        }
    }

    public void printVariables(){
        System.out.println("============= LISTE DES VARIABLES =============");
        for(Variable variable : this.listVariable){
            System.out.println(variable.toString());
        }
    }

    public void printBooleanVariables(){
        System.out.println("============= LISTE DES VARIABLES BOOLEENES =============");
        for(BooleanVariable booleanVariable : this.listBooleanVariable){
            System.out.println(booleanVariable.toString());
        }
    }

    public void printMapVariable(){
        System.out.println("============= LISTE DES VARIABLES + AFFECTATION =============");
        for (Map.Entry<Variable, Object> entry : this.mapVariable.entrySet()) {
            System.out.println(entry.getKey() + ", Affectation : " + entry.getValue());
        }
    }

    public void printConstraints(){
        System.out.println("============= LISTE DES CONTRAINTES =============");
        for(Constraint constraint : this.listConstraint){
            System.out.println(constraint.toString());
        }
    }

    public void addConstraint(Constraint constraint){
        this.listConstraint.add(constraint);
    }

    public String[][] buildHouseString(){
        String[][] listPieceString = new String[this.longueur][this.largeur];
        for(int i = 0; i < this.longueur; i++){
            for(int j = 0; j < this.largeur; j++){
                listPieceString[i][j] = "piece(" + i + "," + j + ")";
            }
        }
        return listPieceString;
    }

    public List<Constraint> getListConstraint(){
        return this.listConstraint;
    }

    public List<Variable> getListVariable(){
        return this.listVariable;
    }

    public List<BooleanVariable> getListBooleanVariable(){
        return this.listBooleanVariable;
    }

    public Map<Variable, Object> getMapVariable(){
        return this.mapVariable;
    }

    public Set<Object> getDomaine(){
        return this.domaine;
    }

}
