package world.actor;

import engine.Action;
import world.WorldObject;

import java.util.LinkedList;


/**
 * Contains all the information to describe a specific actor within a game level map.
 */
public class Actor extends WorldObject {

    public static final int MAXIMUM_ENERGY = 1_024;

    private int energyGainPerTurn;
    private int currentEnergy = MAXIMUM_ENERGY;
    private LinkedList<Action> queuedActions = new LinkedList<>();
    public Actor(ActorTemplate t) {
        super(t);
        energyGainPerTurn = t.getEnergyGainPerTurn();
    }
    public void consumeEnergy(int cost) {
        if (!hasEnoughEnergy(cost))
            throw new IllegalArgumentException("Attempted to consume more energy than Actor had (" + cost + "/" +
                    currentEnergy + ").");
        currentEnergy -= cost;
    }
    public void gainEnergy() {
        currentEnergy += energyGainPerTurn;
        if (currentEnergy > MAXIMUM_ENERGY) currentEnergy = MAXIMUM_ENERGY;
    }
    public boolean hasQueuedAction() {
        return !queuedActions.isEmpty();
    }
    public Action getQueuedAction() {
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
}
