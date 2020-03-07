package world.actor;

import combat.Combatant;
import world.WorldObjectTemplate;

import java.awt.*;
import java.util.ArrayList;

/**
 * Contains information required to describe and represent a generic type of actor.
 */
public class ActorTemplate extends WorldObjectTemplate {
    private final int energyGainPerTurn;
    private final Combatant combatant;
    private final int minimumDepth;

    public ActorTemplate(
            String n,
            String d,
            boolean r,
            ArrayList<Character> s,
            ArrayList<Color> b,
            ArrayList<Color> f,
            int e,
            Combatant c,
            int depth
    ) {
        super(n, d, r, s, b, f);
        energyGainPerTurn = e;
        combatant = c.clone();
        minimumDepth = depth;
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
