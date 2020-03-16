package engine.action;

/**
 * Defines constants for actions.
 */
public class ActionDefinitions {
    public static final int MAXIMUM_ACTION_ENERGY = 1_024;
    public static final int ACTION_ENERGY_BUFFER = 64; //actors must generate this much energy over their next action's cost
    public static final int MAXIMUM_ACTOR_ENERGY = MAXIMUM_ACTION_ENERGY + ACTION_ENERGY_BUFFER;

    static final int ADJACENT_MOVEMENT_ACTION_ENERGY_COST = 256;

    static final int PAUSE_ACTION_ENERGY_COST = -32; //resting restores energy above the base rate

    static final int ADJACENT_ATTACK_ACTION_ENERGY_COST = 192;

    public static final int REACT_TO_ATTACK_ENERGY_COST = ADJACENT_ATTACK_ACTION_ENERGY_COST / 8;

}
