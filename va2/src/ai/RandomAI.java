package ai;

import engine.action.Action;
import engine.action.AdjacentMovementAction;
import engine.action.PauseAction;
import main.Session;
import util.Direction;
import world.actor.Actor;

/**
 * An AI which moves randomly.
 */
public class RandomAI extends AbstractAI {
    @Override
    public Action decide(Actor a) {
        Direction target = Direction.SELF;
        Action action;
        do {
            int r = Session.getRNG().nextInt(Direction.values().length);
            for (Direction direction : Direction.values()) {
                if (direction.ordinal() == r) target = direction;
            }
            action = target == Direction.SELF ? new PauseAction() : new AdjacentMovementAction(target);
        } while(!Session.getEngine().validate(a, action));
        return action;
    }
}
