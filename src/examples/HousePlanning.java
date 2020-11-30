package examples;

import planning.*;
import representation.BooleanVariable;
import representation.Variable;

import java.util.*;

/**
 * Classe qui correspond a un planificateur, sur l'exemple du fil rouge
 */
public class HousePlanning {

    HouseRepresentation houseRepresentation;
    Map<Variable, Object> mapSolved;
    Set<Action> listAction;
    long timeTaken;

    /**
     * Constructeur
     * @param houseRepresentation - Representation d'une maison
     * @param mapSolved - Plan trouve grace a un solveur
     */
    public HousePlanning(HouseRepresentation houseRepresentation, Map<Variable, Object> mapSolved){
        this.houseRepresentation = houseRepresentation;
        this.mapSolved = mapSolved;
        this.listAction = new HashSet<>();
    }

    /**
     * Planificateur AStart
     */
    public void planAStar(){

        long startTime = System.currentTimeMillis();

        BooleanVariable dalleCoulee = houseRepresentation.getDalleCoulee();
        BooleanVariable dalleHumide = houseRepresentation.getDalleHumide();
        BooleanVariable mursEleves = houseRepresentation.getMursEleves();
        BooleanVariable toitureTerminee = houseRepresentation.getToitureTerminee();

        //On part d'une maison vide avec une dalle non coulee
        Map<Variable, Object> start = new HashMap<>();
        for(Variable var : houseRepresentation.getListVariable()){
            start.put(var, null);
        }
        start.put(dalleCoulee, false);

        //Le but est d'arrive a une composition valable comme ci dessous
        Map<Variable, Object> end = new HashMap<>(this.mapSolved);
        end.put(dalleCoulee, true);
        end.put(dalleHumide, false);
        end.put(mursEleves, true);
        end.put(toitureTerminee, true);
        Goal but = new BasicGoal(end);

        //Declaration des variables
        Set<Action> actions = new HashSet<>();
        Map<Variable, Object> precondition;
        Map<Variable, Object> effect;

        //On positionne les pieces
        precondition = new HashMap<>();
        precondition.put(dalleCoulee, true);
        precondition.put(dalleHumide, false);
        for (Object pieceGeneral : houseRepresentation.getDomaine()) {
            for (Variable var : houseRepresentation.getListVariable()) {
                if(!(var instanceof BooleanVariable)){
                    effect = new HashMap<>();
                    effect.put(var, pieceGeneral);
                    Map<Variable, Object> preconditionTmp = new HashMap<>(precondition);
                    preconditionTmp.put(var, null);
                    actions.add(new BasicActionWithString(preconditionTmp, effect, HouseDemo.PLANNING_COST, "On positionne la piece : " + var.getName()));
                }

            }
        }

        //Couler dalle
        precondition = new HashMap<>();
        effect = new HashMap<>();
        effect.put(dalleCoulee, true);
        effect.put(dalleHumide, true);
        actions.add(new BasicActionWithString(precondition, effect, HouseDemo.PLANNING_COST, "Couler la dalle"));

        //Sechange dalle
        precondition = new HashMap<>();
        effect = new HashMap<>();
        precondition.put(dalleCoulee, true);
        precondition.put(dalleHumide, true);
        effect.put(dalleHumide, false);
        actions.add(new BasicActionWithString(precondition, effect, HouseDemo.PLANNING_COST, "Attendre que la dalle seche"));

        //Consruire murs
        precondition = new HashMap<>();
        effect = new HashMap<>();
        precondition.put(dalleCoulee, true);
        precondition.put(dalleHumide, false);
        effect.put(mursEleves, true);
        actions.add(new BasicActionWithString(precondition, effect, HouseDemo.PLANNING_COST, "Construire les murs"));

        //Construire toiture
        precondition = new HashMap<>();
        effect = new HashMap<>();
        precondition.put(dalleCoulee, true);
        precondition.put(mursEleves, true);
        effect.put(toitureTerminee, true);
        actions.add(new BasicActionWithString(precondition, effect, HouseDemo.PLANNING_COST, "Construire le toit"));

        //Creation de  l'heuristique
        Heuristic heuristic = new Heuristic() {
            @Override
            public float estimate(Map<Variable, Object> state) {
                return state.size() - end.size();
            }
        };

        //Utilisation du A*
        AStarPlanner aStarPlanner = new AStarPlanner(start, actions, but, heuristic);

        //Affection des resultats
        this.listAction = aStarPlanner.getActions();
        long endTime = System.currentTimeMillis();
        this.timeTaken = endTime - startTime;

    }

    /**
     * Methode retournant le coût de toutes les actions de l'instance actuelle
     * @return Coût des actions
     */
    public int getTotalActionCost(){
        int cost = 0;
        for(Action action : this.listAction){
            cost += action.getCost();
        }
        return cost;
    }

    /**
     * Printer du resultat
     */
    public void printResults(){
        System.out.println();
        System.out.println("============= LISTE DES ACTIONS A EFFECTUER =============");
        for (Action action : this.listAction) {
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.print("* Action : " + action.toString() + "  ");
        }
        System.out.println();
        System.out.println("* Planning avec A*");
        System.out.println("* Temps mit par le planner : " + this.timeTaken + "ms");
        System.out.println("* Cout total : " + this.getTotalActionCost());
        System.out.println("* Nombre d'action : " + this.listAction.size());
    }

}
