package examples;

import planning.*;
import representation.BooleanVariable;
import representation.Variable;

import java.util.*;

public class HousePlanning {

    HouseRepresentation houseRepresentation;
    Map<Variable, Object> mapSolved;
    Set<Action> listAction;
    long timeTaken;

    public HousePlanning(HouseRepresentation houseRepresentation, Map<Variable, Object> mapSolved){
        this.houseRepresentation = houseRepresentation;
        this.mapSolved = mapSolved;
        this.listAction = new HashSet<>();
    }

    public void planAStar(){

        long startTime = System.currentTimeMillis();

        BooleanVariable dalleCoulee = houseRepresentation.getDalleCoulee();
        BooleanVariable dalleHumide = houseRepresentation.getDalleHumide();
        BooleanVariable mursEleves = houseRepresentation.getMursEleves();
        BooleanVariable toitureTerminee = houseRepresentation.getToitureTerminee();

        Map<Variable, Object> start = new HashMap<>();
        start.put(dalleCoulee, false);

        Map<Variable, Object> end = new HashMap<>(this.mapSolved);
        end.put(dalleCoulee, true);
        end.put(dalleHumide, false);
        end.put(mursEleves, true);
        end.put(toitureTerminee, true);
        Goal but = new BasicGoal(end);

        Set<Action> actions = new HashSet<>();
        Map<Variable, Object> precondition;
        Map<Variable, Object> effect;

        //Couler dalle
        precondition = new HashMap<>();
        effect = new HashMap<>();
        effect.put(dalleCoulee, true);
        effect.put(dalleHumide, true);
        actions.add(new BasicAction(precondition, effect, HouseDemo.PLANNING_COST));

        //Sechange dalle
        precondition = new HashMap<>();
        effect = new HashMap<>();
        precondition.put(dalleCoulee, true);
        precondition.put(dalleHumide, true);
        effect.put(dalleHumide, false);
        actions.add(new BasicAction(precondition, effect, HouseDemo.PLANNING_COST));

        //Consruire murs
        precondition = new HashMap<>();
        effect = new HashMap<>();
        precondition.put(dalleCoulee, true);
        precondition.put(dalleHumide, false);
        effect.put(mursEleves, true);
        actions.add(new BasicAction(precondition, effect, HouseDemo.PLANNING_COST));

        //Construire toiture
        precondition = new HashMap<>();
        effect = new HashMap<>();
        precondition.put(dalleCoulee, true);
        precondition.put(mursEleves, true);
        effect.put(toitureTerminee, true);
        actions.add(new BasicAction(precondition, effect, HouseDemo.PLANNING_COST));

        //On positionne les pieces
        precondition = new HashMap<>();
        precondition.put(dalleCoulee, true);
        precondition.put(dalleHumide, false);
        for (Object pieceGeneral : houseRepresentation.getDomaine()) {
            for (Variable var : houseRepresentation.getListVariable()) {
                effect = new HashMap<>();
                effect.put(var, pieceGeneral);
                Map<Variable, Object> preconditionTmp = new HashMap<>(precondition);
                actions.add(new BasicAction(preconditionTmp, effect, HouseDemo.PLANNING_COST));
            }
        }

        //Creation de  l'heuristique
        Heuristic heuristic = new Heuristic() {
            @Override
            public float estimate(Map<Variable, Object> state) {
                return state.size() - end.size();
            }
        };

        //Utilisation du A*
        new AStarPlanner(start, actions, but, heuristic);

        //Affection des resultats
        this.listAction = actions;
        long endTime = System.currentTimeMillis();
        this.timeTaken = endTime - startTime;

    }

    public int getTotalActionCost(){
        int cost = 0;
        for(Action action : this.listAction){
            cost += action.getCost();
        }
        return cost;
    }

    public void printResults(){
        System.out.println();
        System.out.println("============= LISTE DES ACTIONS A EFFECTUER =============");
        for (Action action : this.listAction) {
            System.out.print("--> Action : " + action);
        }
        System.out.println("* Planning avec A*");
        System.out.println("* Temps mit par le planner : " + this.timeTaken + "ms");
        System.out.println("* Cout total : " + this.getTotalActionCost());
        System.out.println("* Nombre d'action : " + this.listAction.size());
    }

}
