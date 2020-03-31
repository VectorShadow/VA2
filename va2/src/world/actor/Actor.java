package world.actor;

import combat.Combatant;
import engine.action.Action;
import engine.action.ActionDefinitions;
import main.Session;
import status.StatusType;
import util.Coordinate;
import world.WorldObject;
import world.light.Light;

import java.util.LinkedList;

import static status.StatusType.*;

/**
 * Contains all the information to describe a specific actor within a game level map.
 */
public class Actor extends WorldObject {

    private Coordinate location;
    private Combatant combatant;
    private int currentEnergy = 0;
    private int energyGainPerTurn;
    private LinkedList<Action> queuedActions = new LinkedList<>();
    private long[] status = new long[StatusType.values().length];


    public Actor(ActorTemplate t) {
        super(t);
        combatant = t.getCombatant().clone();
        energyGainPerTurn = t.getEnergyGainPerTurn();
        for (int i = 0; i < status.length; ++i)
            status[i] = 0;
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
        double multiplier = 1.0;
        for (StatusType st : StatusType.values()) {
            if (st.AFFECTS[ENERGY_GAIN] && checkStatus(st)) {
                multiplier = st.SCALE;
            }
        }
        currentEnergy += (int)((double)energyGainPerTurn * multiplier);
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
        queueAction(((ActorTemplate) getTemplate()).getAi().decide(this));
    }
    public void queueAction(Action a) {
        queuedActions.addLast(a);
    }

    public void setLocation(Coordinate location) {
        this.location = location;
    }

    public double getMoveEnergyMultiplier() {
        for (StatusType st : StatusType.values()) {
            if (st.AFFECTS[MOVE_ENERGY] && checkStatus(st)) {
                System.out.println(status[st.ordinal()]);
                return st.SCALE;
            }
        }
        return 1.0;
    }
    public double getAttackEnergyMultiplier() {
        for (StatusType st : StatusType.values()) {
            if (st.AFFECTS[ATTACK_ENERGY] && checkStatus(st)) {
                return st.SCALE;
            }
        }
        return 1.0;
    }
    public int getAdjustedStatistic(int combatantStatisticIndex) {
        int combatantStatistic = combatant.getStatistic(combatantStatisticIndex);
        for (StatusType st : StatusType.values()) {
            if (st.AFFECTS[combatantStatisticIndex] && checkStatus(st)) {
                combatantStatistic = (int)((double)combatantStatistic * st.SCALE);
            }
        }
        return combatantStatistic;
    }

    public Light getLight() {
        //hack - todo: Actors need lights, player uses equipped light
        if (this == Session.getPlayer().getActor())
            return Light.TORCH;
        return null;
    }
    public int getSight() {
        //hack - todo: Actors need vision scores
        return 10;
    }
    public void applyStatus(StatusType st) {
        int index = st.ordinal();
        long duration = (long)(st.DURATION_LIMIT - Session.getRNG().nextInt(st.DURATION_VARIANCE));
        long currentValue = status[index];
        /**
         * Counter based status types stack on top of the current counter.
         * Time based status effects use the later end time between the existing value
         * and the new value.
         */
        status[index] = st.COUNTER
                ? currentValue + duration
                : Math.max(currentValue, Session.getEngine().getGameTurn() + duration);
    }

    /**
     * Check whether this status is in effect. If it is a counter type, decrement it.
     */
    public boolean checkStatus(StatusType st) {
        if (inEffect(st)) {
            if (st.COUNTER) status[st.ordinal()]--;
            return true;
        }
        return false;
    }

    /**
     * Check whether this status is in effect.
     */
    public boolean inEffect(StatusType st) {
        int index = st.ordinal();
        return st.COUNTER ? status[index] > 0 : status[index] > Session.getEngine().getGameTurn();
    }

    @Override
    public String toString() {
        return getTemplate().getName() + ": " + getCombatant();
    }
}
