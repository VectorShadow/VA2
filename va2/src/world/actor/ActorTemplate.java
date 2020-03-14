package world.actor;

import ai.AI;
import combat.Combatant;
import main.progression.Reward;
import resources.glyph.BalancedGlyphTemplate;
import world.WorldObjectTemplate;


/**
 * Contains information required to describe and represent a generic type of actor.
 */
public class ActorTemplate extends WorldObjectTemplate {
    private final int energyGainPerTurn;
    private final Combatant combatant;
    private final int minimumDepth;
    private final Reward reward;
    private final AI ai;

    public ActorTemplate(
            String d,
            String n,
            BalancedGlyphTemplate bgt,
            boolean reflect,
            int e,
            Combatant c,
            int md,
            Reward reward,
            AI ai) {
        super(d, n, bgt, reflect);
        energyGainPerTurn = e;
        combatant = c;
        minimumDepth = md;
        this.reward = reward;
        this.ai = ai;
    }


    public int getEnergyGainPerTurn() {
        return energyGainPerTurn;
    }

    public Combatant getCombatant() {
        return combatant;
    }

    public int getMinimumDepth() {
        return minimumDepth;
    }

    public Reward getReward() {
        return reward;
    }

    public AI getAi() {
        return ai;
    }
}
