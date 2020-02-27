package ai;

import engine.action.Action;
import engine.action.AdjacentMovementAction;
import main.Session;
import util.Direction;
import world.actor.Actor;

import java.util.Random;

/**
 * An AI which moves randomly.
 */
public class RandomAI extends AbstractAI {
    private final Random random = new Random();
    @Override
    public Action decide(Actor a) {
        Direction target = Direction.SELF;
        Action action;
        do {
            int r = random.nextInt(Direction.values().length);
            for (Direction direction : Direction.values()) {
                if (direction.ordinal() == r) target = direction;
            }
            action = new AdjacentMovementAction(target);
        } while(target == Direction.SELF || !Session.getEngine().validate(a, action));
        return action;
    }
}
