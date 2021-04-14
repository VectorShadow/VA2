package world.actor;

import ai.AI;
import combat.Combatant;
import main.progression.rewards.Loot;
import resources.glyph.BalancedGlyphTemplate;
import world.WorldObjectTemplate;


/**
 * Contains information required to describe and represent a generic type of actor.
 */
public class ActorTemplate extends WorldObjectTemplate {
    private final int ENERGY_GAIN_PER_TURN;
    private final Combatant COMBATANT;
    private final int MINIMUM_DEPTH;
    private final int REWARD_EXPERIENCE;
    private final int REWARD_ITEM_FAMILY;
    private final AI AI;

    public ActorTemplate(
            String d,
            String n,
            BalancedGlyphTemplate bgt,
            boolean reflect,
            int e,
            int md,
            Combatant c,
            int rxp,
            int rif,
            AI AI) {
        super(d, n, bgt, reflect);
        ENERGY_GAIN_PER_TURN = e;
        MINIMUM_DEPTH = md;
        COMBATANT = c;
        REWARD_EXPERIENCE = rxp;
        REWARD_ITEM_FAMILY = rif;
        this.AI = AI;
    }
    private ActorTemplate(ActorTemplate at) {
        this(
                at.DESCRIPTION,
                at.NAME,
                at.BALANCED_GLYPH_TEMPLATE.clone(),
                at.reflectsLight(),
                at.ENERGY_GAIN_PER_TURN,
                at.MINIMUM_DEPTH,
                at.COMBATANT.clone(),
                at.REWARD_EXPERIENCE,
                at.REWARD_ITEM_FAMILY,
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

    public int getRewardExperience() {
        return REWARD_EXPERIENCE;
    }

    public int getRewardItemFamily() {
        return REWARD_ITEM_FAMILY;
    }

    public AI getAi() {
        return AI;
    }

    @Override
    public ActorTemplate clone() {
        return new ActorTemplate(this);
    }
}
