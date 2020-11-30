package examples;

import representation.*;

import java.util.*;

/**
 * Représentation d'une maison, selon l'exemple du fil rouge
 */
public class HouseRepresentation {

    private final int longueur;
    private final int largeur;
    private final List<String> listPieceNormal;
    private final List<String> listPieceEau;
    private final List<Object> domaine;
    private List<Variable> listVariable;
    private final List<Constraint> listConstraint;

    private BooleanVariable dalleCoulee;
    private BooleanVariable dalleHumide;
    private BooleanVariable mursEleves;
    private BooleanVariable toitureTerminee;

    /**
     * Constructeur
     * @param longueur - Longueur de la maison
     * @param largeur - Largeur de la maison
     * @param listPieceNormal - Liste des pièces dites "normale", donc sans rapport avec des pièces d'eau
     * @param listPieceEau - Liste des pièces dites "Pièce d'eau", donc sans rapport avec les pièces normales
     */
    public HouseRepresentation(int longueur, int largeur, List<String> listPieceNormal, List<String> listPieceEau) {
        this.longueur = longueur;
        this.largeur = largeur;

        this.listPieceNormal = listPieceNormal;
        this.listPieceEau = listPieceEau;

        this.domaine = new ArrayList<>();
        this.listVariable = new ArrayList<>();
        this.listConstraint = new ArrayList<>();

        this.makeAll();
    }

    //----------- Construction -----------

    /**
     * Méthode permettant la construction de l'ensemble des pièces, des variables associées
     * à celles-ci, ainsi que leur domaine
     */
    public void makeAll(){
        this.makeListPiece();
        this.makeDomain();
        this.makeVariables();
        this.makeBooleanVariables();
    }

    public void makeVariables(){
        List<Variable> listVariable = new ArrayList<>();
        String[][] listPieceString = this.buildHouseString();
        for(int i = 0; i < this.longueur; i++){
            for(int j = 0; j < this.largeur; j++){
                listVariable.add(new Variable(listPieceString[i][j], HouseDemo.listToSetObject(this.domaine)));
            }
        }
        this.listVariable = listVariable;
    }

    //Suivant la taille de la maison on adapte le nombre de piece d'eau et de piece seche, puis on creer le domaine
    public void makeListPiece(){
        if(this.longueur*this.largeur%2==0){
            while(this.listPieceEau.size() + 1 > ((this.longueur*this.largeur)/2)){
                this.listPieceEau.remove(this.listPieceEau.get(this.listPieceEau.size() - 1));
            }
            while(this.listPieceNormal.size() > (((this.longueur*this.largeur)/2) + 1)){
                this.listPieceNormal.remove(this.listPieceNormal.get(this.listPieceNormal.size() - 1));
            }
        }else{
            while(this.listPieceEau.size() + 1 > ((this.longueur*this.largeur)/2)){
                this.listPieceEau.remove(this.listPieceEau.get(this.listPieceEau.size() - 1));
            }
            while(this.listPieceNormal.size() - 1 > (((this.longueur*this.largeur)/2) + 1)){
                this.listPieceNormal.remove(this.listPieceNormal.get(this.listPieceNormal.size() - 1));
            }
        }
        this.domaine.addAll(listPieceNormal);
        this.domaine.addAll(listPieceEau);
    }

    public void makeDomain(){
        while(this.domaine.size() > (this.longueur*this.largeur)){
            this.domaine.remove(this.domaine.get(this.domaine.size() - 1));
        }
    }

