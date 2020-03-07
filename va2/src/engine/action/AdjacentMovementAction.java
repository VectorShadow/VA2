package engine.action;

import util.Direction;

public class AdjacentMovementAction extends DirectedAction {
    private final Direction direction;

    public AdjacentMovementAction(Direction d, double actorMultiplier) {
        super(d, d.isDiagonal() ? actorMultiplier * (3.0 / 2.0) : actorMultiplier);
        direction = d;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    protected int getDefaultEnergyCost() {
        return ActionDefinitions.ADJACENT_MOVEMENT_ACTION_ENERGY_COST;
    }
}
