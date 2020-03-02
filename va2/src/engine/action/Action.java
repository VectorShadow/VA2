package engine.action;

import java.io.Serializable;

/**
 * Defines all properties of actions which affect the game state.
 */
public abstract class Action implements Serializable {
    private final int energyCost;

    public Action(int energyCost){
        if (energyCost < 0 || energyCost > ActionDefinitions.MAXIMUM_ACTION_ENERGY)
            throw new IllegalArgumentException("Invalid energy cost: " + energyCost);
        this.energyCost = energyCost;
    }

    public int getEnergyCost() {
        return energyCost;
    }
}
