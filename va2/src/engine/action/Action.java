package engine.action;

import java.io.Serializable;

/**
 * Defines all properties of actions which affect the game state.
 */
public abstract class Action implements Serializable {
    private final int energyCost;

    public Action(double energyMultiplier) {
        int adjustedEnergyCost = (int)(energyMultiplier * (double)getDefaultEnergyCost());
        if (adjustedEnergyCost > ActionDefinitions.MAXIMUM_ACTION_ENERGY)
            throw new IllegalArgumentException("Invalid energy cost: " + adjustedEnergyCost);
        energyCost = adjustedEnergyCost;
    }

    public int getEnergyCost() {
        return energyCost;
    }

    protected abstract int getDefaultEnergyCost();
}
