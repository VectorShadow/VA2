package engine.action;

/**
 * Defines constants for actions.
 */
public class ActionDefinitions {
    public static final int MAXIMUM_ACTION_ENERGY = 1_024;

    static final int ADJACENT_MOVEMENT_ACTION_ENERGY_COST = 256;

    static final int PAUSE_ACTION_ENERGY_COST = -32; //resting restores energy above the base rate

    static final int ADJACENT_ATTACK_ACTION_ENERGY_COST = 128;

    static final int REACT_TO_ATTACK_ENERGY_COST = ADJACENT_ATTACK_ACTION_ENERGY_COST / 8;

}
