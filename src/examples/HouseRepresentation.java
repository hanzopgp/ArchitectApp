package examples;

import representation.*;

import java.util.*;

public class HouseRepresentation {

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

    private Map<Variable, Object> mapVariable;

    public HouseRepresentation(int longueur, int largeur, Set<String> listPieceNormal, Set<String> listPieceEau) {
        this.longueur = longueur;
        this.largeur = largeur;
        this.listPieceNormal = listPieceNormal;
        this.listPieceEau = listPieceEau;

        this.domaine = new HashSet<>();
        this.domaine.addAll(listPieceNormal);
        this.domaine.addAll(listPieceEau);

        this.listVariable = new ArrayList<>();
        this.listConstraint = new ArrayList<>();

        this.makeAll();
    }

    //----------- Construction -----------

    public void makeAll(){
        this.makeVariables();
        this.makeBooleanVariables();
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

        this.listVariable.add(this.dalleCoulee);
        this.listVariable.add(this.dalleHumide);
        this.listVariable.add(this.mursEleves);
        this.listVariable.add(this.toitureTerminee);
    }

    //----------- Creation des contraintes -----------

    //Ajout de toute les contraintes de l'exemple
    public void makeAllConstraint(){
        //this.makeStateSuiteConstraint();
        this.makeOnlyOnePieceConstraint();
        //this.makeEveryPieceUsedConstraint();
        //this.makeWaterPartConstraint();
        //this.makeOnlyOneLivingRoomConstraint();
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
        for(int i = 0; i < HouseDemo.WIDTH; i++){
            for(int j = 0; j < HouseDemo.HEIGHT; j++){
                if(i != j){
                    System.out.println(i+","+j);
                    Variable v1 = this.listVariable.get(i);
                    Variable v2 = this.listVariable.get(j);
                    this.addConstraint(new DifferenceConstraint(v1, v2));
                    this.addConstraint(new DifferenceConstraint(v2, v1));
                }
            }
        }
    }

    //Contrainte toutes les cases occupes
    public void makeEveryPieceUsedConstraint(){
    }

    //Constrainte un seul salon, une seule cuisine
    public void makeOnlyOneLivingRoomConstraint(){
        for(int i = 0; i < HouseDemo.WIDTH; i++){
            for(int j = i + 1; j < HouseDemo.WIDTH; j++){
                Variable v1 = this.listVariable.get(i);
                Variable v2 = this.listVariable.get(j);
                Set<String> domainV1 = HouseDemo.objectSetToStringSet(v1.getDomain());
                Set<String> domainV2 = HouseDemo.objectSetToStringSet(v2.getDomain());
                if (domainV1.contains("salon") && domainV2.contains("salon")) {
                    this.addConstraint(new DifferenceConstraint(v1, v2));
                }
                if (domainV1.contains("cuisine") && domainV2.contains("cuisine")) {
                    this.addConstraint(new DifferenceConstraint(v1, v2));
                }
            }
        }
    }

    //Contrainte pieces d'eau cote a cote
    public void makeWaterPartConstraint(){
        for(Variable v1 : this.listVariable){ //Pour chaque piece de la maison
            List<Variable> notNeighbors = this.getNotNeighbors(v1); //On recupere la liste des voisins de v1, la piece courante
            for(Variable v2 : notNeighbors){
                if(!(v1 instanceof BooleanVariable) && !(v2 instanceof BooleanVariable)){
                    BinaryExtensionConstraint constraint = new BinaryExtensionConstraint(v1, v2); //On cree une contrainte liant la case courante et chacune des cases non-voisines
                    Set<String> domainV1 = HouseDemo.objectSetToStringSet(v1.getDomain()); //On recupere le domaine de la variable courante
                    for(String elementDomainV1 : domainV1){
                        if(this.listPieceEau.contains(elementDomainV1)){ //On regarde chaque element du domaine de V1, si l'element et une piece d'eau alors
                            for(String pieceNormal : this.listPieceNormal){
                                constraint.addTuple(elementDomainV1, pieceNormal); //On ajoute aux couples autorisés de la contrainte SEULEMENT les pieces normales
                            }
                        }
                        else if(this.listPieceNormal.contains(elementDomainV1)){ //Mais si c'est une piece normale
                            for(String pieceGeneral : HouseDemo.objectSetToStringSet(this.domaine)){
                                constraint.addTuple(elementDomainV1, pieceGeneral); //Alors on autorise tout type de piece
                            }
                        }
                        //Pas besoin de reflechir aux pieces voisines car les pieces voisines de v1 sont les pieces non-voisines d'autres pieces
                    }
                    this.addConstraint(constraint);
                }
            }
        }
    }

    //----------- Affichage -----------

    public void printAll(){
        this.printDomaine();
        this.printVariables();
        this.printBooleanVariables();
        this.printMapVariable();
        this.printConstraints();
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
            System.out.println("ID : " + constraint + ", Scope : " + constraint.getScope());
        }
        System.out.println("--> Nombre de contraintes sur cette maison : " + this.listConstraint.size());
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

    public List<Variable> getNotNeighbors(Variable var){
        ArrayList<Variable> neighbors = this.getNeighbors(var);
        List<Variable> notNeighbors = new ArrayList<>(this.listVariable);
        for(Variable v : neighbors){
            if(this.listVariable.contains(v)){
                notNeighbors.remove(v);
            }
        }
        return notNeighbors;
    }

    //----------- Getter et Setter -----------

    public List<Constraint> getListConstraint(){
        return this.listConstraint;
    }

    public List<Variable> getListVariable(){
        return this.listVariable;
    }

    public BooleanVariable getDalleCoulee() {
        return dalleCoulee;
    }

    public BooleanVariable getDalleHumide() {
        return dalleHumide;
    }

    public BooleanVariable getMursEleves() {
        return mursEleves;
    }

    public BooleanVariable getToitureTerminee() {
        return toitureTerminee;
    }

    public Set<Object> getDomaine() {
        return domaine;
    }
}