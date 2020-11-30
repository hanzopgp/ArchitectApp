package examples;

import representation.*;

import java.util.*;

public class HouseDemo {

    public static int WIDTH = 2; //maximum WIDTH*HEIGHT : 16
    public static int HEIGHT = 2;
    public static List<String> LIST_PIECE_NORMAL = new ArrayList<>(Arrays.asList("salon", "chambre1", "salledejeu", "chambre2", "garage", "chambre3", "chambre4", "cinema", "chambre5"));
    public static List<String> LIST_PIECE_EAU = new ArrayList<>(Arrays.asList("sdb+toilette", "cuisine", "toilette", "toilette2",  "sdb2", "sdb3", "toilette3"));
    public static int PLANNING_COST = 5;
    public static String SOLVERTYPE = "backtrack"; //"backtrack", "mac", "macheuristic"
    public static String PLANNERTYPE = "astar"; //"astar", "dijkstra", "bfs", "dfs"
    public static int NB_HOUSE_DATAMINING = 5000;
    public static float MIN_FREQUENCY = 0.7f;
    public static float MIN_CONFIDENCE = 0.7f;
    public static boolean FULL_DISPLAY = false;

    public static void main(String[] args){

        //Choix des parametres
        //inputParameters();

        //----------- Utilisation package representation -----------

        //Creation de la maison de base
        System.out.println();
        System.out.println("######################## CONSTRUCTION DE LA MAISON ########################");

        HouseRepresentation houseRepresentation = new HouseRepresentation(HEIGHT, WIDTH, LIST_PIECE_NORMAL, LIST_PIECE_EAU);

        //Ajout des contraintes
        houseRepresentation.makeAllConstraint();

        //Affichage de la maison de base
        houseRepresentation.printAll();

        //----------- Utilisation package solvers -----------

        System.out.println();
        System.out.println("######################## RESOLUTION ########################");

        Set<Variable> setVariable = HouseDemo.listToSetVariable(houseRepresentation.getListVariable());
        Set<Constraint> setConstraint = HouseDemo.listToSetConstraint(houseRepresentation.getListConstraint());
        HouseSolvers houseSolvers = new HouseSolvers(setVariable, setConstraint);

        //Stop le programme si il y a trop de piece pour pas assez d'element de domaine
        if((WIDTH * HEIGHT) > (LIST_PIECE_EAU.size() + LIST_PIECE_NORMAL.size())){
            System.out.println("Pas assez de pieces disponible pour votre maison");
            System.exit(1);
        }

        //Choix du solver utilise
        switch(SOLVERTYPE){
            case "backtrack" :
                houseSolvers.solveWithBacktrack();
                break;
            case "mac" :
                houseSolvers.solveWithMac();
                break;
            case "macheuristic" :
                houseSolvers.solveWithMacAndHeuristic();
                break;
        }

        //Stoper le programme si pas de resolution possible
        if(houseSolvers.getMapSolved() == null){
            System.out.println("Pas de solution trouvee ! ");
            System.exit(1);
        }

        //Affichage du resultat
        houseSolvers.printResults();

        //----------- Utilisation package planning -----------

        System.out.println();
        System.out.println("######################## PLANNING ########################");

        //Planning avec algorithm A*
        HousePlanning housePlanning = new HousePlanning(houseRepresentation, houseSolvers.getMapSolved());
        housePlanning.planAStar();

        //Affichage du resultat
        housePlanning.printResults();

        //----------- Utilisation package datamining -----------

        System.out.println();
        System.out.println("######################## DATAMINING ########################");

        //Creation et affichage de la base de donnee
        MapSolvedGenerator mapSolvedGenerator = new MapSolvedGenerator(setVariable, setConstraint);
        mapSolvedGenerator.printResults();

        //Recuperation des informations
        HouseDatamining houseDatamining = new HouseDatamining(houseRepresentation, mapSolvedGenerator);
        houseDatamining.mine();

        //Affichage du resultat
        houseDatamining.printResults();

    }

    //----------- Fonction utiles -----------

    public static Set<Variable> listToSetVariable(List<Variable> listVariable){
        return new HashSet<>(listVariable);
    }

    public static Set<Constraint> listToSetConstraint(List<Constraint> listConstraint){ return new HashSet<>(listConstraint); }

    public static Set<Object> listToSetObject(List<Object> listObject){ return new HashSet<>(listObject); }

    public static Set<String> objectSetToStringSet(Set<Object> setObject){
        Set<String> setString = new HashSet<>();
        for(Object o : setObject){
            if(o instanceof String){
                setString.add((String) o);
            }
        }
        return setString;
    }