    /**
     * Construction des variable booléennes qui concernent les étapes
     * de construction de la maison
     */
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
        this.makeOnlyOnePieceConstraint();
        this.makeStateSuiteConstraint();
        //this.makeWaterPartConstraint(); //Impossible de trouver pourquoi elle ne fonctionne pas...
    }

    /**
     * Méthode qui permet de définir les règles de base du problème. Par exemple,
     * une dalle peut être humide uniquement si elle à déjà été coulée.
     *
     * Contrainte dalle coulee -> dalle humide -> murs eleves -> toiture terminee
     */
    public void makeStateSuiteConstraint(){
        Constraint c1 = new Rule(dalleCoulee, false, dalleHumide, false);
        Constraint c2 = new Rule(dalleHumide, true, dalleCoulee, true);
        Constraint c3 = new Rule(dalleHumide, true, mursEleves, false);
        Constraint c4 = new Rule(mursEleves, false, toitureTerminee, false);
        Constraint c5 = new Rule(mursEleves, true, dalleCoulee, true);
        Constraint c6 = new Rule(mursEleves, true, dalleHumide, false);
        Constraint c7 = new Rule(toitureTerminee, true, mursEleves, true);
        this.addConstraint(c1,c2,c3,c4,c5,c6,c7);
    }

    /**
     * Méthode permettant de définir une contrainte qui implique qu'il n'y a
     * qu'une seule pièce par case.
     */
    public void makeOnlyOnePieceConstraint(){
        for(int i = 0; i < this.longueur * this.largeur; i++){
            for(int j = i + 1; j < this.largeur * this.longueur; j++){
                if(i != j){
                    Variable v1 = this.listVariable.get(i);
                    Variable v2 = this.listVariable.get(j);
                    this.addConstraint(new DifferenceConstraint(v1, v2));
                }

            }
        }
    }


    /**
     * Méthode permettant de définir une contrainte qui implique que toutes
     * les pièces d'eau seront cote à cote.
     */
    public void makeWaterPartConstraint(){
        List<Variable> tmpListVariable = new ArrayList<>(this.listVariable);
        tmpListVariable.removeIf(tmpV -> tmpV instanceof BooleanVariable);
        for(Variable v1 : tmpListVariable){ //Pour chaque piece de la maison
            for(Variable v2 : getNotNeighbors(v1)){ //Pour chacun des non-voisins de v1
                BinaryExtensionConstraint constraint = new BinaryExtensionConstraint(v1, v2); //On cree un lien entre les deux
                for(String room1 : HouseDemo.objectSetToStringSet(v1.getDomain())){
                    for(String room2 : HouseDemo.objectSetToStringSet(v2.getDomain())){
                        if (!this.listPieceEau.contains(room1) || !this.listPieceEau.contains(room2)) { //Si v1 ou v2 n'est pas une piece d'eau
                            constraint.addTuple(room1, room2); //Alors tous les couples sont autorises
                        }
                    }
                }
                this.addConstraint(constraint);
            }
        }
    }

    //Contrainte pieces d'eau cote a cote
