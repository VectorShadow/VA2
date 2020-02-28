package world.actor;

import world.WorldObjectTemplate;

import java.awt.*;
import java.util.ArrayList;

/**
 * Contains information required to describe and represent a generic type of actor.
 */
public class ActorTemplate extends WorldObjectTemplate {
    private final int energyGainPerTurn;
    public ActorTemplate(boolean r, ArrayList<Character> s, ArrayList<Color> b, ArrayList<Color> f, int e) {
        super(r, s, b, f);
        energyGainPerTurn = e;
    }

    public int getEnergyGainPerTurn() {
        return energyGainPerTurn;
    }
}
