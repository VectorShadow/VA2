package world.actor;

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
    private int currentEnergy = ActionDefinitions.MAXIMUM_ACTION_ENERGY;
    private int energyGainPerTurn;
    private LinkedList<Action> queuedActions = new LinkedList<>();


    public Actor(ActorTemplate t) {
        super(t);
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
    }
    public void gainEnergy() {
        currentEnergy += energyGainPerTurn;
        if (currentEnergy > ActionDefinitions.MAXIMUM_ACTION_ENERGY)
            currentEnergy = ActionDefinitions.MAXIMUM_ACTION_ENERGY;
    }
    public boolean hasQueuedAction() {
        return !queuedActions.isEmpty();
    }

    public Coordinate getLocation() {
        return location;
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
    public void queueAction(Action a) {
        queuedActions.addLast(a);
    }

    public void setLocation(Coordinate location) {
        this.location = location;
    }
}