//    public void makeWaterPartConstraint(){
//        List<Variable> tmpListVariable = new ArrayList<>(this.listVariable);
//        tmpListVariable.removeIf(tmpV -> tmpV instanceof BooleanVariable);
//        for(Variable v1 : tmpListVariable){ //Pour chaque piece de la maison
//            List<Variable> notNeighbors = this.getNotNeighbors(v1); //On recupere la liste des voisins de v1, la piece
//            for(Variable v2 : notNeighbors){
//                BinaryExtensionConstraint constraint = new BinaryExtensionConstraint(v1, v2); //On cree une contrainte liant la case courante et chacune des cases non-voisines
//                Set<String> domainV1 = HouseDemo.objectSetToStringSet(v1.getDomain()); //On recupere le domaine de la variable courante
//                for(String elementDomainV1 : domainV1){
//                    if(this.listPieceEau.contains(elementDomainV1)){ //On regarde chaque element du domaine de V1, si l'element et une piece d'eau alors
//                        for(String pieceNormal : this.listPieceNormal){
//                            constraint.addTuple(elementDomainV1, pieceNormal); //On ajoute aux couples autorisés de la contrainte SEULEMENT les pieces normales
//                        }
//                    }
//                    else if(this.listPieceNormal.contains(elementDomainV1)){ //Mais si c'est une piece normale
//                        for(String pieceGeneral : HouseDemo.objectSetToStringSet(HouseDemo.listToSetObject(this.domaine))){
//                            constraint.addTuple(elementDomainV1, pieceGeneral); //Alors on autorise tout type de piece
//
//                        }
//                    }
//                    //Pas besoin de reflechir aux pieces voisines car les pieces voisines de v1 sont les pieces non-voisines d'autres pieces
//                }
//                this.addConstraint(constraint);
//            }
//        }
//    }


    //----------- Affichage -----------

    /**
     * Printer global
     */
    public void printAll(){
        this.printArea();
        this.printDomaine();
        this.printVariables();
        this.printBooleanVariables();
        this.printConstraints();
    }

    /**
     * Printer de la maison
     */
    public void printArea(){
        System.out.println();
        System.out.println("============= MAISON =============");
        for(int i = 0; i < this.longueur; i++){
            for(int j = 0; j < this.largeur; j++){
                System.out.print("  x  ");
            }
            System.out.println();
        }
    }

    /**
     * Printer des domaines de l'instance
     */
    public void printDomaine(){
        System.out.println();
        System.out.println("============= DOMAINE =============");
        for(Object o : this.domaine){
            System.out.println("* " + o.toString());
        }
    }

    /**
     * Printer de l'ensemble des variables de l'instance
     */
    public void printVariables(){
        System.out.println();
        System.out.println("============= LISTE DES VARIABLES =============");
        for(Variable variable : this.listVariable){
            System.out.println(variable.toString());
        }
    }

    /**
     * Printer de l'ensemble des variables booléennes de l'instance
     */
    public void printBooleanVariables(){
        System.out.println();
        System.out.println("============= LISTE DES VARIABLES BOOLEENES =============");
        System.out.println("* Dalle coulee : " + this.dalleCoulee.toString());
        System.out.println("* Dalle humide : " + this.dalleHumide.toString());
        System.out.println("* Murs eleves : " + this.mursEleves.toString());
        System.out.println("* Toiture terminee : " + this.toitureTerminee.toString());
    }

    /**
     * Printer de l'ensemble des contraintes de l'instance
     */
    public void printConstraints(){
        System.out.println();
        System.out.println("============= LISTE DES CONTRAINTES =============");
        for(Constraint constraint : this.listConstraint){
            System.out.println("Type : " + constraint + ", Sur les variables : " + constraint.getScope());
        }
        System.out.println("--> Nombre de contraintes sur cette maison : " + this.listConstraint.size());
    }

    //----------- Fonction utiles -----------

    /**
     * Méthode permettant d'ajouter une contrainte au problème actuel.
     * @param constraint - Contrainte à ajouter
     */
    public void addConstraint(Constraint constraint){
        this.listConstraint.add(constraint);
    }

    /**
     * Méthode permettant d'ajouter une liste de contrainte à la liste
     * de contrainte existante du problème.
     * @param listConstraint
     */
    public void addConstraint(Constraint ... listConstraint){ this.listConstraint.addAll(Arrays.asList(listConstraint)); }


    /**
     * Méthode permettant de retourner un tableau 2D de toutes les pièces de la maison,
     * avec leur nom qui précise leur position (ex : piece1,2)
     * @return
     */
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

    /**
     * Méthode permettant de retourner un tableau 2D de toutes les variables passées
     * en paramètre.
     * @param listVariable - Liste de variables à transformer
     * @return Tableau 2D de Variable
     */
    public Variable[][] listTo2DArray(List<Variable> listVariable){
        Variable[][] array = new Variable[this.longueur][this.largeur];
        for(int i = 0; i < this.longueur; i++){
            for(int j = 0; j < this.largeur; j++){
                array[i][j] = listVariable.get((i * this.largeur) + j);
            }
        }
        return array;
    }

    //Renvoie la liste des voisins d'une case donnee

    /**
     * Méthode permettant de retourner tout les voisins autour d'une pièce.
     * @param var - Variable représentant une pièce
     * @return Liste de pièces voisines
     */
    public ArrayList<Variable> getNeighbors(Variable var){
        ArrayList<Variable> neighbors = new ArrayList<>();
        List<Variable> tmpListVariable = new ArrayList<>(this.listVariable);
        tmpListVariable.removeIf(tmpV -> tmpV instanceof BooleanVariable);
        Variable[][] arrayVariable = this.listTo2DArray(tmpListVariable);
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

    /**
     * Méthode permettant de retourner toutes les pièces n'étant pas voisine à une
     * pièce de référence passée en paramètre
     * @param var - Variable représentant une pièce de référence
     * @return Liste de non-voisins
     */
    public List<Variable> getNotNeighbors(Variable var){
        ArrayList<Variable> neighbors = this.getNeighbors(var);
        List<Variable> tmpListVariable = new ArrayList<>(this.listVariable);
        tmpListVariable.removeIf(tmpV -> tmpV instanceof BooleanVariable);
        List<Variable> notNeighbors = new ArrayList<>(tmpListVariable);
        for(Variable v : neighbors){
            if(tmpListVariable.contains(v)){
                notNeighbors.remove(v);
            }
        }
        notNeighbors.remove(var);
        return notNeighbors;
    }

    //----------- Getter et Setter -----------

    /**
     * Getter de listContraint
     * @return Liste des contraintes du problème
     */
    public List<Constraint> getListConstraint(){
        return this.listConstraint;
    }

    /**
     * Getter de listVariable
     * @return Liste des variables du problème
     */
    public List<Variable> getListVariable(){
        return this.listVariable;
    }

    /**
     * Getter de dalleCoulee
     * @return dalleCoulee
     */
    public BooleanVariable getDalleCoulee() { return dalleCoulee; }

    /**
     * Getter de dalleHumide
     * @return dalleHumide
     */
    public BooleanVariable getDalleHumide() { return dalleHumide; }

    /**
     * Getter de mursEleves
     * @return mursEleves
     */
    public BooleanVariable getMursEleves() { return mursEleves; }

    /**
     * Getter de toitureTerminee
     * @return toitureTerminee
     */
    public BooleanVariable getToitureTerminee() { return toitureTerminee; }

    /**
     * Getter des domaine
     * @return Ensemble de domaines du problème
     */
    public Set<Object> getDomaine() { return HouseDemo.listToSetObject(domaine); }

}
