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
    public ActorTemplate(
            String n,
            String d,
            boolean r,
            ArrayList<Character> s,
            ArrayList<Color> b,
            ArrayList<Color> f,
            int e,
            Combatant c
    ) {
        super(n, d, r, s, b, f);
        energyGainPerTurn = e;
        combatant = c.clone();
    }

    public int getEnergyGainPerTurn() {
        return energyGainPerTurn;
    }
}
