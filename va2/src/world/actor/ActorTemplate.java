package world.actor;

import combat.Combatant;
import resources.glyph.BalancedGlyphTemplate;
import world.WorldObjectTemplate;

import java.awt.*;

/**
 * Contains information required to describe and represent a generic type of actor.
 */
public class ActorTemplate extends WorldObjectTemplate {
    private final int energyGainPerTurn;
    private final Combatant combatant;
    private final int minimumDepth;

    public ActorTemplate(
            String d,
            String n,
            Color nc,
            BalancedGlyphTemplate bgt,
            boolean r,
            int e,
            Combatant c,
            int md) {
        super(d, n, nc, bgt, r);
        energyGainPerTurn = e;
        combatant = c;
        minimumDepth = md;
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
}
