package world.actor;

import combat.Combatant;
import engine.action.Action;
import engine.action.ActionDefinitions;
import main.Session;
import main.progression.rewards.Loot;
import main.progression.rewards.Reward;
import status.StatusEffect;
import status.StatusType;
import util.Coordinate;
import world.WorldObject;
import world.light.Light;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import static status.StatusType.*;

/**
 * Contains all the information to describe a specific actor within a game level map.
 */
public class Actor extends WorldObject {

    private Coordinate location; // #REFACTOR - as is
    private Combatant combatant; // #REFACTOR - TODO - deprecate Combatant, pull combat stats from REFACTORED ActorTemplate
    private int currentEnergy = 0; // #REFACTOR - as is
    private int energyGainPerTurn; // #REFACTOR - TODO - get this from REFACTORED ActorTemplate
    private LinkedList<Action> queuedActions = new LinkedList<>(); // #REFACTOR - as is
    private ArrayList<StatusEffect> statusEffects; // #REFACTOR - TODO - REFACTOR status effects and re-assess
    private final Reward REWARD; // #REFACTOR - TODO - deprecate Reward, pull experience and drop table from REFACTORED ActorTemplate


    public Actor(ActorTemplate t, Reward r) {
        super(t);
        combatant = t.getCombatant().clone();
        energyGainPerTurn = t.getEnergyGainPerTurn();
        statusEffects = new ArrayList<>();
        REWARD = r;
//        new Reward(
//                t.getRewardExperience(),
//                Loot.generateDropTable(dungeonTheme, rewardQuality, t.getRewardItemFamily(), noDropChance)
//        );
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
        currentEnergy += (int)((double)energyGainPerTurn * statusScale(ENERGY_GAIN));
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
        return statusScale(MOVE_ENERGY);
    }
    public double getAttackEnergyMultiplier() {
        return statusScale(ATTACK_ENERGY);
    }
    public int getAdjustedStatistic(int combatantStatisticIndex) {
        int combatantStatistic = combatant.getStatistic(combatantStatisticIndex);
        return (int)((double)combatantStatistic * statusScale(combatantStatisticIndex));
    }

    private double statusScale(int affects) {
        double multiplier = 1.0;
        StatusEffect se;
        for (Iterator<StatusEffect> sei = statusEffects.iterator(); sei.hasNext();) {
            se = sei.next();
            if (se.getType().AFFECTS[affects]){
                if (se.inEffect())
                    multiplier *= se.getType().SCALE;
                else
                    sei.remove();
            }
        }
        return multiplier;
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

    public ArrayList<StatusEffect> getStatusEffects() {
        return statusEffects;
    }
    public void applyStatus(StatusType st) {
        long duration = (long)(st.DURATION_LIMIT - Session.getRNG().nextInt(st.DURATION_VARIANCE));
        for (StatusEffect se : statusEffects) {
            if (se.getType() == st) {
                se.updateExpiration(duration);
                return;
            }
        }
        statusEffects.add(new StatusEffect(st, duration));
    }

    public Reward finalizeReward() {
        return REWARD.finalize(this);
    }

    @Override
    public String toString() {
        return getTemplate().getName() + ": " + getCombatant();
    }
}
