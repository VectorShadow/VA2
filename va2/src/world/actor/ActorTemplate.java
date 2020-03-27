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
    private final int ENERGY_GAIN_PER_TURN;
    private final Combatant COMBATANT;
    private final int MINIMUM_DEPTH;
    private final Reward REWARD;
    private final AI AI;

    public ActorTemplate(
            String d,
            String n,
            BalancedGlyphTemplate bgt,
            boolean reflect,
            int e,
            Combatant c,
            int md,
            Reward REWARD,
            AI AI) {
        super(d, n, bgt, reflect);
        ENERGY_GAIN_PER_TURN = e;
        COMBATANT = c;
        MINIMUM_DEPTH = md;
        this.REWARD = REWARD;
        this.AI = AI;
    }
    private ActorTemplate(ActorTemplate at) {
        this(
                at.DESCRIPTION,
                at.NAME,
                at.BALANCED_GLYPH_TEMPLATE.clone(),
                at.reflectsLight(),
                at.ENERGY_GAIN_PER_TURN,
                at.COMBATANT.clone(),
                at.MINIMUM_DEPTH,
                at.REWARD,
                at.AI);
    }


    public int getEnergyGainPerTurn() {
        return ENERGY_GAIN_PER_TURN;
    }

    public Combatant getCombatant() {
        return COMBATANT;
    }

    public int getMinimumDepth() {
        return MINIMUM_DEPTH;
    }

    public Reward getReward() {
        return REWARD;
    }

    public AI getAi() {
        return AI;
    }

    @Override
    public ActorTemplate clone() {
        return new ActorTemplate(this);
    }
}
