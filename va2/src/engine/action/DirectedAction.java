package engine.action;

import util.Direction;

public abstract class DirectedAction extends Action {
    private final Direction direction;

    public DirectedAction(Direction d, double actorMultiplier) {
        super(actorMultiplier);
        direction = d;
    }

    public Direction getDirection() {
        return direction;
    }
}
