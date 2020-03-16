package world.actor;

import combat.Combatant;
import engine.action.Action;
import engine.action.ActionDefinitions;
import util.Coordinate;
import world.WorldObject;

import java.util.LinkedList;


/**
 * Contains all the information to describe a specific actor within a game level map.
 */
public class Actor extends WorldObject {

    private Coordinate location;
    private Combatant combatant;
    private int currentEnergy = 0;
    private int energyGainPerTurn;
    private LinkedList<Action> queuedActions = new LinkedList<>();


    public Actor(ActorTemplate t) {
        super(t);
        combatant = t.getCombatant().clone();
        energyGainPerTurn = t.getEnergyGainPerTurn();
    }
    public void clearQueuedActions() {
        queuedActions = new LinkedList<>();
    }
    public void consumeEnergy(int cost) {
        if (!hasEnoughEnergy(cost))
            throw new IllegalArgumentException("Attempted to consume more energy than Actor had (" + cost + "/" +
                    currentEnergy + ").");
        currentEnergy -= cost;
        //allow negative energy actions (for example, resting) without exceeding the cap
        if (currentEnergy > ActionDefinitions.MAXIMUM_ACTOR_ENERGY)
            currentEnergy = ActionDefinitions.MAXIMUM_ACTOR_ENERGY;
    }
    public void gainEnergy() {
        currentEnergy += energyGainPerTurn;
        if (currentEnergy > ActionDefinitions.MAXIMUM_ACTOR_ENERGY)
            currentEnergy = ActionDefinitions.MAXIMUM_ACTOR_ENERGY;
    }
    public boolean hasQueuedAction() {
        return !queuedActions.isEmpty();
    }

    public Coordinate getLocation() {
        return location;
    }

    public Combatant getCombatant() {
        return combatant;
    }

    public Action checkQueuedAction() {
        if (!hasQueuedAction())
            throw new IllegalStateException("No Actions are queued.");
        return queuedActions.peekFirst();
    }
    public Action removeQueuedAction() {
        if (!hasQueuedAction())
            throw new IllegalStateException("No Actions are queued.");
        return queuedActions.removeFirst();
    }

    public boolean hasEnoughEnergy(int cost) {
        return currentEnergy > cost;
    }
    public void plan() {
        //todo - hack! call the template's AI and use it to generate an action or set of actions
        queueAction(((ActorTemplate)getTemplate()).getAi().decide(this));
    }
    public void queueAction(Action a) {
        queuedActions.addLast(a);
    }

    public void setLocation(Coordinate location) {
        this.location = location;
    }

    public double getMoveEnergyMultiplier() {
        //hack - todo: implement an array of multipliers
        //todo - check temporary effects
        return 1.0;
    }
    public double getAttackEnergyMultiplier() {
        //hack - todo: implement an array of multipliers
        //todo - check temporary effects
        return 1.0;
    }
    public int getAdjustedStatistic(int combatantStatisticIndex) {
        int combatantStatistic = combatant.getStatistic(combatantStatisticIndex);
        //todo - use the index to check status array and adjust with any that apply
        return combatantStatistic;
    }
}