    //Fixe les variables globales
    public static void inputParameters(){
        System.out.flush();
        System.out.println("=============== CHOIX DES PARAMETRES ===============");
        System.out.println("Entrez la longueur de la maison : (compris entre 1 et 4)");
        WIDTH = scannerIntLimit(new Scanner(System.in), 1, 4);
        System.out.println("Entrez la largeur de la maison : (compris entre 1 et 4)");
        HEIGHT = scannerIntLimit(new Scanner(System.in), 1, 4);
        System.out.println("Entrez le numero du solver a utiliser : ");
        System.out.println("1 - Backtrack");
        System.out.println("2 - MAC");
        System.out.println("3 - MacHeuristic");
        int intSolverType = scannerIntLimit(new Scanner(System.in), 1, 3);
        SOLVERTYPE = choseSolverType(intSolverType);
        System.out.println("Entrez le numero du planner a utiliser : ");
        System.out.println("1 - A*");
        System.out.println("2 - Dijkstra");
        System.out.println("3 - BFS");
        System.out.println("4 - DFS");
        int intPlannerType = scannerIntLimit(new Scanner(System.in), 1, 4);
        PLANNERTYPE = chosePlannerType(intPlannerType);
        System.out.println("Voulez vous l'affichage complet ? : ");
        System.out.println("1 - Oui");
        System.out.println("2 - Non");
        String strFullDisplay = scannerString(new Scanner(System.in), "Vous devez entrer 'oui' ou 'non' !");
        FULL_DISPLAY = (strFullDisplay.equals("oui"));
        System.out.println("Entrez le nombre maximal de solutions a trouver : (compris entre 0 et 3000)");
        NB_HOUSE_DATAMINING = scannerIntLimit(new Scanner(System.in), 0, 3000);
        System.out.println("Entrez la confiance minimale souhaitee : (compris entre 0 et 1)");
        MIN_CONFIDENCE = scannerFloatLimit(new Scanner(System.in), 0, 1);
        System.out.println("Entrez la frequence minimale souhaitee : (compris entre 0 et 1)");
        MIN_FREQUENCY = scannerFloatLimit(new Scanner(System.in), 0, 1);
        System.out.println();
        System.out.println("Parametres enregistres !\nMaison de "+WIDTH+"x"+HEIGHT+" cases\nSolveur utilise: "+SOLVERTYPE+"\n"+
                "Nombre de solutions maximales a trouver : "+NB_HOUSE_DATAMINING+"\n"+
                "Confiance minimale : "+MIN_CONFIDENCE+"\n"+
                "Frequence minimale : "+MIN_FREQUENCY);
    }

    public static String choseSolverType(int str){
        switch(str){
            case 1 :
                return "backtrack";
            case 2 :
                return "mac";
            case 3 :
                return "macheuristic";
            default:
                throw new IllegalStateException("Unexpected value: " + str);
        }
    }

    public static String chosePlannerType(int str){
        switch(str){
            case 1 :
                return "astar";
            case 2 :
                return "dijkstra";
            case 3 :
                return "BFS";
            case 4 :
                return "DFS";
            default:
                throw new IllegalStateException("Unexpected value: " + str);
        }
    }

    public static String scannerString(Scanner scanner, String errormsg) {
        while (!scanner.hasNext()) {
            System.out.println(errormsg);
            scanner.nextLine();
        }
        return scanner.next();
    }

    // Verifie que l'input est un int
    public static int scannerInt(Scanner scanner, String errormsg) {
        while (!scanner.hasNextInt()) {
            System.out.println(errormsg);
            scanner.nextLine();
        }
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    // Verifie que l'input est un float
    public static float scannerFloat(Scanner scanner, String errormsg) {
        while (!scanner.hasNextFloat()) {
            System.out.println(errormsg);
            scanner.nextLine();
        }
        float input = scanner.nextFloat();
        scanner.nextLine();
        System.out.println("VALEUR : "+input);
        return input;
    }

    // Verifie que l'input est un int et qu'il est entre min et max
    public static int scannerIntLimit(Scanner scanner, int min, int max) {
        int input;
        int cpt = 0;
        do {
            if (cpt >= 1) { //affiche le msg d'erreur specifique a scannerIntLimit apres un cycle (donc une erreur de l'utilisateur)
                System.out.println("Entrez un nombre entre " + min + " et " + max + " !");
            }
            cpt++;
            input = scannerInt(scanner, "Veuillez entrer un nombre entier");
        } while (input < min || input > max);
        return input;
    }

    // Verifie que l'input est un int et qu'il est entre min et max
    public static float scannerFloatLimit(Scanner scanner, int min, int max) {
        float input;
        int cpt = 0;
        do {
            if (cpt >= 1) { //affiche le msg d'erreur specifique a scannerIntLimit apres un cycle (donc une erreur de l'utilisateur)
                System.out.println("Entrez un nombre entre " + min + " et " + max + " !");
            }
            cpt++;
            input = scannerFloat(scanner, "Veuillez entrer un nombre decimale a virgule");
        } while (input < min || input > max);
        return input;
    }

}