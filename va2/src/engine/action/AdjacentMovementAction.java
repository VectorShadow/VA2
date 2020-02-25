package engine.action;

import util.Direction;

public class AdjacentMovementAction extends Action {
    private final Direction direction;

    public AdjacentMovementAction(Direction d) {
        super(
                d.isDiagonal()
                        ? (ActionDefinitions.ADJACENT_MOVEMENT_ACTION_ENERGY_COST * 3) / 2
                        : ActionDefinitions.ADJACENT_MOVEMENT_ACTION_ENERGY_COST
        );
        direction = d;
    }

    public Direction getDirection() {
        return direction;
    }
}
