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
            String d,
            String n,
            Color nc,
            ArrayList<Character> s,
            ArrayList<Color> b,
            ArrayList<Color> f,
            boolean r,
            int e,
            Combatant c,
            int md) {
        super(d, n, nc, s, b, f, r);
        energyGainPerTurn = e;
        combatant = c.clone();
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
